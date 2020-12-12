package io.github.blkmkt.good.feign;

import io.github.blkmkt.good.vo.GoodModel;
import io.github.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "blkmkt-search")
public interface ElasticSaveFeignService {
    @PostMapping("es/save/good")
    R save(@RequestBody List<GoodModel> goodModels);
}
