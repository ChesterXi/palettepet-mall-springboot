package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<Goods> getGoodsList(@Param("category") Integer category,@Param("isHot") Boolean isHot, @Param("isNew") Boolean isNew);
    Goods getGoodsById(@Param("id") Long id);
    Integer getGoodsOnSale();
}
