package com.systop.ddky.banner.controller;

import com.systop.ddky.banner.service.bannerService;
import com.systop.ddky.common.pojo.Banner;
import com.systop.ddky.common.utils.FileUtil;
import com.systop.ddky.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api("轮播图模块")
@RequestMapping("/ddky/banner")
public class bannerController {
    @Autowired
    private bannerService bannerService;

    @GetMapping("/findBanners")
    @ApiOperation("查询所有")
    public R findBanners(){
        return bannerService.findBanners();
    }

    @GetMapping("/findBannerById/{id}")
    @ApiOperation("根据ID查询")
    public R findBannerById(Integer id){
        return bannerService.findBannerById(id);
    }

    @GetMapping("/findBannerByName")
    @ApiOperation("根据名字模糊查询")
    public R findBannerByName(String bannerName){
        return bannerService.findBannerByName(bannerName);
    }

    @GetMapping("/delectBannerById/{id}")
    @ApiOperation("根据ID删除")
    public R delectBannerById(Integer id){
        return bannerService.delectBannerById(id);
    }

    @PostMapping("/saveBanner")
    @ApiOperation("添加轮播图")
    public R saveBanner(@RequestBody Banner banner){
        return bannerService.saveBanner(banner);
    }

    @RequestMapping("/uploadImger")
    @ApiOperation("上传图片")
    public String uploadImg(MultipartFile imgfile, HttpServletRequest request){
        String filePath = FileUtil.uploadFile(imgfile, request);
        System.out.println("{\"path\":\"" + filePath + "\"}");
        return "{\"path\":\"" + filePath + "\"}";
    }

}
