package io.github.blkmkt.ware.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.ware.dao.WareGoodDao;
import io.github.blkmkt.ware.entity.WareGoodEntity;
import io.github.blkmkt.ware.service.WareGoodService;


@Service("wareGoodService")
public class WareGoodServiceImpl extends ServiceImpl<WareGoodDao, WareGoodEntity> implements WareGoodService {

    @Override
    public PageUtils<WareGoodEntity> queryPage(PageParam params) {
        IPage<WareGoodEntity> page = this.page(
                new Query<WareGoodEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

}