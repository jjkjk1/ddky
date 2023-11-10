package com.systop.ddky.merchants.mapper;

import com.systop.ddky.common.pojo.Admin;
import com.systop.ddky.common.pojo.Merchants;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantsMapper {
    //登录
    Merchants login(String account);
    //查询所有商家
    List<Merchants> findMerchants();
    //根据名字模糊查询
    List<Merchants> findMerchantByName(String mername);
    //添加商家
    int saveMerchant(Merchants merchants);
    //修改商家
    int updateMerchant(Merchants merchants);
    //删除
    int deleteMerchant(Integer id);
    //分页查询
    List<Merchants> findMerchantsByPage();
}
