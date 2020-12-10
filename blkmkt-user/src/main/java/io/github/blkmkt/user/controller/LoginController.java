package io.github.blkmkt.user.controller;

import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.exception.PhoneNumExistException;
import io.github.blkmkt.user.exception.UserExistException;
import io.github.blkmkt.user.service.LoginService;
import io.github.blkmkt.user.vo.RegisterVo;
import io.github.blkmkt.user.vo.UserLoginVo;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"用户登录与注册"})
@RestController
@RequestMapping("user/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "给定学号和密码进行登录")
    @ApiImplicitParam(name = "userLoginVo", value = "用户登录所需信息的实体")
    public R login(@RequestBody UserLoginVo userLoginVo) {
        UserEntity userEntity = loginService.login(userLoginVo);
        if (userEntity != null) {
            return R.ok().put("user", userEntity);
        } else {
            return R.error(
                BizCodeEnum.LOGIN_PASSWORD_EXCEPTION.getCode(),
                BizCodeEnum.LOGIN_PASSWORD_EXCEPTION.getMsg()
            );
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "给定注册信息进行注册")
    @ApiImplicitParam(name = "registerVo", value = "用户注册所需信息的实体")
    public R register(@Validated @RequestBody RegisterVo registerVo) {
        try {
            loginService.register(registerVo);
        } catch (UserExistException userExistException) {
            return R.error(
                BizCodeEnum.USER_EXIST_EXCEPTION.getCode(),
                BizCodeEnum.USER_EXIST_EXCEPTION.getMsg()
            );
        } catch (PhoneNumExistException phoneNumExistException) {
            return R.error(
                BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),
                BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg()
            );
        }
        return R.ok();
    }
}
