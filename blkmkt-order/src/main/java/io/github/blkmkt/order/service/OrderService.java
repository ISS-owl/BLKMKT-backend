package io.github.blkmkt.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 23:41:32
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

