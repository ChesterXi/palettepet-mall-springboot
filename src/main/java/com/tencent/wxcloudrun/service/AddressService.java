package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.model.Address;

import java.util.List;

public interface AddressService {

    Address query(String openId,Integer id);

    List<Address> queryByUserId(String openId);

    int add(Address address);

    int update(Address address);

    void delete(Integer id);

    void resetDefault(String openId);

}
