package io.github.blkmkt.order.controller;

import com.alibaba.fastjson.TypeReference;
import io.github.blkmkt.order.entity.OrderEntity;
import io.github.blkmkt.order.feign.GoodFeignService;
import io.github.blkmkt.order.feign.UserFeignService;
import io.github.blkmkt.order.service.OrderService;
import io.github.blkmkt.order.vo.ConfirmVo;
import io.github.blkmkt.order.vo.GoodVo;
import io.github.blkmkt.order.vo.UserVo;
import io.github.common.entity.PageParam;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
        CompletableFuture<Void> userCompletableFuture = CompletableFuture.runAsync(() -> {
            R userInfo = userFeignService.info(consumerId);
            UserVo user = userInfo.getData("user", new TypeReference<>() {});
            confirmVo.setConsumer(user);
        }, executor);

        CompletableFuture<Void> goodCompletableFuture = CompletableFuture.runAsync(() -> {
            R goodInfo = goodFeignService.info(goodId);
            GoodVo good = goodInfo.getData("good", new TypeReference<>() {});
            confirmVo.setGood(good);
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
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "order", value = "order entity", required = true)
    public R save(@RequestBody OrderEntity order){
        // 请求购买的商品数量不能超过商品现存的数量
        // TODO: 改用ware远程调用
        R goodInfo = goodFeignService.info(order.getGoodId());
        GoodVo good = goodInfo.getData("good", new TypeReference<>() {});
        if (good.getCurrentNum() < order.getGoodNum()) {
            return R.error(
                BizCodeEnum.ORDER_NUM_NOT_ENOUGH.getCode(),
                BizCodeEnum.ORDER_NUM_NOT_ENOUGH.getMsg()
            );
        }
        // 保存订单
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
		orderService.save(order);

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
