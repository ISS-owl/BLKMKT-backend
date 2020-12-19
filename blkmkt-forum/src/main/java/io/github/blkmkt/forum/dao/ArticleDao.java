package io.github.blkmkt.forum.dao;

import io.github.blkmkt.forum.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-19 12:53:38
 */
@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {
	
}
