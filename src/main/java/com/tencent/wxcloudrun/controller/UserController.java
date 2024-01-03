package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ResponseUtil;
import com.tencent.wxcloudrun.model.Address;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Object add(@RequestBody String body, HttpServletRequest request){
        String openId = request.getHeader("x-wx-openid");
        User userByOpenId = userService.getUserByOpenId(openId);
        if(userByOpenId != null){
            return ResponseUtil.exitUser();
        }
        String nickName = JacksonUtil.parseString(body, "nickName");
        String avatarUrl = JacksonUtil.parseString(body, "avatarUrl");
        String mobile = JacksonUtil.parseString(body, "mobile");
        if(nickName == null || avatarUrl == null || mobile == null){
            return ResponseUtil.badArgument();
        }
        //add
        User user = new User();
        user.setOpenId(openId);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);
        user.setMobile(mobile);
        userService.add(user);
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("openId",openId);
        result.put("nickName",nickName);
        result.put("avatarUrl",avatarUrl);
        result.put("mobile",mobile);

        return ResponseUtil.ok(result);
    }

    @PostMapping("update")
    public Object update(@RequestBody String body, @RequestHeader("x-wx-openid") String openId){
        User userByOpenId = userService.getUserByOpenId(openId);
        if(userByOpenId == null){
            return ResponseUtil.userNotExist();
        }
        String nickName = JacksonUtil.parseString(body, "nickName");
        String avatarUrl = JacksonUtil.parseString(body, "avatarUrl");
        String mobile = JacksonUtil.parseString(body, "mobile");
        if(nickName == null || avatarUrl == null || mobile == null){
            return ResponseUtil.badArgument();
        }
        userByOpenId.setNickName(nickName);
        userByOpenId.setAvatarUrl(avatarUrl);
        userByOpenId.setMobile(mobile);
        userService.update(userByOpenId);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("openId",openId);
        result.put("nickName",nickName);
        result.put("avatarUrl",avatarUrl);
        result.put("mobile",mobile);

        return ResponseUtil.ok(result);
    }
}
