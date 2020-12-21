package io.github.blkmkt.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import io.github.blkmkt.ware.enums.OrderStatusEnum;
import io.github.blkmkt.ware.exception.NoStockException;
import io.github.blkmkt.ware.feign.OrderFeignService;
import io.github.common.to.OrderTo;
import io.github.common.to.StockLockedTo;
import io.github.blkmkt.ware.vo.WareLockOrderVo;
import io.github.common.utils.R;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils<WareOrderEntity> queryPage(PageParam params) {
        IPage<WareOrderEntity> page = this.page(
                new Query<WareOrderEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public void orderLockStock(WareLockOrderVo wareLockOrderVo) {
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
            // 发送锁定消息至延时队列
            StockLockedTo stockLockedTo = new StockLockedTo();
            BeanUtils.copyProperties(wareOrderEntity, stockLockedTo);
            rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked", stockLockedTo);
        }
    }

    @Override
    public void unlock(StockLockedTo stockLockedTo) {
        Integer id = stockLockedTo.getId();
        WareOrderEntity wareOrderEntity = this.baseMapper.selectById(id);
        // 释放仍然存在的订单
        if (wareOrderEntity != null) {
            R orderInfo = orderFeignService.info(stockLockedTo.getOrderId());
            if ((int)orderInfo.get("code") == 200) {
                OrderTo order = orderInfo.getData("order", new TypeReference<>() {});
                if (order == null || order.getStatus().equals(OrderStatusEnum.CANCLED.getCode())) {
                    if (wareOrderEntity.getLockStatus() == 1) { // 上锁状态
                        unlockStock(wareOrderEntity.getGoodId(), wareOrderEntity.getGoodNum(), wareOrderEntity.getId());
                    }
                }
            }
        }
    }


    @Override
    public void unlock(OrderTo orderTo) {
        Integer orderId = orderTo.getId();
        WareOrderEntity wareOrderEntity = this.baseMapper.selectOne(new QueryWrapper<WareOrderEntity>().eq("order_id", orderId));

        if (wareOrderEntity.getLockStatus() == 1) {
            unlockStock(wareOrderEntity.getGoodId(), wareOrderEntity.getGoodNum(), wareOrderEntity.getId());
        }
    }

    private void unlockStock(Integer goodId, Integer goodNum, Integer taskId) {
        this.baseMapper.unlockStock(goodId, goodNum);

        WareOrderEntity wareOrderEntity = new WareOrderEntity();
        wareOrderEntity.setId(taskId);
        wareOrderEntity.setLockStatus(2);
        this.updateById(wareOrderEntity);
    }

}
