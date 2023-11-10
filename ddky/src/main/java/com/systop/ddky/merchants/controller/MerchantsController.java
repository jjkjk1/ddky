package com.systop.ddky.merchants.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.systop.ddky.common.pojo.Merchants;
import com.systop.ddky.common.utils.FileUtil;
import com.systop.ddky.common.utils.R;
import com.systop.ddky.merchants.service.MerchantsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@Api("商家模块")
@RequestMapping("/ddky/merchants")
public class MerchantsController {
    @Autowired
    private MerchantsService merchantsService;
    @Autowired
    private DefaultKaptcha captchaProducer;
    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody Merchants merchants, @RequestParam String verifyCode, HttpSession session){
        return merchantsService.login(merchants,verifyCode,session);
    }
    @GetMapping("/findMerchants")
    @ApiOperation("查询所有商家")
    public R findMerchants(){
        return merchantsService.findMerchants();
    }

    @GetMapping("/findMerchantByName")
    @ApiOperation("根据商家名字模糊查询")
    public R findMerchantByName(@RequestParam String mername){
        return merchantsService.findMerchantByName(mername);
    }

    @PostMapping("/saveMerchant")
    @ApiOperation("添加商家")
    public R saveMerchant(@RequestBody Merchants merchants){
        return merchantsService.saveMerchant(merchants);
    }

    @PostMapping("/updateMerchant")
    @ApiOperation("修改商家")
    public R updaeMerchant(@RequestBody Merchants merchants){
        return merchantsService.updateMerchant(merchants);
    }

    @GetMapping("/deleteMerchant/{id}")
    @ApiOperation("删除商家")
    public R deleteMerchant(@PathVariable Integer id){
        return merchantsService.deleteMerchant(id);
    }

    @GetMapping("/findMerchantsByPage")
    @ApiOperation("分页查询所有商家")
    public R findMerchantsByPage(@RequestParam(value = "pageNum",required = false,defaultValue = "1")
                              Integer pageNum,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "5")
                              Integer pageSize){
        return merchantsService.findMerchantsByPage(pageNum,pageSize);
    }

    @RequestMapping("/uplodImage")
    @ApiOperation("/上传图片")
    public String uplodImage(MultipartFile imgfile, HttpServletRequest request){
        String filePath = FileUtil.uploadFile(imgfile,request);
        return "{\"path\":\"" + filePath + "\"}";
    }

}
