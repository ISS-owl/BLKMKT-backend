package io.github.issowl.authserver.feign;

import io.github.common.entity.Response;
import io.github.issowl.authserver.vo.RegisterVo;
import io.github.issowl.authserver.vo.UserLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "blkmkt-user")
public interface UserFeignService {
    @PostMapping("user/login")
    public Response login(@RequestBody UserLoginVo userLoginVo);

    @PostMapping("user/register")
    public Response register(@RequestBody RegisterVo registerVo);

}
