package io.github.issowl.search.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.issowl.search.config.ElasticSearchConfig;
import io.github.issowl.search.service.SaveService;
import io.github.issowl.search.vo.model.GoodModel;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SaveServiceImpl implements SaveService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean saveGoodAsIndices(List<GoodModel> goodModelList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (GoodModel goodModel : goodModelList) {
            IndexRequest indexRequest = new IndexRequest("product");
            String s = JSON.toJSONString(goodModel);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        boolean hasFailures = bulkResponse.hasFailures();
        List<String> collect = Arrays.stream(bulkResponse.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());

        log.info("商品上架完成：{}",collect);

        return !hasFailures;
    }
}
