package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dao.InviteGrouponMapper;
import com.tencent.wxcloudrun.model.InviteGroupon;
import org.apache.ibatis.annotations.Param;

public interface InviteGrouponService {
    InviteGroupon getInviteGrouponByInviteCode(String inviteCode);
}
