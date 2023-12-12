package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.GoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsProductService {

    List<GoodsProduct> getGoodsProductByGoodsId(Long goodsId);
    GoodsProduct getGoodsProductById(Integer id);
    int reduceStock(Integer id, Integer num);
    int addStock(Integer id, Short num);
}
