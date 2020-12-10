package io.github.blkmkt.good.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.good.entity.GoodCommentReplayEntity;
import io.github.blkmkt.good.service.GoodCommentReplayService;
import io.github.common.utils.PageUtils;



/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-10 22:26:54
 */
@Api(tags = {""})
@RestController
@RequestMapping("good/good_comment_replay")
public class GoodCommentReplayController {
    @Autowired
    private GoodCommentReplayService goodCommentReplayService;

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
        PageUtils<GoodCommentReplayEntity> page = goodCommentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Long id){
		GoodCommentReplayEntity goodCommentReplay = goodCommentReplayService.getById(id);

        return R.ok().put("goodCommentReplay", goodCommentReplay);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "goodCommentReplay", value = "goodCommentReplay entity", required = true)
    public R save(@RequestBody GoodCommentReplayEntity goodCommentReplay){
		goodCommentReplayService.save(goodCommentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "goodCommentReplay", value = "goodCommentReplay entity", required = true)
    public R update(@RequestBody GoodCommentReplayEntity goodCommentReplay){
		goodCommentReplayService.updateById(goodCommentReplay);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Long[] ids){
		goodCommentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
