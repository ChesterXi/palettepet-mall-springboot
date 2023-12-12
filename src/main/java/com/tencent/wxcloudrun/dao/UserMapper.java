package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.User;

public interface UserMapper {
    User getUserByOpenId(String openId);
    Integer add(User user);
}
