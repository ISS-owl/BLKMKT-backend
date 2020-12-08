package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.blkmkt.forum.entity.ReplyEntity;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.ArticleEntity;

import java.util.List;

/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils<ArticleEntity> queryPage(PageParam params);

    List<ReplyEntity> getAllReply(int articleId);
}

