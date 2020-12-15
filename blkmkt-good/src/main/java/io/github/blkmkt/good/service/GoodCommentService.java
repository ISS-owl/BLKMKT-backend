package io.github.blkmkt.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.good.entity.GoodCommentEntity;

import java.util.List;


/**
 * 货物评论
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 16:12:43
 */
public interface GoodCommentService extends IService<GoodCommentEntity> {

    PageUtils<GoodCommentEntity> queryPage(PageParam params);

    PageUtils<GoodCommentEntity> getCommentEntityByGoodId(PageParam param, Integer id);
}

