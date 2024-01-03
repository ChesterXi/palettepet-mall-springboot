package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.User;

public interface UserService {
    User getUserByOpenId(String openId);
    void add(User user);
    int update(User user);
}
