package com.systop.ddky.merchants.service;

import com.systop.ddky.common.pojo.Merchants;
import com.systop.ddky.common.utils.R;

import javax.servlet.http.HttpSession;

public interface MerchantsService {
    R login(Merchants merchants, String verifyCode, HttpSession session);
    R findMerchants();
    R findMerchantByName(String mername);
    R saveMerchant(Merchants merchants);
    R updateMerchant(Merchants merchants);
    R deleteMerchant(Integer id);
    R findMerchantsByPage(Integer pageNum,Integer pageSize);
}
