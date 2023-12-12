package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.client.WxPayClient;
import com.tencent.wxcloudrun.config.ResponseUtil;
import com.tencent.wxcloudrun.constants.AppConstant;
import com.tencent.wxcloudrun.constants.WxEventEnum;
import com.tencent.wxcloudrun.dao.OrderMapper;
import com.tencent.wxcloudrun.model.*;
import com.tencent.wxcloudrun.service.*;
import com.tencent.wxcloudrun.util.JacksonUtil;
import com.tencent.wxcloudrun.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderSeriveImpl implements OrderService {

    @Value("${wx.env.id:prod-4gi0ary04b229bf6}")
    public String WX_ENV_ID;
    @Value("${wx.app.server:palettemall}")
    public String WX_APP_SERVER;
    @Value("${wx.merchant.id:1660506142}")
    public String WX_MERCHANT_ID;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private WxPayClient wxClient;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GoodsProductService goodsProductService;

    @Autowired
    private InviteGrouponService inviteGrouponService;

    @Autowired
    private OrderGoodsService orderGoodsService;



    public int countByOrderSn(String openId,String orderSn){
        Integer result = orderMapper.countByOrderSn(openId, orderSn);
        System.out.println(result);
        return result;
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public String generateOrderSn(String openId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(12);
        while (countByOrderSn(openId, orderSn) != 0) {
            orderSn = now + getRandomNum(12);
        }
        return orderSn;
    }

    @Override
    @Transactional
    public Object submit(String openId, String body) {
        if(openId == null){
            return ResponseUtil.unlogin();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FrontProduct> productList = (List<FrontProduct>)JacksonUtil.parseObjectList(body,"productList",FrontProduct.class);
        List<FrontProduct> frontProductList = objectMapper.convertValue(productList, new TypeReference<List<FrontProduct>>() {
        });
        Integer addressId = JacksonUtil.parseInteger(body,"addressId");
        String inviteCode = JacksonUtil.parseString(body,"inviteCode");
        Integer cardId = JacksonUtil.parseInteger(body,"cartId");
        String message = JacksonUtil.parseString(body,"message");
        if(productList == null || addressId == null){
            return ResponseUtil.badArgument();
        }

        //收货地址
        Address checkedAddress = addressService.query(openId, addressId);
        if(checkedAddress == null){
            return ResponseUtil.badArgument();
        }

        List<FrontOrderProduct> frontOrderProductList = new ArrayList<>();
        //计算价格
        BigDecimal goodsPrice = new BigDecimal(0);
        BigDecimal orderPrice;
        for (FrontProduct frontProduct : frontProductList) {
            GoodsProduct tempGoodsProduct = goodsProductService.getGoodsProductById(frontProduct.getProductId());
            if(tempGoodsProduct!=null){
                FrontOrderProduct frontOrderProduct = new FrontOrderProduct();
                frontOrderProduct.setGoodsId(tempGoodsProduct.getGoodsId());
                frontOrderProduct.setGoodsName(tempGoodsProduct.getGoodsName());
                frontOrderProduct.setGoodsSn(tempGoodsProduct.getGoodsSn());
                frontOrderProduct.setGoodsCode(tempGoodsProduct.getGoodsCode());
                frontOrderProduct.setProductId(tempGoodsProduct.getId());
                frontOrderProduct.setPrice(tempGoodsProduct.getPrice());
                frontOrderProduct.setNumber(frontProduct.getCount());
                frontOrderProduct.setSpecifications(tempGoodsProduct.getSpecification());
                frontOrderProduct.setColor(tempGoodsProduct.getColor());
                frontOrderProduct.setPicUrl(tempGoodsProduct.getUrl());
                frontOrderProductList.add(frontOrderProduct);
                //订单价格叠加
                BigDecimal tempProductPrice = tempGoodsProduct.getPrice();
                BigDecimal tempOrderCount = new BigDecimal(frontProduct.getCount());
                goodsPrice = goodsPrice.add(tempProductPrice.multiply(tempOrderCount));
            }
        }
        if (frontOrderProductList.size() == 0) {
            return ResponseUtil.badArgumentValue();
        }

        if(inviteCode != null){
            //查找团长折扣
            InviteGroupon inviteGrouponByInviteCode = inviteGrouponService.getInviteGrouponByInviteCode(inviteCode);
            if(inviteGrouponByInviteCode == null || inviteGrouponByInviteCode.getDeleted()==true){
                return ResponseUtil.inviteCodeExpired();
            }
            else {
                orderPrice = new BigDecimal(goodsPrice.multiply(inviteGrouponByInviteCode.getDiscount()).toString());
            }
        }
        else{
            orderPrice = new BigDecimal(goodsPrice.toString());
        }


        Integer orderId = null;
        Order order = null;
        //生成订单
        order = new Order();
        order.setOpenId(openId);
        //生成订单号
        order.setOrderSn(generateOrderSn(openId));
        order.setOrderPrice(orderPrice);
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(checkedAddress.getName());
        order.setMobile(checkedAddress.getTel());
        order.setMessage(message);
        String detailedAddress = checkedAddress.getProvince() + checkedAddress.getCity() + checkedAddress.getCounty() + " " + checkedAddress.getAddressDetail();
        order.setAddress(detailedAddress);
        order.setGoodsPrice(goodsPrice);
        order.setOrderPrice(orderPrice);
        System.out.println(order);
        //add,getid
        add(order);
        orderId = order.getId();
        //添加订单商品表项
        for (FrontOrderProduct frontOrderProduct : frontOrderProductList) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setOrderSn(order.getOrderSn());
            orderGoods.setGoodsId(frontOrderProduct.getGoodsId());
            orderGoods.setGoodsName(frontOrderProduct.getGoodsName());
            orderGoods.setGoodsSn(frontOrderProduct.getGoodsSn());
            orderGoods.setGoodsCode(frontOrderProduct.getGoodsCode());
            orderGoods.setProductId(frontOrderProduct.getProductId());
            orderGoods.setNumber(frontOrderProduct.getNumber());
            orderGoods.setPrice(frontOrderProduct.getPrice());
            orderGoods.setSpecifications(frontOrderProduct.getSpecifications());
            orderGoods.setColor(frontOrderProduct.getColor());
            orderGoods.setPicUrl(frontOrderProduct.getPicUrl());
            orderGoodsService.insertOrderGoods(orderGoods);
        }

        //扣库存
        for (FrontOrderProduct frontOrderProduct : frontOrderProductList) {
            GoodsProduct goodsProduct = goodsProductService.getGoodsProductById(frontOrderProduct.getProductId());
            int remainNumber = goodsProduct.getNumber() - frontOrderProduct.getNumber();
            if (remainNumber < 0) {
                throw new RuntimeException("下单的商品货品数量大于库存量");
            }
            if (goodsProductService.reduceStock(frontOrderProduct.getProductId(), frontOrderProduct.getNumber()) == 0) {
                throw new RuntimeException("商品货品库存减少失败");
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("orderSn",order.getOrderSn());
        resultMap.put("payStatus",order.getOrderStatus());
        return resultMap;
    }

    @Override
    public Integer add(Order order) {
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.insertOrder(order);
    }

    @Override
    public Object prepay(String openId, String ip, WxPrePayParam wxPrePayParam) {
        JSONObject prePayJson = new JSONObject();
        prePayJson.put("openid",openId);
        prePayJson.put("sub_mch_id",WX_MERCHANT_ID);
        prePayJson.put("env_id",WX_ENV_ID);
        prePayJson.put("out_trade_no",wxPrePayParam.getOrderSn());
        prePayJson.put("spbill_create_ip",ip);
        prePayJson.put("callback_type",2);
        prePayJson.put("total_fee",wxPrePayParam.getOrderPrice().setScale(2, RoundingMode.DOWN).intValue());
        Container container = new Container();
        container.setPath(AppConstant.WEB_HOOK_PAY_PATH);
        container.setService(WX_APP_SERVER);
        prePayJson.put("container",container);
        WxEventEnum event = WxEventEnum.UNIFIED_ORDER;
        JSONObject respJson = wxClient.prePay(prePayJson);
        return respJson;
    }

    @Override
    public Order getOrderByOrderSn(String orderSn) {
        return orderMapper.getOrderByOrderSn(orderSn);
    }

    @Override
    public Integer updateByOrderSn(String orderSn, Short orderStatus) {
        return orderMapper.updateByOrderSn(orderSn,orderStatus);
    }
}
