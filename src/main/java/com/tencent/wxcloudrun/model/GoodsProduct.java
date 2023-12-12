package com.tencent.wxcloudrun.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodsProduct {
    private Integer id;
    private Long goodsId;
    private String goodsName;
    private String goodsSn;
    private String goodsCode;
    private String specification;
    private String color;
    private BigDecimal price;
    private Integer number;
    private String url;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
