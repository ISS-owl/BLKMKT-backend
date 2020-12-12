package io.github.issowl.search.service.impl;

import io.github.issowl.search.service.ElasticDeleteService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ElasticDeleteSeriveImpl implements ElasticDeleteService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean deleteGoodAsIndices(List<Integer> ids) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Integer id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest("goods");
            deleteRequest.id(id.toString());
            bulkRequest.add(deleteRequest);
        }
        return CommonServiceImpl.getBulkResponse(bulkRequest, restHighLevelClient);
    }
}
