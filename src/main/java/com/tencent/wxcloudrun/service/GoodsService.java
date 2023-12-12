package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsList();
    Goods getGoodsById(Long id);
}
