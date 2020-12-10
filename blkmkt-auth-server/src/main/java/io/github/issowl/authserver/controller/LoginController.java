package io.github.issowl.authserver.controller;

import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.github.issowl.authserver.feign.UserFeignService;
import io.github.issowl.authserver.utils.JWTUtils;
import io.github.issowl.authserver.vo.RefreshTokenVo;
import io.github.issowl.authserver.vo.UserLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("auth/")
public class LoginController {
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
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

            return Objects.requireNonNull(R.ok().put("token", token)).put("refresh_token", token);
        }
        return response;
    }

    @GetMapping("/logout")
    public R logout(@RequestParam Integer studentId) {
        redisTemplate.opsForHash().delete(String.valueOf(studentId));
        return R.ok();
    }

    @PostMapping("/refreshToken")
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

        return R.ok(newToken);
    }
}
