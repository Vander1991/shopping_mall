package com.mall.sellergoods.service;


import com.mall.entity.PageResult;
import com.mall.model.TbBrand;

import java.util.List;

/**
 * @author : Winnie
 * @date :   2019/9/4
 * @description :  
 */
public interface BrandService {
    /**
     * 获取所有品牌列表
     * @return
     */
    List<TbBrand> findAll();
    /**
     * 返回分页列表
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);
    /**
     * 增加品类
     */
    void add(TbBrand brand);
    /**
     * 修改
     */
    void update(TbBrand brand);
    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    TbBrand findOne(Long id);


}
