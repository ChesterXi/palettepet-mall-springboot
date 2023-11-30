package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    Address query(@Param("userId") Integer userId, @Param("id") Integer id);

    List<Address> queryByUserId(Integer userId);

    int add(Address address);

    int update(Address address);

    void logicalDeleteById(Integer id);

    void resetDefault(Integer userId);
}
