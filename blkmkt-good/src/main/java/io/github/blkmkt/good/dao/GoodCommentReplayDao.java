package io.github.blkmkt.good.dao;

import io.github.blkmkt.good.entity.GoodCommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对评价的回复
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@Mapper
public interface GoodCommentReplayDao extends BaseMapper<GoodCommentReplayEntity> {
	
}
