package io.github.blkmkt.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.user.entity.UserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 23:54:16
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

