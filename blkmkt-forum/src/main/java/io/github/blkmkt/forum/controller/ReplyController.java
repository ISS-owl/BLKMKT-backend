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
<<<<<<< HEAD
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-19 12:53:38
 */
@Api(tags = {""})
=======
 * @author YuNing Wu
 * @email 414085716@qq.com
 * @date 2020-12-19 12:53:38
 */
@Api(tags = {"回复"})
>>>>>>> 6ae64d4 (feat:complete forum module)
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
<<<<<<< HEAD
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
=======
    @ApiOperation(value = "获取指定id的回复信息", notes = "获取指定id的回复信息")
>>>>>>> 6ae64d4 (feat:complete forum module)
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		ReplyEntity reply = replyService.getById(id);

        return R.ok().put("reply", reply);
    }

    /**
     * 保存
     */
    @PostMapping("/")
<<<<<<< HEAD
    @ApiOperation(value = "保存信息", notes = "保存信息")
=======
    @ApiOperation(value = "保存回复", notes = "保存回复")
>>>>>>> 6ae64d4 (feat:complete forum module)
    @ApiImplicitParam(name = "reply", value = "reply entity", required = true)
    public R save(@RequestBody ReplyEntity reply){
        int pre = reply.getPreId();
        ReplyEntity preR = replyService.getById(pre);
        if(preR.getPreId()==0)reply.setFirstId(preR.getId());
        else reply.setFirstId(preR.getFirstId());
		replyService.save(reply);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
<<<<<<< HEAD
    @ApiOperation(value = "更新信息", notes = "更新信息")
=======
    @ApiOperation(value = "更新回复", notes = "更新回复")
>>>>>>> 6ae64d4 (feat:complete forum module)
    @ApiImplicitParam(name = "reply", value = "reply entity", required = true)
    public R update(@RequestBody ReplyEntity reply){
		replyService.updateById(reply);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
<<<<<<< HEAD
    @ApiOperation(value = "删除", notes = "删除信息")
=======
    @ApiOperation(value = "删除回复", notes = "删除回复")
>>>>>>> 6ae64d4 (feat:complete forum module)
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		replyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


<<<<<<< HEAD
    @GetMapping("/AllReplyFromArticle/{article_id}")
    @ApiOperation(value = "信息", notes = "获取某个文章的所有评论信息")
=======
    @GetMapping("/fromArticle/{article_id}")
    @ApiOperation(value = "获取某个文章的所有回复信息", notes = "获取某个文章的所有回复信息")
>>>>>>> 6ae64d4 (feat:complete forum module)
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
<<<<<<< HEAD
        List<ReplyEntity> replyDetails = new ArrayList<>();
=======
>>>>>>> 6ae64d4 (feat:complete forum module)

        PageParam PageParam = new PageParam(pageNo, pageSize);
        PageUtils<ReplyEntity> replyEntityPage = replyService.getReplyEntityByArticleId(PageParam, id);

        List<ReplyEntity> replyEntities = replyEntityPage.getList();
        replyEntities.sort(Comparator.comparing(reply -> reply.getDate()));

        return R.ok().put("data", replyEntities);
    }

    @GetMapping("getDiscuss/{id}")
<<<<<<< HEAD
    @ApiOperation(value = "上下文评论列表", notes = "获取指定id的评论相关的所有评论")
    @ApiImplicitParam(name = "id", value = "id", required = true)
=======
    @ApiOperation(value = "回复上下文", notes = "获取指定id的回复相关的所有回复")
    @ApiImplicitParam(name = "id", value = "回复id", required = true)
>>>>>>> 6ae64d4 (feat:complete forum module)
    public R getDiscuss(@PathVariable("id") Integer id){
        List<ReplyEntity> replyEntities = replyService.getDiscuss(id);

        return R.ok().put("discuss", replyEntities);
    }

    @PutMapping("/like/{id}")
<<<<<<< HEAD
    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
=======
    @ApiOperation(value = "点赞回复", notes = "点赞回复")
    @ApiImplicitParam(name = "id", value = "回复id", required = true)
>>>>>>> 6ae64d4 (feat:complete forum module)
    public R like(@PathVariable Integer id) {
        ReplyEntity replyEntity = replyService.getById(id);
        replyEntity.setLikeNum(replyEntity.getLikeNum()+1);;
        replyService.updateById(replyEntity);
        return R.ok();
    }

    @PutMapping("/undo-like/{id}")
<<<<<<< HEAD
    @ApiOperation(value = "取消点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
=======
    @ApiOperation(value = "取消点赞回复", notes = "点赞回复")
    @ApiImplicitParam(name = "id", value = "回复id", required = true)
>>>>>>> 6ae64d4 (feat:complete forum module)
    public R undoLike(@PathVariable Integer id) {
        ReplyEntity replyEntity = replyService.getById(id);
        replyEntity.setLikeNum(replyEntity.getLikeNum()-1);
        replyService.updateById(replyEntity);
        return R.ok();
    }

}
