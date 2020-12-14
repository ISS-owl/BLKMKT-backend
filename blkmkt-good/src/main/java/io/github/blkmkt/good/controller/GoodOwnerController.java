package io.github.blkmkt.good.controller;

import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"商品及其拥有者"})
@RestController
@RequestMapping("good/good")
public class GoodOwnerController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/owner_all_goods")
    @ApiOperation(value = "获取用户所有的商品", notes = "商品包括上架和未上架的")
    @ApiImplicitParam(name = "ownerId", value = "用户id", required = true, example = "1")
    public R getOwnerAllGoods(@RequestParam Integer ownerId) {
        List<GoodEntity> ownerAllGoods = goodService.getOwnerAllGoods(ownerId);

        return R.ok().put("data", ownerAllGoods);
    }

    @GetMapping("owner_up_goods")
    @ApiOperation(value = "获取用户所有上架的商品", notes = "不包括未上架的")
    @ApiImplicitParam(name = "ownerId", value = "用户id", required = true, example = "1")
    public R getOwnerUpGoods(@RequestParam Integer ownerId) {
        List<GoodEntity> ownerUpGoods = goodService.getOwnerUpGoods(ownerId);

        return R.ok().put("data", ownerUpGoods);
    }

    @GetMapping("owner_not_up_goods")
    @ApiOperation(value = "获取用户所有未上架的商品", notes = "不包括上架的")
    @ApiImplicitParam(name = "ownerId", value = "用户id", required = true, example = "1")
    public R getOwnerNotUpGoods(@RequestParam Integer ownerId) {
        List<GoodEntity> ownerNotUpGoods = goodService.getOwnerNotUpGoods(ownerId);
        return R.ok().put("data", ownerNotUpGoods);
    }
}
