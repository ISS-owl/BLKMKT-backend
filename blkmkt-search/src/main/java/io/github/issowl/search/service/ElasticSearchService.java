package io.github.issowl.search.service;

import io.github.issowl.search.vo.SearchParam;
import io.github.issowl.search.vo.SearchResult;

public interface ElasticSearchService {
    SearchResult getSearchResult(SearchParam searchParam);
}
