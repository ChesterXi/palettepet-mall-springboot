package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.model.GoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsProductMapper {

    List<GoodsProduct> getGoodsProductByGoodsId(@Param("goodsId") Long goodsId);
    GoodsProduct getGoodsProductById(@Param("id") Integer id);
    int reduceStock(@Param("id") Integer id, @Param("num") Integer num);
    int addStock(@Param("id") Integer id, @Param("num") Short num);
}
