package io.github.blkmkt.good.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import io.github.common.utils.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.good.entity.GoodCommentEntity;
import io.github.blkmkt.good.service.GoodCommentService;
import io.github.common.utils.PageUtils;



/**
 * 货物评论
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 19:53:23
 */
@Api(tags = {"商品评论"})
@RestController
@RequestMapping("good/good_comment")
public class GoodCommentController {
    @Autowired
    private GoodCommentService goodCommentService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public ResponseWithData<PageUtils<GoodCommentEntity>> list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<GoodCommentEntity> page = goodCommentService.queryPage(params);

        return ResponseUtils.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public ResponseWithData<GoodCommentEntity> info(@PathVariable("id") Integer id){
		GoodCommentEntity goodComment = goodCommentService.getById(id);

        return ResponseUtils.ok(goodComment);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "goodComment", value = "goodComment entity", required = true)
    public Response save(@RequestBody GoodCommentEntity goodComment){
		goodCommentService.save(goodComment);

        return ResponseUtils.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "goodComment", value = "goodComment entity", required = true)
    public Response update(@RequestBody GoodCommentEntity goodComment){
		goodCommentService.updateById(goodComment);

        return ResponseUtils.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public Response delete(@RequestBody Integer[] ids){
		goodCommentService.removeByIds(Arrays.asList(ids));

        return ResponseUtils.ok();
    }

}
