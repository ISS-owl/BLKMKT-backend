package io.github.issowl.authserver.controller;

import io.github.common.entity.Response;
import io.github.issowl.authserver.constant.AuthServerConstant;
import io.github.issowl.authserver.feign.UserFeignService;
import io.github.issowl.authserver.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping("/login")
    public Response login(@RequestBody UserLoginVo userLoginVo, HttpSession session) {
        Response response = userFeignService.login(userLoginVo);
        if (response.getCode() == 200) {
            session.setAttribute(AuthServerConstant. LOGIN_USER, response);
        }
        return response;
    }
}
