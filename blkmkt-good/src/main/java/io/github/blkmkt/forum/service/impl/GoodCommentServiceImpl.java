package io.github.blkmkt.forum.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.GoodCommentDao;
import io.github.blkmkt.forum.entity.GoodCommentEntity;
import io.github.blkmkt.forum.service.GoodCommentService;

@Service("goodCommentService")
public class GoodCommentServiceImpl extends ServiceImpl<GoodCommentDao, GoodCommentEntity> implements GoodCommentService {

    @Override
    public PageUtils<GoodCommentEntity> queryPage(PageParam params) {
        IPage<GoodCommentEntity> page = this.page(
                new Query<GoodCommentEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public PageUtils<GoodCommentEntity> getCommentEntityByGoodId(PageParam params, Integer id) {
        IPage<GoodCommentEntity> page = this.page(
            new Query<GoodCommentEntity>().getPage(params),
            new QueryWrapper<GoodCommentEntity>().eq("good_id", id)
        );

        return new PageUtils<>(page);
    }

}
