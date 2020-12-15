package io.github.blkmkt.good.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.blkmkt.good.entity.CommentReplayEntity;
import io.github.blkmkt.good.entity.GoodCommentEntity;
import io.github.blkmkt.good.feign.UserFeignService;
import io.github.blkmkt.good.service.CommentReplayService;
import io.github.blkmkt.good.service.GoodCommentService;
import io.github.blkmkt.good.vo.CommentDetailsVo;
import io.github.blkmkt.good.vo.ReplayDetailsVo;
import io.github.blkmkt.good.vo.UserVo;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
        PageParam params = new PageParam(pageNo, pageSize, null, null);
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
        @ApiImplicitParam(name = "sort", value = "排序字段,[like|createTime|UpdateTime]_[asc|desc]", required = false)
    })
    public R info(
        @PathVariable("good_id") Integer id,
        @RequestParam(value = "sort", required = false) String sort
    ) {
        List<CommentDetailsVo> commentDetails = new ArrayList<>();
        List<GoodCommentEntity> commentEntity = goodCommentService.getCommentEntityByGoodId(id);
        for (GoodCommentEntity goodCommentEntity : commentEntity) {
            CommentDetailsVo commentDetailsVo = new CommentDetailsVo();
            R userInfoInComment = userFeignService.info(goodCommentEntity.getUserId());

            // 设置用户信息
            UserVo userInComment = JSONObject.parseObject(JSON.toJSONString(userInfoInComment.get("user")), UserVo.class) ;
            commentDetailsVo.setUsername(userInComment.getName());
            commentDetailsVo.setHeadImgUrl(userInComment.getHeadImgUrl());
            commentDetailsVo.setUserId(userInComment.getId());
            // 设置评论及回复
            commentDetailsVo.setCommentEntity(goodCommentEntity);
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
            // 回复按照创建时间排序
            replayDetailsVos.sort(Comparator.comparingLong(replay -> replay.getCommentReplayEntity().getCreateTime().getTime()));
            commentDetailsVo.setReplayEntities(replayDetailsVos);

            commentDetails.add(commentDetailsVo);
        }
//        // 给评论排序
//        if (StringUtils.isEmpty(sort) || "")

        return R.ok().put("data", commentDetails);
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
    @PostMapping("/{id}")
    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    @ApiImplicitParam(name = "id", value = "评论的id", required = true)
    public R like(@PathVariable Integer id) {
        GoodCommentEntity commentEntity = goodCommentService.getById(id);
        commentEntity.setLikeNum(commentEntity.getLikeNum() + 1);
        goodCommentService.updateById(commentEntity);

        return R.ok();
    }

}
