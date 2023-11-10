package com.systop.ddky.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.systop.ddky.admin.service.AdminService;
import com.systop.ddky.common.pojo.Admin;
import com.systop.ddky.common.utils.FileUtil;
import com.systop.ddky.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@Api("管理员模块")
@RequestMapping("/ddky/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DefaultKaptcha captchaProducer;

    @GetMapping("/findAdmins")
    @ApiOperation("查询所有管理员")
    public R findAdmins(){
        return adminService.findAdmins();
    }
    @GetMapping("/findAdminsByPage")
    @ApiOperation("分页查询所有管理员")
    public R findAdminsByPage(@RequestParam(value = "pageNum",required = false,defaultValue = "1")
                                  Integer pageNum,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "5")
                              Integer pageSize){
        return adminService.findAdminsByPage(pageNum,pageSize);
    }

    @PostMapping("/saveAdmin")
    @ApiOperation("添加管理员")
    public R saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @GetMapping("/getAdminByName")
    @ApiOperation("查询管理员")
    public R getAdminByName(@RequestParam String name){
        return adminService.getAdminByName(name);
    }

    @PostMapping("/updateAdmin")
    @ApiOperation("更新")
    public R updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @GetMapping("/deleteAdmin/{id}")
    @ApiOperation("删除")
    public R deleteAdmin(@PathVariable Integer id){
        return adminService.deleteAdmin(id);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody Admin admin,@RequestParam String verifyCode, HttpSession session){
        return adminService.login(admin,verifyCode,session);
    }

    @RequestMapping("/uplodImage")
    @ApiOperation("/上传图片")
    public String uplodImage(MultipartFile imgfile,HttpServletRequest request){
        String filePath = FileUtil.uploadFile(imgfile,request);
        return "{\"path\":\"" + filePath + "\"}";
    }

    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
