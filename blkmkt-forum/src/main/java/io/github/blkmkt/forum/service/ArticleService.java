package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.ArticleEntity;


/**
 * 
 *
<<<<<<< HEAD
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
=======
 * @author YuNing Wu
 * @email 414085716@qq.com
>>>>>>> 6ae64d4 (feat:complete forum module)
 * @date 2020-12-19 12:53:38
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils<ArticleEntity> queryPage(PageParam params);
}

