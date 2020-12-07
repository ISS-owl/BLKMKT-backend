package io.github.blkmkt.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.good.entity.GoodCommentReplayEntity;


/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 16:12:43
 */
public interface GoodCommentReplayService extends IService<GoodCommentReplayEntity> {

    PageUtils queryPage(PageParam params);
}

