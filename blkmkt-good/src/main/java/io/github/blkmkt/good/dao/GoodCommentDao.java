package io.github.blkmkt.good.dao;

import io.github.blkmkt.good.entity.GoodCommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货物评论
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@Mapper
public interface GoodCommentDao extends BaseMapper<GoodCommentEntity> {
	
}
