package io.github.blkmkt.ware.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.ware.dao.WareOrderDao;
import io.github.blkmkt.ware.entity.WareOrderEntity;
import io.github.blkmkt.ware.service.WareOrderService;


@Service("wareOrderService")
public class WareOrderServiceImpl extends ServiceImpl<WareOrderDao, WareOrderEntity> implements WareOrderService {

    @Override
    public PageUtils<WareOrderEntity> queryPage(PageParam params) {
        IPage<WareOrderEntity> page = this.page(
                new Query<WareOrderEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

}