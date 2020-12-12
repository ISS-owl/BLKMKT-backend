package io.github.blkmkt.good.controller;

import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;
import io.github.common.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("good/good")
public class GoodUpController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/up/{id}")
    @ApiOperation(value = "商品上架", notes = "根据给定id上架对应的商品")
    public R upGood(@PathVariable Integer id) {
        // 更改mysql中数据的状态
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(id);
        goodEntity.setStatus(1);    // 上架
        goodService.updateById(goodEntity);

        return goodService.upGood(id);
    }

    @PostMapping("/up")
    @ApiOperation(value = "创建商品并上架", notes = "根据给定id创建并上架对应的商品")
    public R upGood(@RequestBody GoodEntity good) {
        // 填充自动计算属性
        good.setStatus(1);  // 上架
        good.setCreateTime(new Date());
        good.setUpdateTime(new Date());

        goodService.save(good);
        return goodService.upGood(good);
    }

    @PutMapping("/up")
    @ApiOperation(value = "更新上架商品", notes = "更新上架商品同时更新数据库")
    public R updateGood(@RequestBody GoodEntity goodEntity) {
        goodService.updateById(goodEntity);
        return goodService.updateGood(goodEntity);
    }

    @DeleteMapping("/up")
    @ApiOperation(value = "删除上架商品", notes = "根据id直接删除上架商品")
    public R deleteGood(@RequestBody List<Integer> ids) {
        return goodService.deleteGood(ids);
    }
}
