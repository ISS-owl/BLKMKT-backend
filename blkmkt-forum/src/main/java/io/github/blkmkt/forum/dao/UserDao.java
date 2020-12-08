package io.github.blkmkt.forum.dao;

import io.github.blkmkt.forum.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
@Component(value ="userMapper")
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
