package com.tencent.wxcloudrun.service.impl;

import com.github.pagehelper.PageHelper;
import com.tencent.wxcloudrun.dao.GoodsMapper;
import com.tencent.wxcloudrun.model.Goods;
import com.tencent.wxcloudrun.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoodsList(Integer category,Boolean isHot, Boolean isNew,Integer offset, Integer limit) {
        PageHelper.startPage(offset, limit);
        return goodsMapper.getGoodsList(category,isHot,isNew);
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public Integer getGoodsOnSale() {
        return goodsMapper.getGoodsOnSale();
    }
}
