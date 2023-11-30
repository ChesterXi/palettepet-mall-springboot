package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.AddressMapper;
import com.tencent.wxcloudrun.model.Address;
import com.tencent.wxcloudrun.service.AddressService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public Address query(Integer userId, Integer id) {
        return addressMapper.query(userId, id);
    }

    @Override
    public List<Address> queryByUserId(Integer userId) {
        return addressMapper.queryByUserId(userId);
    }

    @Override
    public int add(Address address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.add(address);
    }

    @Override
    public int update(Address address) {
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.update(address);
    }

    @Override
    public void delete(Integer id) {
        addressMapper.logicalDeleteById(id);
    }

    @Override
    public void resetDefault(Integer userId) {

    }
}
