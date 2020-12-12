package io.github.issowl.search.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.common.utils.Query;
import io.github.issowl.search.config.ElasticSearchConfig;
import io.github.issowl.search.constant.EsConstant;
import io.github.issowl.search.service.ElasticSearchService;
import io.github.issowl.search.vo.SearchParam;
import io.github.issowl.search.vo.SearchResult;
import io.github.issowl.search.vo.model.GoodModel;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResult getSearchResult(SearchParam searchParam) {
        SearchResult searchResult= null;
        SearchRequest request = buildSearchRequest(searchParam);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(request, ElasticSearchConfig.COMMON_OPTIONS);
            searchResult = buildSearchResult(searchParam,searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    private SearchRequest buildSearchRequest(SearchParam searchParam) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 用Bool创建多个条件
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // bool must
        if (!StringUtils.isEmpty(searchParam.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", searchParam.getKeyword()));
        }
        // bool filters
        if (searchParam.getGtype() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("gtype", searchParam.getGtype()));
        }
        if (searchParam.getHasStock() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParam.getHasStock()));
        }
        if (!StringUtils.isEmpty(searchParam.getCategory())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("category.keyword", searchParam.getCategory()));
        }
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
        if (!StringUtils.isEmpty(searchParam.getPriceRange())) {
            String[] prices = searchParam.getPriceRange().split("_");
            if (prices.length == 1) {
                if (searchParam.getPriceRange().startsWith("_")) {
                    rangeQueryBuilder.lte(Integer.parseInt(prices[0]));
                } else {
                    rangeQueryBuilder.gte(Integer.parseInt(prices[0]));
                }
            } else if (prices.length == 2) {
                if (!prices[0].isEmpty()) {
                    rangeQueryBuilder.gte(Integer.parseInt(prices[0]));
                }
                rangeQueryBuilder.lte(Integer.parseInt(prices[1]));
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        searchSourceBuilder.query(boolQueryBuilder);

        // 排序
        if (!StringUtils.isEmpty(searchParam.getSort())) {
            String[] sortSplit = searchParam.getSort().split("_");
            searchSourceBuilder.sort(sortSplit[0], sortSplit[1].equalsIgnoreCase("asc")
                ? SortOrder.ASC : SortOrder.DESC);
        }

        // 分页
        searchSourceBuilder.from((searchParam.getPageNum() - 1) * EsConstant.GOOD_PAGE_SIZE);
        searchSourceBuilder.size(EsConstant.GOOD_PAGE_SIZE);

        // 高亮
        if (!StringUtils.isEmpty(searchParam.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        return new SearchRequest(new String[]{"goods"}, searchSourceBuilder);
    }

    private SearchResult buildSearchResult(SearchParam searchParam, SearchResponse searchResponse) {
        SearchResult searchResult = new SearchResult();
        SearchHits hits = searchResponse.getHits();

        // 设置商品信息
        if (hits.getHits() != null && hits.getHits().length > 0) {
            List<GoodModel> goodModels = new ArrayList<>();
            for (SearchHit hit: hits) {
                String sourceAsString = hit.getSourceAsString();
                GoodModel goodModel = JSON.parseObject(sourceAsString, GoodModel.class);
                // 设置高亮属性
                if (!StringUtils.isEmpty(searchParam.getKeyword())) {
                    HighlightField title = hit.getHighlightFields().get("title");
                    String highlight = title.getFragments()[0].string();
                    goodModel.setTitle(highlight);
                }
                goodModels.add(goodModel);
            }
            searchResult.setGoodModelList(goodModels);
        }

        // 设置分页信息
        searchResult.setPageNum(searchParam.getPageNum());
        searchResult.setTotal(hits.getTotalHits().value);
        searchResult.setTotalPages(
            (int) Math.ceil(1.0 * searchResult.getTotal() / EsConstant.GOOD_PAGE_SIZE)
        );

        return searchResult;
    }
}
