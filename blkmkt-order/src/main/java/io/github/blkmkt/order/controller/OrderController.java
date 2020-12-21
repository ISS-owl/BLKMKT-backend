package io.github.blkmkt.order.controller;

import com.alibaba.fastjson.TypeReference;
import io.github.blkmkt.order.entity.OrderEntity;
import io.github.blkmkt.order.enums.OrderStatusEnum;
import io.github.blkmkt.order.exception.NoStockException;
import io.github.blkmkt.order.feign.GoodFeignService;
import io.github.blkmkt.order.feign.UserFeignService;
import io.github.blkmkt.order.feign.WareFeignService;
import io.github.blkmkt.order.service.OrderService;
import io.github.blkmkt.order.vo.*;
import io.github.common.entity.PageParam;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 订单表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 20:22:03
 */
@Api(tags = {"订单"})
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private GoodFeignService goodFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public R list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<OrderEntity> page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    @GetMapping("/confirm")
    @ApiOperation(value = "确认订单", notes = "返回用户信息和商品信息，让用户确认")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "consumerId", value = "消费者id"),
        @ApiImplicitParam(name = "goodId", value = "商品id")
    })
    public R confirm(@RequestParam Integer consumerId, @RequestParam Integer goodId) {
        // 保证接口幂等性
        ConfirmVo confirmVo = new ConfirmVo();

        // 异步获取用户和商品信息
        CompletableFuture<Void> goodCompletableFuture = CompletableFuture.runAsync(() -> {
            R goodInfo = goodFeignService.info(goodId);
            GoodVo good = goodInfo.getData("good", new TypeReference<>() {});
            confirmVo.setGood(good);
        }, executor);

        CompletableFuture<Void> userCompletableFuture = CompletableFuture.runAsync(() -> {
            R userInfo = userFeignService.info(consumerId);
            UserVo user = userInfo.getData("user", new TypeReference<>() {});
            confirmVo.setConsumer(user);
        }, executor);

        // 设置防重令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("order:token" + consumerId, token, 30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        try {
            CompletableFuture.allOf(userCompletableFuture, goodCompletableFuture).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return R.ok().put("data", confirmVo);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存订单", notes = "保存订单信息")
    @ApiImplicitParam(name = "order", value = "order entity", required = true)
    public R save(@RequestBody OrderSubmitVo orderSubmitVo){
        // 校验防重令牌
        Integer consumerId = orderSubmitVo.getConsumerId();
        String script= "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long execute = redisTemplate.execute(
            new DefaultRedisScript<>(script, Long.class),
            Collections.singletonList("order:token" + consumerId),
            orderSubmitVo.getToken()
        );
        if (execute == 0) {
            // 校验失败
            return R.error(
                BizCodeEnum.ORDER_DUPLICATE_SUBMIT_EXCEPTION.getCode(),
                BizCodeEnum.ORDER_DUPLICATE_SUBMIT_EXCEPTION.getMsg()
            );
        }

        // 保存订单
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderSubmitVo, orderEntity);
        orderEntity.setCreateTime(new Date());
        orderEntity.setUpdateTime(new Date());
        orderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
		orderService.save(orderEntity);

		// 获取商品信息
        R goodInfo = goodFeignService.info(orderSubmitVo.getGoodId());
        GoodVo good = goodInfo.getData("good", new TypeReference<>() {});
        // 锁定库存
        WareLockOrderVo wareLockOrderVo = new WareLockOrderVo();
        wareLockOrderVo.setLockedGood(good);
        wareLockOrderVo.setOrderId(orderEntity.getId());
        wareLockOrderVo.setGoodNum(orderEntity.getGoodNum());
        R response = wareFeignService.lockOrder(wareLockOrderVo);
        if ((int)response.get("code") == 200) {
            rabbitTemplate.convertAndSend("order-event-exchange","order.create.order", orderEntity);
        } else {
            String msg = response.get("msg").toString();
            throw new NoStockException(msg);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "order", value = "order entity", required = true)
    public R update(@RequestBody OrderEntity order){
        order.setUpdateTime(new Date());
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
