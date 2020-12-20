package io.github.blkmkt.ware.dao;

import io.github.blkmkt.ware.entity.WareOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品订单信息
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
@Mapper
public interface WareOrderDao extends BaseMapper<WareOrderEntity> {
	
}
