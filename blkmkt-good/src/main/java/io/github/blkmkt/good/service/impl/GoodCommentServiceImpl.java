package io.github.blkmkt.good.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;

import io.github.blkmkt.good.dao.GoodCommentDao;
import io.github.blkmkt.good.entity.GoodCommentEntity;
import io.github.blkmkt.good.service.GoodCommentService;


@Service("goodCommentService")
public class GoodCommentServiceImpl extends ServiceImpl<GoodCommentDao, GoodCommentEntity> implements GoodCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodCommentEntity> page = this.page(
                new Query<GoodCommentEntity>().getPage(params),
                new QueryWrapper<GoodCommentEntity>()
        );

        return new PageUtils(page);
    }

}