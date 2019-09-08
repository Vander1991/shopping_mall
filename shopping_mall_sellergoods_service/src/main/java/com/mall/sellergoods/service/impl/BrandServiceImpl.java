package com.mall.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.mapper.TbBrandMapper;
import com.mall.model.TbBrand;
import com.mall.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : Winnie
 * @date :   2019/9/4
 * @description : 品牌业务逻辑的实现
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.select(null);
    }
}
