package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FrontOrderProduct {
    private Long goodsId;
    private String goodsName;
    private String goodsSn;
    private String goodsCode;
    private Integer productId;
    private BigDecimal price;
    private Integer number;
    private String specifications;
    private String color;
    private String picUrl;
}
