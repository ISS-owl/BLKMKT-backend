package io.github.blkmkt.forum.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.ReplyEntity;
import io.github.blkmkt.forum.service.ReplyService;
import io.github.common.utils.PageUtils;



/**
 * 
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-19 12:53:38
 */
@Api(tags = {""})
@RestController
@RequestMapping("forum/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

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
        PageUtils<ReplyEntity> page = replyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		ReplyEntity reply = replyService.getById(id);

        return R.ok().put("reply", reply);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "reply", value = "reply entity", required = true)
    public R save(@RequestBody ReplyEntity reply){
		replyService.save(reply);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "reply", value = "reply entity", required = true)
    public R update(@RequestBody ReplyEntity reply){
		replyService.updateById(reply);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		replyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @GetMapping("/AllReplyFromArticle/{article_id}")
    @ApiOperation(value = "信息", notes = "获取某个文章的所有评论信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "article_id", value = "文章id", required = true),
        @ApiImplicitParam(name = "pageNo", value = "当前页数"),
        @ApiImplicitParam(name = "pageSize", value = "页面大小")
    })
    public R getReplyByArticleId(
        @PathVariable("article_id") Integer id,
        @RequestParam Long pageNo,
        @RequestParam Long pageSize
    ) {
        List<ReplyEntity> replyDetails = new ArrayList<>();

        PageParam PageParam = new PageParam(pageNo, pageSize);
        PageUtils<ReplyEntity> replyEntityPage = replyService.getReplyEntityByArticleId(PageParam, id);

        List<ReplyEntity> replyEntities = replyEntityPage.getList();
        replyEntities.sort(Comparator.comparing(reply -> reply.getDate()));

        return R.ok().put("data", replyEntities);
    }

    @GetMapping("getDiscuss/{id}")
    @ApiOperation(value = "上下文评论列表", notes = "获取指定id的评论相关的所有评论")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R getDiscuss(@PathVariable("id") Integer id){
        List<ReplyEntity> replyEntities = replyService.getDiscuss(id);

        return R.ok().put("discuss", replyEntities);
    }

    @PutMapping("/like/{id}")
    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
    public R like(@PathVariable Integer id) {
        ReplyEntity replyEntity = replyService.getById(id);
        replyEntity.setLikeNum(replyEntity.getLikeNum()+1);;
        replyService.updateById(replyEntity);
        return R.ok();
    }

    @PutMapping("/undo-like/{id}")
    @ApiOperation(value = "取消点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
    public R undoLike(@PathVariable Integer id) {
        ReplyEntity replyEntity = replyService.getById(id);
        replyEntity.setLikeNum(replyEntity.getLikeNum()-1);;
        replyService.updateById(replyEntity);
        return R.ok();
    }

}
