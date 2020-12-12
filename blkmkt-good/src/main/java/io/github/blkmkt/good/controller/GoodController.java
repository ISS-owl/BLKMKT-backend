package io.github.blkmkt.good.controller;

import java.util.Arrays;
import java.util.Collections;

import io.github.blkmkt.good.feign.ElasticSaveFeignService;
import io.github.blkmkt.good.vo.GoodModel;
import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;
import io.github.common.utils.PageUtils;



/**
 * 货物表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-10 22:26:54
 */
@Api(tags = {""})
@RestController
@RequestMapping("good/good")
public class GoodController {
    @Autowired
    private GoodService goodService;

    @Autowired
    private ElasticSaveFeignService saveFeignService;

    @GetMapping("/up/{id}")
    @ApiOperation(value = "商品上架", notes = "根据给定id上架对应的商品")
    public R upGood(@PathVariable Integer id) {
        // 创建good model
        GoodEntity good = goodService.getById(id);
        GoodModel goodModel = new GoodModel();
        BeanUtils.copyProperties(good, goodModel);
        // 远程调用elasticsearch进行保存
        return saveFeignService.save(Collections.singletonList(goodModel));
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
        PageUtils<GoodEntity> page = goodService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		GoodEntity good = goodService.getById(id);

        return R.ok().put("good", good);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "good", value = "good entity", required = true)
    public R save(@RequestBody GoodEntity good){
		goodService.save(good);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "good", value = "good entity", required = true)
    public R update(@RequestBody GoodEntity good){
		goodService.updateById(good);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		goodService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
