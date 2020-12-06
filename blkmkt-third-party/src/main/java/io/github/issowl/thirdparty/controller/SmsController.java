package io.github.issowl.thirdparty.controller;

import io.github.common.utils.R;
import io.github.issowl.thirdparty.component.SmsComponent;
import io.github.issowl.thirdparty.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @Autowired
    private SmsComponent smsComponent;

    @RequestMapping(value = "/sendCode")
    public R sendSms(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        int statusCode = smsComponent.sendCode(phone, code);
        return statusCode / 200 == 1? R.ok() : R.error("短信发送错误");
    }
}
