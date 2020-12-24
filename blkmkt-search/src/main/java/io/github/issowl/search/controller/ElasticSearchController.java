package io.github.issowl.search.controller;

import com.alibaba.fastjson.JSON;
import io.github.common.to.es.GoodSearchParam;
import io.github.common.to.es.GoodSearchResult;
import io.github.common.utils.R;
import io.github.issowl.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "es/search")
public class ElasticSearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping(value = "/good")
    public R search(@RequestParam String searchParamString) {
        GoodSearchParam searchParam = JSON.parseObject(searchParamString, GoodSearchParam.class);
        GoodSearchResult searchResult = elasticSearchService.getSearchResult(searchParam);
        return R.ok().put("data", searchResult);
    }
}
