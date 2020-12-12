package io.github.issowl.search;

import com.alibaba.fastjson.JSON;
import io.github.issowl.search.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlkmktSearchApplicationTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Data
    class User {
        public String name;
        public Integer age;
    }

    @Test
    void contextLoads() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        User user = new User();
        user.setName("王涛");
        user.setAge(20);
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }
}
