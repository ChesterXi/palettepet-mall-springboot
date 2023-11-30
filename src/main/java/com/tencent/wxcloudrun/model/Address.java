package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Address {
    private Integer id;
    private String name;
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String areaCode;
    private String postalCode;
    private String tel;
    private Boolean isDefault;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
