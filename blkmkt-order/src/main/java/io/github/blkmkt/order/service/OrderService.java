package io.github.blkmkt.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.order.entity.OrderEntity;


/**
 * 订单表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 20:22:03
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils<OrderEntity> queryPage(PageParam params);

    void closeOrder(OrderEntity orderEntity);
}

