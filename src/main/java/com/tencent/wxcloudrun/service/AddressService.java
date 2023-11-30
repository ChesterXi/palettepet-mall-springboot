package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.model.Address;

import java.util.List;

public interface AddressService {

    Address query(Integer userId,Integer id);

    List<Address> queryByUserId(Integer userId);

    int add(Address address);

    int update(Address address);

    void delete(Integer id);

    void resetDefault(Integer userId);

}
