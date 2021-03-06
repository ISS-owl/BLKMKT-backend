package io.github.blkmkt.ware.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.ware.entity.WareGoodEntity;
import io.github.blkmkt.ware.service.WareGoodService;
import io.github.common.utils.PageUtils;



/**
 * 商品库存表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
@Api(tags = {""})
@RestController
@RequestMapping("ware/ware_good")
public class WareGoodController {
    @Autowired
    private WareGoodService wareGoodService;

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
        PageUtils<WareGoodEntity> page = wareGoodService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		WareGoodEntity wareGood = wareGoodService.getById(id);

        return R.ok().put("wareGood", wareGood);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "wareGood", value = "wareGood entity", required = true)
    public R save(@RequestBody WareGoodEntity wareGood){
		wareGoodService.save(wareGood);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "wareGood", value = "wareGood entity", required = true)
    public R update(@RequestBody WareGoodEntity wareGood){
		wareGoodService.updateById(wareGood);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		wareGoodService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
