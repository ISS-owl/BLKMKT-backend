package io.github.blkmkt.forum.dao;

import io.github.blkmkt.forum.entity.GoodEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货物表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@Mapper
public interface GoodDao extends BaseMapper<GoodEntity> {
	
}
