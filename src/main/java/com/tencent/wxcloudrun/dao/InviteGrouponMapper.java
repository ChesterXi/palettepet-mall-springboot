package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.InviteGroupon;
import org.apache.ibatis.annotations.Param;

public interface InviteGrouponMapper {
    InviteGroupon getInviteGrouponById(@Param("id") Integer id);

    InviteGroupon getInviteGrouponByInviteCode(@Param("inviteCode") String inviteCode);
}
