package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ResponseUtil;
import com.tencent.wxcloudrun.model.Address;
import com.tencent.wxcloudrun.service.AddressService;
import com.tencent.wxcloudrun.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 用户收获地址列表
     * @param openId
     * @return 收获地址列表
     */
    @GetMapping("list")
    public Object list(String openId){

        List<Address> addresses = addressService.queryByUserId(openId);
        return ResponseUtil.okList(addresses);
    }

    /**
     * 收获地址详情
     * @param openId
     * @param id
     * @return
     */
    @GetMapping("detail")
    public Object detail(String openId,Integer id){
        if(openId == null){
            return ResponseUtil.unlogin();
        }
        Address address = addressService.query(openId, id);
        if(address == null){
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(address);
    }


    /**
     * 添加或更新收获地址
     * @param openId 用户id
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("save")
    public Object save(String openId,@RequestBody Address address){
        if(openId == null){
            return ResponseUtil.unlogin();
        }
        Object error = validate(address);

        if(error != null){
            return error;
        }
        if(address.getId() == null || address.getId().equals(0)){
//            if(address.getIsDefault()){
//                addressService.resetDefault(userId);
//            }
            address.setId(null);
            address.setOpenId(openId);
            addressService.add(address);
        }
        else {
            Address addressByQuery= addressService.query(openId, address.getId());
            if(addressByQuery == null){
                return ResponseUtil.badArgumentValue();
            }
//            if (address.getIsDefault()) {
//                // 重置其他收货地址的默认选项
//                addressService.resetDefault(userId);
//            }
            address.setOpenId(openId);
            addressService.update(address);
        }

        return ResponseUtil.ok(address.getId());

    }

    /**
     * 删除收货地址
     * @param openId
     * @param address
     * @return 删除操作结果
     */
    @PostMapping("delete")
    public Object delete(String openId,@RequestBody Address address){
        if(openId == null){
            return ResponseUtil.unlogin();
        }
        Integer id = address.getId();

        if(id == null){
            return ResponseUtil.badArgument();
        }
        Address addressByQuery = addressService.query(openId, id);
        if(addressByQuery == null){
            return ResponseUtil.badArgumentValue();
        }

        addressService.delete(id);
        return ResponseUtil.ok();

    }

    private Object validate(Address address) {
        String name = address.getName();
        if (ObjectUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        // 测试收货手机号码是否正确
        String mobile = address.getTel();
        if (ObjectUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileSimple(mobile)) {
            return ResponseUtil.badArgument();
        }

        String province = address.getProvince();
        if (ObjectUtils.isEmpty(province)) {
            return ResponseUtil.badArgument();
        }

        String city = address.getCity();
        if (ObjectUtils.isEmpty(city)) {
            return ResponseUtil.badArgument();
        }

        String county = address.getCounty();
        if (ObjectUtils.isEmpty(county)) {
            return ResponseUtil.badArgument();
        }


//        String areaCode = address.getAreaCode();
//        if (ObjectUtils.isEmpty(areaCode)) {
//            return ResponseUtil.badArgument();
//        }

        String detailedAddress = address.getAddressDetail();
        if (ObjectUtils.isEmpty(detailedAddress)) {
            return ResponseUtil.badArgument();
        }

        Boolean isDefault = address.getIsDefault();
        if (isDefault == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }
}
