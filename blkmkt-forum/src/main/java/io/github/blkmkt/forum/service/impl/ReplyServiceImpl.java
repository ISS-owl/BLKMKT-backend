package io.github.blkmkt.forum.service.impl;

import io.github.blkmkt.forum.vo.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.ReplyDao;
import io.github.blkmkt.forum.entity.ReplyEntity;
import io.github.blkmkt.forum.service.ReplyService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, ReplyEntity> implements ReplyService {

    @Autowired
    ReplyDao replyDao;

    @Override
    public PageUtils<ReplyEntity> queryPage(PageParam params) {
        IPage<ReplyEntity> page = this.page(
                new Query<ReplyEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    public PageUtils<ReplyEntity> getReplyEntityByArticleId(PageParam param, Integer id){
        IPage<ReplyEntity> page = this.page(
            new Query<ReplyEntity>().getPage(param),
            new QueryWrapper<ReplyEntity>().eq("article_id",id)
        );
        return new PageUtils<>(page);
    }

    public PageUtils<ReplyEntity> getDiscuss(PageParam param, Integer id){
        Discuss discuss = new Discuss();
        ReplyEntity replyEntity = replyDao.selectById(id);
        int firstid = replyEntity.getFirstId();
        if(firstid==0){
            return null;
        }
        IPage<ReplyEntity> page = this.page(
            new Query<ReplyEntity>().getPage(param),
            new QueryWrapper<ReplyEntity>().eq("first_id",firstid).or().eq("id",firstid)
        );
        return new PageUtils<>(page);
    }

}
