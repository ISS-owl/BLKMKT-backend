package io.github.blkmkt.forum.dao;

import io.github.blkmkt.forum.entity.GoodCommentEntity;
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
