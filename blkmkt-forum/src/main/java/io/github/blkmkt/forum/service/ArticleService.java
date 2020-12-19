package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.ArticleEntity;


/**
 * 
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-19 12:53:38
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils<ArticleEntity> queryPage(PageParam params);
}

