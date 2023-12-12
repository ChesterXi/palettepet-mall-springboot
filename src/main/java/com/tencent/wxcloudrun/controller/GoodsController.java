package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ResponseUtil;
import com.tencent.wxcloudrun.model.Goods;
import com.tencent.wxcloudrun.model.GoodsProduct;
import com.tencent.wxcloudrun.service.GoodsProductService;
import com.tencent.wxcloudrun.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
@Validated
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsProductService goodsProductService;

    @GetMapping("list")
    public Object listGoods(){
        //添加搜索历史

        //查询列表数据
        List<Goods> goodsList = goodsService.getGoodsList();
        if(goodsList!=null && !goodsList.isEmpty()){
            return ResponseUtil.ok(goodsList);
        }
        return ResponseUtil.serious();
    }

    @GetMapping("detail")
    public Object getGoodsDetail(Long id){
        //返回数据
        Map<String, Object> data = new HashMap<>();
        //获取商品信息
        Goods goodsById = goodsService.getGoodsById(id);

        //商品规格

        //商品规格对应的价格
        List<GoodsProduct> goodsProductByGoodsId = goodsProductService.getGoodsProductByGoodsId(id);

        //

        data.put("goods",goodsById);
        data.put("productList",goodsProductByGoodsId);
        return ResponseUtil.ok(data);
    }
}
