package io.github.issowl.search.service;


import io.github.common.to.es.GoodSearchParam;
import io.github.common.to.es.GoodSearchResult;

public interface ElasticSearchService {
    GoodSearchResult getSearchResult(GoodSearchParam searchParam);
}
