package io.github.issowl.search.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.common.to.es.model.GoodModel;
import io.github.issowl.search.service.ElasticSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ElasticSaveServiceImpl implements ElasticSaveService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean saveGoodAsIndices(List<GoodModel> goodModelList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (GoodModel goodModel : goodModelList) {
            IndexRequest indexRequest = new IndexRequest("goods");
            String s = JSON.toJSONString(goodModel);
            indexRequest.id(goodModel.getId().toString());
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        return CommonServiceImpl.getBulkResponse(bulkRequest, restHighLevelClient);
    }
}
