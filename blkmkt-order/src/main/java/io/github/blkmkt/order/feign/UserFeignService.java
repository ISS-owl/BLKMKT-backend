package io.github.blkmkt.order.feign;

import io.github.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "blkmkt-user")
public interface UserFeignService {
    @GetMapping("/user/user/{id}")
    R info(@PathVariable("id") Integer id);

}
