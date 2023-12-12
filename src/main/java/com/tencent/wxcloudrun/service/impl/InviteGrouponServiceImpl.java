package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.InviteGrouponMapper;
import com.tencent.wxcloudrun.model.InviteGroupon;
import com.tencent.wxcloudrun.service.InviteGrouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InviteGrouponServiceImpl implements InviteGrouponService {

    @Resource
    private InviteGrouponMapper inviteGrouponMapper;
    @Override
    public InviteGroupon getInviteGrouponByInviteCode(String inviteCode) {
        return inviteGrouponMapper.getInviteGrouponByInviteCode(inviteCode);
    }
}
