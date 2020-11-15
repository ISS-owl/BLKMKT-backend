package io.github.blkmkt.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.good.entity.GoodCommentEntity;

import java.util.Map;

/**
 * 货物评论
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
public interface GoodCommentService extends IService<GoodCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

