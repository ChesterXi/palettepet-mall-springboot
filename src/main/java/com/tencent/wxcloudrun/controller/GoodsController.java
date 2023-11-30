package com.tencent.wxcloudrun.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@Validated
public class GoodsController {

    @GetMapping("list")
    public Object listGoods(){
        //添加搜索历史

        //查询列表数据
        return new Object();
    }

    @GetMapping("detail")
    public Object getGoodsDetail(Integer id){
        //获取商品详情
        return new Object();
    }
}
