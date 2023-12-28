package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsList(Integer category,Boolean isHot, Boolean isNew,Integer offset, Integer limit);
    Goods getGoodsById(Long id);
    Integer getGoodsOnSale();
}
