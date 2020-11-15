package io.github.blkmkt.user.dao;

import io.github.blkmkt.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 23:54:16
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
