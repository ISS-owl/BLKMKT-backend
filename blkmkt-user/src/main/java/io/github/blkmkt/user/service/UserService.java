package io.github.blkmkt.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.user.entity.UserEntity;


/**
 * 用户表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 19:59:03
 */
public interface UserService extends IService<UserEntity> {

    PageUtils<UserEntity> queryPage(PageParam params);
}

