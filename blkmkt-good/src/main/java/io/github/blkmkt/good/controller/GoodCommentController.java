package io.github.blkmkt.good.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.blkmkt.good.entity.GoodCommentEntity;
import io.github.blkmkt.good.service.GoodCommentService;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;



/**
 * 货物评论
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@RestController
@RequestMapping("good/goodcomment")
public class GoodCommentController {
    @Autowired
    private GoodCommentService goodCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		GoodCommentEntity goodComment = goodCommentService.getById(id);

        return R.ok().put("goodComment", goodComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GoodCommentEntity goodComment){
		goodCommentService.save(goodComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GoodCommentEntity goodComment){
		goodCommentService.updateById(goodComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("good:goodcomment:delete")
    public R delete(@RequestBody Integer[] ids){
		goodCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
