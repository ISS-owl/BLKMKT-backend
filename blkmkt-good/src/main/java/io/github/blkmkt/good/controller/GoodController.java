package io.github.blkmkt.good.controller;

import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;
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
@Api(tags = {""})
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
        // 填充自动计算属性
        good.setStatus(0);  // 未上架
        good.setCreateTime(new Date());
        good.setUpdateTime(new Date());
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
        good.setUpdateTime(new Date());
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
