package io.github.issowl.authserver.controller;

import io.github.common.entity.Response;
import io.github.common.utils.ResponseUtils;
import io.github.issowl.authserver.feign.UserFeignService;
import io.github.issowl.authserver.utils.JWTUtils;
import io.github.issowl.authserver.vo.UserLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public Response login(@RequestBody UserLoginVo userLoginVo) {
        Response response = userFeignService.login(userLoginVo);
        if (response.getCode() == 200) {
            String studentId = userLoginVo.getStudentId();
            String token = JWTUtils.generateToken(studentId);
            String refreshToken = String.valueOf(UUID.randomUUID());

            redisTemplate.opsForHash().put(refreshToken, "token", token);
            redisTemplate.opsForHash().put(refreshToken, "studentId", studentId);

            redisTemplate.expire(refreshToken, JWTUtils.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        }
        return response;
    }

    @GetMapping("/refreshToken")
    public Response refreshToken(@RequestParam String refreshToken) {
        // Not allowed refresh token
        String username = (String)redisTemplate.opsForHash().get(refreshToken, "studentId");
        if(StringUtils.isEmpty(username)){
            return ResponseUtils.error(1003, "refreshToken error");
        }

        String newToken = JWTUtils.generateToken(username);
        redisTemplate.opsForHash().put(refreshToken, "token", newToken);
        return ResponseUtils.ok(newToken);
    }
}
