package com.systop.ddky.merchants.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.systop.ddky.common.pojo.Merchants;
import com.systop.ddky.common.utils.JwtUtils;
import com.systop.ddky.common.utils.R;
import com.systop.ddky.merchants.mapper.MerchantsMapper;
import com.systop.ddky.merchants.service.MerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MerchantsServiceImpl implements MerchantsService {
    @Autowired
    private MerchantsMapper merchantsMapper;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public R login(Merchants merchants, String verifyCode, HttpSession session) {
        long merchartsId=0;
        Merchants merchants1 = merchantsMapper.login(merchants.getAccount());
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (merchants1.getAccount().equals(merchants.getAccount())){
            if (merchants1.getPassword().equals(merchants.getPassword())){
                if(kaptchaCode.equals(verifyCode)){
                    merchartsId=1;
                    String token = jwtUtils.generateToken(merchartsId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", token);
                    map.put("expire", jwtUtils.getExpire());
                    return R.ok("登录成功").put("map",map);
                }else {
                    return R.error("验证码错误");
                }
            }else {
                return R.error("密码错误");
            }
        }else {
            return R.error("账号错误");
        }
    }

    @Override
    public R findMerchants() {
        List<Merchants> list = merchantsMapper.findMerchants();
        return R.ok().put("merchants",list);
    }

    @Override
    public R findMerchantByName(String mername) {
        List<Merchants> list = merchantsMapper.findMerchantByName("%" + mername + "%");
        return R.ok().put("merchants",list);
    }

    @Override
    public R saveMerchant(Merchants merchants) {
        int result = merchantsMapper.saveMerchant(merchants);
        if(result > 0){
            return R.ok("添加成功！");
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R updateMerchant(Merchants merchants) {
        int result = merchantsMapper.updateMerchant(merchants);
        if(result > 0){
            return R.ok("更新成功！");
        }else {
            return R.error("更新失败");
        }
    }

    @Override
    public R deleteMerchant(Integer id) {
        int result = merchantsMapper.deleteMerchant(id);
        if(result > 0){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findMerchantsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Merchants> merchants = merchantsMapper.findMerchantsByPage();
        PageInfo<Merchants> pageInfo = new PageInfo<>(merchants);
        return R.ok().put("merchants",pageInfo);
    }
}
