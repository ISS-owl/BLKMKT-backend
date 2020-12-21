package io.github.blkmkt.order.service.impl;

import io.github.blkmkt.order.enums.OrderStatusEnum;
import io.github.common.to.OrderTo;
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

import io.github.blkmkt.order.dao.OrderDao;
import io.github.blkmkt.order.entity.OrderEntity;
import io.github.blkmkt.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils<OrderEntity> queryPage(PageParam params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public void closeOrder(OrderEntity orderEntity) {
        OrderEntity currentOrderEntity = this.getById(orderEntity.getId());
        if (currentOrderEntity.getStatus().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
            // 将订单设置为取消状态
            OrderEntity newOrderEntity = new OrderEntity();
            newOrderEntity.setId(currentOrderEntity.getId());
            newOrderEntity.setStatus(OrderStatusEnum.CANCLED.getCode());
            this.updateById(newOrderEntity);
            // 用消息队列通知其它服务进行关单的操作
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(currentOrderEntity, orderTo);
            rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);
        }
    }

}
