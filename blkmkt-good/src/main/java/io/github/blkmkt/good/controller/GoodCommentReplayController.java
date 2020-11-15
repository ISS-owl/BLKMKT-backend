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

import io.github.blkmkt.good.entity.GoodCommentReplayEntity;
import io.github.blkmkt.good.service.GoodCommentReplayService;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;



/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@RestController
@RequestMapping("good/goodcommentreplay")
public class GoodCommentReplayController {
    @Autowired
    private GoodCommentReplayService goodCommentReplayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("good:goodcommentreplay:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodCommentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("good:goodcommentreplay:info")
    public R info(@PathVariable("id") Long id){
		GoodCommentReplayEntity goodCommentReplay = goodCommentReplayService.getById(id);

        return R.ok().put("goodCommentReplay", goodCommentReplay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("good:goodcommentreplay:save")
    public R save(@RequestBody GoodCommentReplayEntity goodCommentReplay){
		goodCommentReplayService.save(goodCommentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("good:goodcommentreplay:update")
    public R update(@RequestBody GoodCommentReplayEntity goodCommentReplay){
		goodCommentReplayService.updateById(goodCommentReplay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("good:goodcommentreplay:delete")
    public R delete(@RequestBody Long[] ids){
		goodCommentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
