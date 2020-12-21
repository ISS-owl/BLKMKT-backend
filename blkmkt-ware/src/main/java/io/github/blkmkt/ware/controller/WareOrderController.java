package io.github.blkmkt.ware.controller;

import java.util.Arrays;

import io.github.blkmkt.ware.exception.NoStockException;
import io.github.blkmkt.ware.vo.WareLockOrderVo;
import io.github.common.entity.PageParam;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.ware.entity.WareOrderEntity;
import io.github.blkmkt.ware.service.WareOrderService;
import io.github.common.utils.PageUtils;



/**
 * 商品订单信息
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
@Api(tags = {""})
@RestController
@RequestMapping("ware/ware_order")
public class WareOrderController {
    @Autowired
    private WareOrderService wareOrderService;

    @PostMapping("/lock")
    public R lockOrder(@RequestBody WareLockOrderVo wareLockOrderVo) {
        try {
            wareOrderService.orderLockStock(wareLockOrderVo);
            return R.ok();
        } catch (NoStockException e) {
            return R.error(
                BizCodeEnum.ORDER_NUM_NOT_ENOUGH.getCode(),
                BizCodeEnum.ORDER_NUM_NOT_ENOUGH.getMsg()
            );
        }
    }


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
        PageUtils<WareOrderEntity> page = wareOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		WareOrderEntity wareOrder = wareOrderService.getById(id);

        return R.ok().put("wareOrder", wareOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "wareOrder", value = "wareOrder entity", required = true)
    public R save(@RequestBody WareOrderEntity wareOrder){
		wareOrderService.save(wareOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "wareOrder", value = "wareOrder entity", required = true)
    public R update(@RequestBody WareOrderEntity wareOrder){
		wareOrderService.updateById(wareOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		wareOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
