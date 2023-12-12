package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String openId;
    private String nickName;
    private String mobile;
    private String avatarUrl;
    private Boolean dealer;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Integer status;
}
