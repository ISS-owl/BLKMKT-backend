package io.github.blkmkt.forum.controller;

import io.github.blkmkt.forum.entity.GoodEntity;
import io.github.blkmkt.forum.service.GoodService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;


/**
 * 货物表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-10 22:26:54
 */
@Api(tags = {"商品"})
@RestController
@RequestMapping("good/good")
public class GoodController {
    @Autowired
    private GoodService goodService;

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
        PageParam params = new PageParam(pageNo, pageSize);
        PageUtils<GoodEntity> page = goodService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 查看商品
     */
    @GetMapping("/")
    @ApiOperation(value = "获取用户所属的商品信息", notes = "根据传入的模式不同返回不同的商品信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ownerId", value = "用户id", required = true, example = "1"),
        @ApiImplicitParam(name = "mode", value = "模式（0-未上架，1-上架，2-全部）", required = true, example = "2"),
        @ApiImplicitParam(name = "pageNo", value = "页号（从1开始）", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页面大小", example = "10"),
    })
    public R getGoodByCondition(
        @RequestParam Integer ownerId,
        @RequestParam Integer mode,
        @RequestParam Long pageNo,
        @RequestParam Long pageSize
    ) {
        PageParam param = new PageParam(pageNo, pageSize);

        PageUtils<GoodEntity> goodEntities;
        if (mode == 0) {
            goodEntities = goodService.getOwnerNotUpGoods(param, ownerId);
        } else if (mode == 1) {
            goodEntities = goodService.getOwnerUpGoods(param, ownerId);
        } else if (mode == 2) {
            goodEntities = goodService.getOwnerAllGoods(param, ownerId);
        } else {
            goodEntities = null;
        }
        return R.ok().put("data", goodEntities);
    }

    /**
     * 创建商品（未上架）
     */
    @PostMapping("/")
    @ApiOperation(value = "创建商品", notes = "创建商品（未上架）")
    @ApiImplicitParam(name = "good", value = "good entity", required = true)
    public R save(@RequestBody GoodEntity good){
        // 填充自动计算属性
        good.setStatus(0);  // 未上架
        good.setCreateTime(new Date());
        good.setUpdateTime(new Date());
		goodService.save(good);

        return R.ok();
    }

    /**
     * 更新商品（未上架）
     */
    @PutMapping("/")
    @ApiOperation(value = "更新商品", notes = "更新商品（未上架）")
    @ApiImplicitParam(name = "good", value = "good entity", required = true)
    public R update(@RequestBody GoodEntity good){
        good.setUpdateTime(new Date());
		goodService.updateById(good);

        return R.ok();
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除商品", notes = "删除商品（未上架）")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		goodService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
