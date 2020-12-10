package io.github.issowl.authserver.controller;

import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.github.issowl.authserver.feign.UserFeignService;
import io.github.issowl.authserver.utils.JWTUtils;
import io.github.issowl.authserver.vo.RefreshTokenVo;
import io.github.issowl.authserver.vo.RegisterVo;
import io.github.issowl.authserver.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Api(tags = {"认证"})
@RestController
@RequestMapping("auth/")
public class LoginController {
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "给定学号和密码进行登录")
    @ApiImplicitParam(name = "userLoginVo", value = "用户登录所需信息的实体")
    public R login(@RequestBody UserLoginVo userLoginVo) {
        R response = userFeignService.login(userLoginVo);
        if ((int)response.get("code") == 200) {
            // 保存在redis
            String studentId = userLoginVo.getStudentId();
            String token = JWTUtils.generateToken(studentId);
            String refreshToken = UUID.randomUUID().toString().replace("-", "");

            redisTemplate.opsForHash().put(studentId, "token", token);
            redisTemplate.opsForHash().put(studentId, "refresh_token", refreshToken);

            redisTemplate.expire(studentId, JWTUtils.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            return Objects.requireNonNull(response.put("token", token)).put("refresh_token", token);
        }
        return response;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "给定足够的信息进行登录")
    @ApiImplicitParam(name = "registerVo", value = "用户注册所需信息的实体")
    public R register(@RequestBody RegisterVo registerVo) {
        return userFeignService.register(registerVo);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出指定studentId的用户")
    @ApiImplicitParam(name = "studentId", value = "用户学号")
    public R logout(@RequestParam Integer studentId) {
        redisTemplate.opsForHash().delete(String.valueOf(studentId));
        return R.ok();
    }

    @PostMapping("/refreshToken")
    @ApiOperation(value = "刷新token", notes = "在token快要过期时刷新token")
    @ApiImplicitParam(name = "refreshTokenVo", value = "refreshToken和学号")
    public R refreshToken(@RequestBody RefreshTokenVo refreshTokenVo) {
        String studentId = refreshTokenVo.getStudentId();
        String refreshToken = refreshTokenVo.getRefreshToken();

        String oldRefreshToken = (String)redisTemplate.opsForHash().get(studentId, "refresh_token");
        if(StringUtils.isEmpty(oldRefreshToken) || !refreshToken.equals(oldRefreshToken)){
            return R.error(BizCodeEnum.REFRESH_TOKEN_INVALID.getCode(), BizCodeEnum.REFRESH_TOKEN_INVALID.getMsg());
        }

        String newToken = JWTUtils.generateToken(studentId);
        redisTemplate.opsForHash().put(studentId, "token", newToken);
        redisTemplate.expire(studentId, JWTUtils.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return R.ok().put("token", newToken);
    }
}
