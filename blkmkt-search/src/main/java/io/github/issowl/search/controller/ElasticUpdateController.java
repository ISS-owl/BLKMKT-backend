package io.github.issowl.search.controller;

import io.github.common.exception.BizCodeEnum;
import io.github.common.to.es.model.GoodModel;
import io.github.common.utils.R;
import io.github.issowl.search.service.ElasticUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "es/update")
public class ElasticUpdateController {
    @Autowired
    private ElasticUpdateService elasticUpdateService;

    @PutMapping("/good")
    public R update(@RequestBody List<GoodModel> goodModelList) {
        boolean status = false;
        try {
            status = elasticUpdateService.updateGoodAsIndices(goodModelList);
        } catch (Exception e) {
            log.error("远程更新索引失败");
        }
        if (status){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PRODUCT_UPDATE_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UPDATE_EXCEPTION.getMsg());
        }
    }
}
