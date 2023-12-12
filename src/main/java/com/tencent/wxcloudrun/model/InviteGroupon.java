package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InviteGroupon {
    Integer id;
    String openId;
    String inviteCode;
    BigDecimal discount;
    LocalDateTime addTime;
    LocalDateTime updateTime;
    LocalDateTime expireTime;
    Boolean deleted;
}
