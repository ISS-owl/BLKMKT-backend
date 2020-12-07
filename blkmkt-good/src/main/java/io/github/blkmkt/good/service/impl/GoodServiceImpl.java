package io.github.blkmkt.good.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.good.dao.GoodDao;
import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;


@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodDao, GoodEntity> implements GoodService {

    @Override
    public PageUtils queryPage(PageParam params) {
        IPage<GoodEntity> page = this.page(
                new Query<GoodEntity>().getPage(params),
                new QueryWrapper<GoodEntity>()
        );

        return new PageUtils(page);
    }

}