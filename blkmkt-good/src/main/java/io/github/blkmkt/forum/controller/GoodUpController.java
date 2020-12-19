package io.github.blkmkt.forum.controller;

import io.github.blkmkt.forum.entity.GoodEntity;
import io.github.blkmkt.forum.service.GoodService;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = {"商品上架"})
@RestController
@RequestMapping("good/good")
public class GoodUpController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/up/{id}")
    @ApiOperation(value = "商品上架", notes = "根据给定id上架已有的，未上架的商品")
    @ApiImplicitParam(name = "id", value = "未上架的商品id", required = true, example = "1")
    public R upGood(@PathVariable Integer id) {
        // 更改mysql中数据的状态
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(id);
        goodEntity.setStatus(1);    // 上架
        goodService.updateById(goodEntity);

        return goodService.upGood(id);
    }

    @PostMapping("/up")
    @ApiOperation(value = "创建商品并上架", notes = "直接创建商品实体并上架")
    @ApiImplicitParam(name = "good", value = "商品信息", required = true)
    public R upGood(@RequestBody GoodEntity good) {
        // 填充自动计算属性
        good.setStatus(1);  // 上架
        good.setLikeNum(0);
        good.setCreateTime(new Date());
        good.setUpdateTime(new Date());

        goodService.save(good);
        return goodService.upGood(good);
    }

    @PutMapping("/up")
    @ApiOperation(value = "更新上架商品", notes = "更新上架商品同时更新数据库")
    @ApiImplicitParam(name = "good", value = "商品信息", required = true)
    public R updateGood(@RequestBody GoodEntity good) {
        goodService.updateById(good);
        return goodService.updateGood(good);
    }

    @DeleteMapping("/up")
    @ApiOperation(value = "商品下架", notes = "根据id直接删除上架商品")
    @ApiImplicitParam(name = "ids", value = "商品id", required = true)
    public R deleteGood(@RequestBody List<Integer> ids) {
        // 设置状态为下架
        for (Integer id : ids) {
            GoodEntity good = goodService.getById(id);
            good.setStatus(0);
            goodService.updateById(good);
        }
        return goodService.deleteGood(ids);
    }

    @PutMapping("/like/{id}")
    @ApiOperation(value = "点赞商品", notes = "给上架的商品点赞")
    @ApiImplicitParam(name = "id", value = "已上架的商品id", required = true, example = "1")
    public R likeGood(@PathVariable Integer id) {
        GoodEntity goodEntity = goodService.getById(id);
        goodEntity.setLikeNum(goodEntity.getLikeNum() + 1);
        goodService.updateById(goodEntity);

        return goodService.updateGood(goodEntity);
    }
}
