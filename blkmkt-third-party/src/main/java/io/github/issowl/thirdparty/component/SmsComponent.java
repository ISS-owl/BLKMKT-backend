package io.github.issowl.thirdparty.component;

import io.github.issowl.thirdparty.utils.HttpUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.sms")
public class SmsComponent {

    private String host;

    private String path;

    private String appcode;

    public int sendCode(String phone, String code) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> query = new HashMap<>();
        query.put("mobile", phone);
        query.put("param", "code:" + code);
        query.put("tpl_id", "TP1711063");
        Map<String, String> body = new HashMap<>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, "POST", headers, query, body);
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
