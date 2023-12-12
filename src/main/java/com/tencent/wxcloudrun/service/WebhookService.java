package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;

public interface WebhookService{

    Object respWxPayHook(JSONObject req);

    Object respWxRefundHook(JSONObject req);
}
