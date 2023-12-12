package com.tencent.wxcloudrun.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderGoods {
    private Integer id;
    private Integer orderId;
    private String orderSn;
    private Long goodsId;
    private String goodsName;
    private String goodsSn;
    private String goodsCode;
    private Integer productId;
    //购买数量
    private Integer number;
    private BigDecimal price;
    private String specifications;
    private String color;
    private String picUrl;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
