package io.github.blkmkt.forum.controller;

import java.util.Arrays;
import java.util.Date;

import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.CommentReplayEntity;
import io.github.blkmkt.forum.service.CommentReplayService;
import io.github.common.utils.PageUtils;



/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-13 19:36:24
 */
@Api(tags = {"评论回复"})
@RestController
@RequestMapping("good/comment_replay")
public class CommentReplayController {
    @Autowired
    private CommentReplayService commentReplayService;

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
        PageUtils<CommentReplayEntity> page = commentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 添加回复
     */
    @PostMapping("/")
    @ApiOperation(value = "添加回复", notes = "添加回复")
    @ApiImplicitParam(name = "commentReplay", value = "commentReplay entity", required = true)
    public R save(@RequestBody CommentReplayEntity commentReplay){
        commentReplay.setCreateTime(new Date());
        commentReplay.setUpdateTime(new Date());
		commentReplayService.save(commentReplay);

        return R.ok();
    }

    /**
     * 修改回复
     */
    @PutMapping("/")
    @ApiOperation(value = "修改回复", notes = "修改回复")
    @ApiImplicitParam(name = "commentReplay", value = "commentReplay entity", required = true)
    public R update(@RequestBody CommentReplayEntity commentReplay){
        commentReplay.setUpdateTime(new Date());
		commentReplayService.updateById(commentReplay);

        return R.ok();
    }

    /**
     * 删除回复
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除回复", notes = "删除回复")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		commentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 点赞回复
     */
    @PutMapping("/like/{id}")
    @ApiOperation(value = "点赞回复", notes = "点赞回复")
    @ApiImplicitParam(name = "id", value = "回复的id", required = true)
    public R like(@PathVariable Integer id) {
        CommentReplayEntity replayEntity = commentReplayService.getById(id);
        replayEntity.setLikeNum(replayEntity.getLikeNum() + 1);
        commentReplayService.updateById(replayEntity);

        return R.ok();
    }

}
