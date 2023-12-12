package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@Validated
public class WebHookController {

    @Autowired
    private WebhookService webhookService;

    /**
     * 回调模块-下单回调
     * @param req
     * @return
     */
    @PostMapping("v1/pay")
    public Object payCallback(@RequestBody JSONObject req){
        return webhookService.respWxPayHook(req);
    }

    /**
     * 回调模块-退款回调
     * @param req
     * @return
     */
    @PostMapping("v1/refund")
    public Object refundCallback(@RequestBody JSONObject req){
        return new JSONObject();
    }



}
