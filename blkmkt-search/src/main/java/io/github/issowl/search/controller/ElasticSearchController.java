package io.github.issowl.search.controller;

import io.github.common.utils.R;
import io.github.issowl.search.service.ElasticSearchService;
import io.github.issowl.search.vo.SearchParam;
import io.github.issowl.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "search")
public class ElasticSearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping(value = "/good")
    public R search(SearchParam searchParam) {
        SearchResult searchResult = elasticSearchService.getSearchResult(searchParam);
        return R.ok().put("result", searchResult);
    }
}
