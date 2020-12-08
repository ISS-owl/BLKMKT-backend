package io.github.blkmkt.forum.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.github.blkmkt.forum.entity.ReplyEntity;
import io.github.blkmkt.forum.entity.UserEntity;
import io.github.blkmkt.forum.util.PermissionUtil;
import io.github.common.entity.PageParam;
import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import io.github.common.utils.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.ArticleEntity;
import io.github.blkmkt.forum.service.ArticleService;
import io.github.common.utils.PageUtils;

import javax.servlet.http.HttpSession;

/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
@Api(tags = {""})
@RestController
@RequestMapping("order/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    PermissionUtil permissionUtil;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public ResponseWithData<PageUtils<ArticleEntity>> list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<ArticleEntity> page = articleService.queryPage(params);

        return ResponseUtils.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public ResponseWithData<ArticleEntity> info(@PathVariable("id") Integer id){
		ArticleEntity article = articleService.getById(id);

        return ResponseUtils.ok(article);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "article", value = "article entity", required = true)
    public Response save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return ResponseUtils.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "article", value = "article entity", required = true)
    public Response update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return ResponseUtils.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public Response delete(@RequestBody Integer[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return ResponseUtils.ok();
    }

    /**
    @RequestMapping("/addReply")
    @ResponseBody
    public String addReply(@RequestParam("articleId")int articleId,
                            @RequestParam("reply_content")String reply_content,
                            HttpSession session){
        Date date = new Date();
        UserEntity user = (UserEntity) session.getAttribute("user");

        if(user==null){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"尚未登陆！\"}";
            return jsonStr;
        }
        if(!(license.hasArticlePer))
    }
                            **/

    @RequestMapping("/getAllReply")
    @ResponseBody
    public Response addReply(@RequestParam("articleId")int articleId){
        List<ReplyEntity> replyEntities = articleService.getAllReply(articleId);

        return ResponseUtils.ok(replyEntities);
    }


    @RequestMapping("/test")
    @ResponseBody
    public Response testPer(@RequestParam("uid")int uid,@RequestParam("pname")String pname){
        boolean status = permissionUtil.hasPermission(pname,uid);
        return ResponseUtils.ok(status);
    }

}
