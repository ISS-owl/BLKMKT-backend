package io.github.blkmkt.forum.service.impl;

import io.github.blkmkt.forum.dao.ReplyDao;
import io.github.blkmkt.forum.entity.ReplyEntity;
import io.github.blkmkt.forum.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.ArticleDao;
import io.github.blkmkt.forum.entity.ArticleEntity;
import io.github.blkmkt.forum.service.ArticleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {
    @Autowired
    ReplyDao replyDao;

    @Override
    public PageUtils<ArticleEntity> queryPage(PageParam params) {
        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    public List<ReplyEntity> getAllReply(int articleId){
        Map<String,Object> condition = new HashMap<>();
        condition.put("article_Id",articleId);
        List<ReplyEntity> replyList = replyDao.selectByMap(condition);
        return replyList;
    }

}