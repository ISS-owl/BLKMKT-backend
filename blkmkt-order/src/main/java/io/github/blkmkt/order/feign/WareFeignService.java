package io.github.blkmkt.order.feign;

import io.github.blkmkt.order.vo.WareLockOrderVo;
import io.github.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "blkmkt-ware")
public interface WareFeignService {
    @PostMapping("ware/ware_order/lock")
    R lockOrder(@RequestBody WareLockOrderVo wareLockOrderVo);
}
