package io.github.issowl.search.controller;

import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.github.issowl.search.service.ElasticSaveService;
import io.github.issowl.search.vo.model.GoodModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("es/save")
public class ElasticSaveController {
    @Autowired
    private ElasticSaveService elasticSaveService;

    @PostMapping("/good")
    public R save(@RequestBody List<GoodModel> goodModels) {
        boolean status = false;
        try {
            status = elasticSaveService.saveGoodAsIndices(goodModels);
        } catch (Exception e) {
            log.error("远程保存索引失败");
        }
        if (status){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }

}
