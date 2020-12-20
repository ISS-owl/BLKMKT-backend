package io.github.blkmkt.ware.service.impl;

import io.github.blkmkt.ware.exception.NoStockException;
import io.github.blkmkt.ware.vo.WareLockOrderVo;
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

    @Override
    public Boolean orderLockStock(WareLockOrderVo wareLockOrderVo) {
        Integer goodId = wareLockOrderVo.getLockedGood().getId();
        Integer goodNum = wareLockOrderVo.getGoodNum();
        // 查询是否有库存
        Integer wareHasStockId = this.baseMapper.hasStock(goodId, goodNum);
        if (wareHasStockId == null) {
            // 没有库存
            throw new NoStockException(goodId);
        } else {
            // 利用乐观锁锁住库存
            this.baseMapper.lockStock(goodId, goodNum);
            // 记录对应订单消息
            WareOrderEntity wareOrderEntity = new WareOrderEntity();
            wareOrderEntity.setGoodId(goodId);
            wareOrderEntity.setGoodNum(goodNum);
            wareOrderEntity.setOrderId(wareLockOrderVo.getOrderId());
            wareOrderEntity.setLockStatus(1);
            this.save(wareOrderEntity);
            // TODO: 发送锁定消息至延时队列
        }

        return true;
    }

}
