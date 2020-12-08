package io.github.blkmkt.user.controller;

import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.exception.PhoneNumExistException;
import io.github.blkmkt.user.exception.UserExistException;
import io.github.blkmkt.user.service.LoginService;
import io.github.blkmkt.user.vo.RegisterVo;
import io.github.blkmkt.user.vo.UserLoginVo;
import io.github.common.entity.Response;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Response login(@RequestBody UserLoginVo userLoginVo) {
        UserEntity userEntity = loginService.login(userLoginVo);
        if (userEntity != null) {
            return ResponseUtils.ok(userEntity);
        } else {
            return ResponseUtils.error(
                BizCodeEnum.LOGIN_PASSWORD_EXCEPTION.getCode(),
                BizCodeEnum.LOGIN_PASSWORD_EXCEPTION.getMsg()
            );
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody RegisterVo registerVo) {
        try {
            loginService.register(registerVo);
        } catch (UserExistException userExistException) {
            return ResponseUtils.error(
                BizCodeEnum.USER_EXIST_EXCEPTION.getCode(),
                BizCodeEnum.USER_EXIST_EXCEPTION.getMsg()
            );
        } catch (PhoneNumExistException phoneNumExistException) {
            return ResponseUtils.error(
                BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),
                BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg()
            );
        }
        return ResponseUtils.ok();
    }
}
