package com.tencent.wxcloudrun.model;


import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Goods {
    private Long id;

    //商品编号
    private String goodsSn;

    private String name;

    private String goodsCode;

    //商品图片列表
    private String[] gallery;
    private String keywords;

    private Boolean isOnSale;
    private Short sortOrder;
    //商品页面商品图片
    private String picUrl;

    //商品分享海报
    private String shareUrl;

    private Boolean isNew;

    private Boolean isHot;
    //单位：盒/件
    private String unit;

    private BigDecimal retailPrice;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
