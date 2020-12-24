package io.github.issowl.search.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.common.to.es.model.GoodModel;
import io.github.issowl.search.service.ElasticUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ElasticUpdateServiceImpl implements ElasticUpdateService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean updateGoodAsIndices(List<GoodModel> goodModelList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (GoodModel goodModel : goodModelList) {
            String s = JSON.toJSONString(goodModel);    // 自动过滤null字段
            UpdateRequest updateRequest = new UpdateRequest("goods", goodModel.getId().toString());
            updateRequest.doc(s, XContentType.JSON);
            bulkRequest.add(updateRequest);
        }
        return CommonServiceImpl.getBulkResponse(bulkRequest, restHighLevelClient);
    }

}
