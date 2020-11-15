package io.github.blkmkt.order.dao;

import io.github.blkmkt.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 23:41:32
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
