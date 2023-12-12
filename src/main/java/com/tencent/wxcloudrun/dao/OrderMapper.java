package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    Integer countByOrderSn(@Param("openId") String openId, @Param("orderSn") String orderSn);
    Integer insertOrder(Order order);
    Order getOrderByOrderSn(String orderSn);
    Integer updateByOrderSn(@Param("orderSn")String orderSn, @Param("orderStatus")Short orderStatus);
}
