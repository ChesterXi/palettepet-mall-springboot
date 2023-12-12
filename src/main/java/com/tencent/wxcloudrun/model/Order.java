package com.tencent.wxcloudrun.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private String openId;
    private String orderSn;
    private Short orderStatus;
    private Short aftersaleStatus;
    private String consignee;
    private String mobile;
    private String address;
    private String message;
    private BigDecimal goodsPrice;
    private BigDecimal orderPrice;
    private String payId;
    private LocalDateTime payTime;
    private String shipSn;
    private String shipChannel;
    private LocalDateTime shipTime;
    private BigDecimal refundAmount;
    private String refundType;
    private String refundContent;
    private LocalDateTime refundTime;
    private LocalDateTime confirmTime;
    private LocalDateTime endTime;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
