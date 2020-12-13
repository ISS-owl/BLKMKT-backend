package io.github.issowl.search.controller;

import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.github.issowl.search.service.ElasticDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "es/delete")
public class ElasticDeleteController {
    @Autowired
    private ElasticDeleteService deleteService;

    @DeleteMapping("/good")
    public R delete(@RequestBody List<Integer> ids) {
        boolean status = false;
        try {
            status = deleteService.deleteGoodAsIndices(ids);
        } catch (Exception e) {
            log.error("远程删除索引失败");
        }
        if (status){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PRODUCT_DELETE_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_DELETE_EXCEPTION.getMsg());
        }
    }
}
