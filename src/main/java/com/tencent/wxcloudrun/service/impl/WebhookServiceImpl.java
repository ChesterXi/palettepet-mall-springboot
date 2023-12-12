package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ResponseUtil;
import com.tencent.wxcloudrun.model.Order;
import com.tencent.wxcloudrun.service.OrderService;
import com.tencent.wxcloudrun.service.WebhookService;
import com.tencent.wxcloudrun.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebhookServiceImpl implements WebhookService {

    @Autowired
    private OrderService orderService;

    @Override
    public Object respWxPayHook(JSONObject req) {
        String outTradeNo = req.getString("outTradeNo");
        Order orderEntity = orderService.getOrderByOrderSn(outTradeNo);
        if(orderEntity == null){
            return ResponseUtil.unOrderSn();
        }
        updatePayOrder(outTradeNo);
        return ResponseUtil.ok();
    }

    @Override
    public Object respWxRefundHook(JSONObject req) {
        return null;
    }


    private void updatePayOrder(String outTradeNo){
        orderService.updateByOrderSn(outTradeNo, OrderUtil.STATUS_PAY);
    }
}
