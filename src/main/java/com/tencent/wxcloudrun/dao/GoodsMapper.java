package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<Goods> getGoodsList();
    Goods getGoodsById(@Param("id") Long id);
}
