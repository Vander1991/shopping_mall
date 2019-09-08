package com.mall.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.model.TbBrand;
import com.mall.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Winnie
 * @date :   2019/9/4
 * @description :
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
}
