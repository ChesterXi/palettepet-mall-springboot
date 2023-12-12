package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Order;
import com.tencent.wxcloudrun.model.WxPrePayParam;

public interface OrderService {
    String generateOrderSn(String openId);
    Object submit(String openId,String body);
    Integer add(Order order);
    Object prepay(String openId, String ip, WxPrePayParam wxPrePayParam);
    Order getOrderByOrderSn(String orderSn);
    Integer updateByOrderSn(String orderSn,Short orderStatus);
}
