package io.github.blkmkt.good.service.impl;

import io.github.blkmkt.good.entity.GoodCommentEntity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.good.dao.GoodCommentReplayDao;
import io.github.blkmkt.good.entity.GoodCommentReplayEntity;
import io.github.blkmkt.good.service.GoodCommentReplayService;


@Service("goodCommentReplayService")
public class GoodCommentReplayServiceImpl extends ServiceImpl<GoodCommentReplayDao, GoodCommentReplayEntity> implements GoodCommentReplayService {

    @Override
    public PageUtils<GoodCommentReplayEntity> queryPage(PageParam params) {
        IPage<GoodCommentReplayEntity> page = this.page(
                new Query<GoodCommentReplayEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

}