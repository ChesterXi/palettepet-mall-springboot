package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.dao.OrderGoodsMapper;
import com.tencent.wxcloudrun.model.OrderGoods;
import com.tencent.wxcloudrun.service.OrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public Integer insertOrderGoods(OrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertOrderGoods(orderGoods);
    }
}
