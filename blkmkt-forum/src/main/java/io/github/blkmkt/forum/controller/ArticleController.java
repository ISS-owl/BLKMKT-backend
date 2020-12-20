package io.github.blkmkt.forum.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.ArticleEntity;
import io.github.blkmkt.forum.service.ArticleService;
import io.github.common.utils.PageUtils;


/*
 * @author YuNing Wu
 * @email 414085716@qq.com
 * @date 2020-12-19 12:53:38
 */
@Api(tags = {"文章"})
@RestController
@RequestMapping("forum/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

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
        PageUtils<ArticleEntity> page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("hot/list")
    @ApiOperation(value = "按点赞数排序所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
        @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public R hotList(
        @RequestParam(value = "pageNo", required = false) Long pageNo,
        @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, "like_num", "desc");
        PageUtils<ArticleEntity> page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		ArticleEntity article = articleService.getById(id);

        return R.ok().put("article", article);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "article", value = "article entity", required = true)
    public R save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "article", value = "article entity", required = true)
    public R update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PutMapping("/like/{id}")
    @ApiOperation(value = "点赞文章", notes = "点赞文章")
    @ApiImplicitParam(name = "id", value = "文章的id", required = true)
    public R like(@PathVariable Integer id) {
        ArticleEntity articleEntity = articleService.getById(id);
        articleEntity.setLikeNum(articleEntity.getLikeNum() + 1);
        articleService.updateById(articleEntity);

        return R.ok();
    }

    @PutMapping("/undo-like/{id}")
    @ApiOperation(value = "取消点赞文章", notes = "取消点赞文章")
    @ApiImplicitParam(name = "id", value = "文章的id", required = true)
    public R undoLike(@PathVariable Integer id) {
        ArticleEntity articleEntity = articleService.getById(id);
        articleEntity.setLikeNum(articleEntity.getLikeNum() - 1);
        articleService.updateById(articleEntity);

        return R.ok();
    }

    @PutMapping("/dislike/{id}")
    @ApiOperation(value = "点踩文章", notes = "点踩文章")
    @ApiImplicitParam(name = "id", value = "文章的id", required = true)
    public R dislike(@PathVariable Integer id) {
        ArticleEntity articleEntity = articleService.getById(id);
        articleEntity.setLikeNum(articleEntity.getDislikeNum() + 1);
        articleService.updateById(articleEntity);

        return R.ok();
    }

    @PutMapping("/undo-dislike/{id}")
    @ApiOperation(value = "取消点踩文章", notes = "取消点踩文章")
    @ApiImplicitParam(name = "id", value = "文章的id", required = true)
    public R undoDislike(@PathVariable Integer id) {
        ArticleEntity articleEntity = articleService.getById(id);
        articleEntity.setLikeNum(articleEntity.getDislikeNum() - 1);
        articleService.updateById(articleEntity);

        return R.ok();
    }



}
