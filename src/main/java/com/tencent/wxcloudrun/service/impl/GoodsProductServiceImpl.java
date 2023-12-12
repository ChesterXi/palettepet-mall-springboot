package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.GoodsProductMapper;
import com.tencent.wxcloudrun.model.GoodsProduct;
import com.tencent.wxcloudrun.service.GoodsProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsProductServiceImpl implements GoodsProductService {

    @Resource
    private GoodsProductMapper goodsProductMapper;
    @Override
    public List<GoodsProduct> getGoodsProductByGoodsId(Long goodsId) {
        return goodsProductMapper.getGoodsProductByGoodsId(goodsId);
    }

    @Override
    public GoodsProduct getGoodsProductById(Integer id) {
        return goodsProductMapper.getGoodsProductById(id);
    }

    @Override
    public int reduceStock(Integer id, Integer num) {
        return goodsProductMapper.reduceStock(id,num);
    }

    @Override
    public int addStock(Integer id, Short num) {
        return goodsProductMapper.addStock(id,num);
    }
}
