package io.github.blkmkt.user.service;

import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.vo.RegisterVo;
import io.github.blkmkt.user.vo.UserLoginVo;

/**
 * 用户登录
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-08 9:10:03
 */
public interface LoginService {
    UserEntity login(UserLoginVo loginVo);

    void register(RegisterVo registerVo);
}
