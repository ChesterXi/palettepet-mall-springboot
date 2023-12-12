package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.model.WxPrePayParam;
import com.tencent.wxcloudrun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("submit")
    public Object submit(@RequestBody String body, @RequestHeader("x-wx-openid") String openId){
        return orderService.submit(openId,body);
    }

    @PostMapping("prepay")
    public Object prePay(@RequestHeader("x-wx-openid") String openId,
                         @RequestHeader("x-real-ip") String ip,
                         @RequestBody WxPrePayParam wxPrePayParam){
        return orderService.prepay(openId,ip,wxPrePayParam);
    }
}
