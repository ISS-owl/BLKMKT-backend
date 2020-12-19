package io.github.blkmkt.forum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.blkmkt.forum.entity.CommentReplayEntity;
import io.github.blkmkt.forum.entity.GoodCommentEntity;
import io.github.blkmkt.forum.feign.UserFeignService;
import io.github.blkmkt.forum.service.CommentReplayService;
import io.github.blkmkt.forum.service.GoodCommentService;
import io.github.blkmkt.forum.vo.CommentDetailsVo;
import io.github.blkmkt.forum.vo.ReplayDetailsVo;
import io.github.blkmkt.forum.vo.UserVo;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 货物评论
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-10 22:26:54
 */
@Api(tags = {"商品评论"})
@RestController
@RequestMapping("good/good_comment")
public class GoodCommentController {
    @Autowired
    private GoodCommentService goodCommentService;

    @Autowired
    private CommentReplayService commentReplayService;

    @Autowired
    private UserFeignService userFeignService;

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
        PageUtils<GoodCommentEntity> page = goodCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据商品id查询所有评论信息
     */
    @GetMapping("/{good_id}")
    @ApiOperation(value = "查询商品评论", notes = "根据商品id查询所有评论信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "good_id", value = "商品id", required = true),
        @ApiImplicitParam(name = "pageNo", value = "当前页数"),
        @ApiImplicitParam(name = "pageSize", value = "页面大小"),
        @ApiImplicitParam(name = "sort", value = "排序字段,[like_num|create_time]")
    })
    public R info(
        @PathVariable("good_id") Integer id,
        @RequestParam(value = "pageNo", required = false) Long pageNo,
        @RequestParam(value = "pageSize", required = false) Long pageSize,
        @RequestParam(value = "sort", required = false) String sort
    ) {
        List<CommentDetailsVo> commentDetails = new ArrayList<>();

        // 设置分页参数，包括排序字段和升序/降序
        PageParam commentPageParam;
        if ("like_num".equals(sort)) {
            commentPageParam = new PageParam(pageNo, pageSize, "like_num", "desc");
        } else if ("create_time".equals(sort)) {
            commentPageParam = new PageParam(pageNo, pageSize, "create_time", "asc");
        } else {
            commentPageParam = new PageParam(pageNo, pageSize);
        }
        PageUtils<GoodCommentEntity> commentEntityPage = goodCommentService.getCommentEntityByGoodId(commentPageParam, id);
        for (GoodCommentEntity goodCommentEntity : commentEntityPage.getList()) {
            CommentDetailsVo commentDetailsVo = new CommentDetailsVo();
            R userInfoInComment = userFeignService.info(goodCommentEntity.getUserId());

            // 设置用户信息
            UserVo userInComment = JSONObject.parseObject(JSON.toJSONString(userInfoInComment.get("user")), UserVo.class) ;
            commentDetailsVo.setUsername(userInComment.getName());
            commentDetailsVo.setHeadImgUrl(userInComment.getHeadImgUrl());
            commentDetailsVo.setUserId(userInComment.getId());
            // 设置评论
            commentDetailsVo.setCommentEntity(goodCommentEntity);
            // 设置回复
            List<CommentReplayEntity> replaysEntities = commentReplayService.getReplaysByCommentId(goodCommentEntity.getId());
            List<ReplayDetailsVo> replayDetailsVos = new ArrayList<>();
            for (CommentReplayEntity replayEntity : replaysEntities) {
                ReplayDetailsVo replayDetailsVo = new ReplayDetailsVo();

                R userInfoInReplay = userFeignService.info(replayEntity.getUserId());
                UserVo userInReplay = JSONObject.parseObject(JSON.toJSONString(userInfoInReplay.get("user")), UserVo.class);
                // 设置用户信息
                replayDetailsVo.setUserId(userInReplay.getId());
                replayDetailsVo.setUsername(userInReplay.getName());
                replayDetailsVo.setHeadImgUrl(userInReplay.getHeadImgUrl());
                // 设置回复
                replayDetailsVo.setCommentReplayEntity(replayEntity);

                replayDetailsVos.add(replayDetailsVo);
            }
            // 回复按照创建时间升序
            replayDetailsVos.sort(Comparator.comparing(reply -> reply.getCommentReplayEntity().getCreateTime()));
            commentDetailsVo.setReplayEntities(replayDetailsVos);

            commentDetails.add(commentDetailsVo);
        }

        PageUtils<CommentDetailsVo> commentDetailsVoPage = new PageUtils<>(
            commentDetails,
            commentEntityPage.getTotalCount(),
            commentEntityPage.getPageSize(),
            commentEntityPage.getCurrPage()
        );
        return R.ok().put("data", commentDetailsVoPage);
    }


    /**
     * 增加评论
     */
    @PostMapping("/")
    @ApiOperation(value = "增加评论", notes = "增加评论")
    @ApiImplicitParam(name = "goodComment", value = "goodComment entity", required = true)
    public R save(@RequestBody GoodCommentEntity goodComment){
        goodComment.setCreateTime(new Date());
        goodComment.setUpdateTime(new Date());
		goodCommentService.save(goodComment);

        return R.ok();
    }

    /**
     * 修改评论
     */
    @PutMapping("/")
    @ApiOperation(value = "修改评论", notes = "修改评论")
    @ApiImplicitParam(name = "goodComment", value = "goodComment entity", required = true)
    public R update(@RequestBody GoodCommentEntity goodComment){
        goodComment.setUpdateTime(new Date());
		goodCommentService.updateById(goodComment);

        return R.ok();
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除评论", notes = "删除评论，包含直接评论和对评论的回复")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Long[] ids) {
        // 删除原来的评论
		goodCommentService.removeByIds(Arrays.asList(ids));
        for (Long id : ids) {
            commentReplayService.deleteAllReplayByCommentId(id);
        }

        return R.ok();
    }

    /**
     * 点赞评论
     */
    @PutMapping("/like/{id}")
    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
    public R like(@PathVariable Integer id) {
        GoodCommentEntity commentEntity = goodCommentService.getById(id);
        commentEntity.setLikeNum(commentEntity.getLikeNum() + 1);
        goodCommentService.updateById(commentEntity);

        return R.ok();
    }

}
