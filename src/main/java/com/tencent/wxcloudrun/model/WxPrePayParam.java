package com.tencent.wxcloudrun.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class WxPrePayParam {
    private Integer orderId;
    private BigDecimal orderPrice;
    private String orderSn;

}
