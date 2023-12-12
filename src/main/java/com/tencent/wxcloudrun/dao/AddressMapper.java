package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    Address query(@Param("openId") String openId, @Param("id") Integer id);

    List<Address> queryByUserId(@Param("openId") String openId);

    int add(Address address);

    int update(Address address);

    void logicalDeleteById(Integer id);

    void resetDefault(String openId);
}
