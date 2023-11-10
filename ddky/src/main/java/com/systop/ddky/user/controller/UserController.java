package com.systop.ddky.user.controller;

import com.systop.ddky.common.pojo.User;

import com.systop.ddky.common.utils.FileUtil;
import com.systop.ddky.common.utils.R;
import com.systop.ddky.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Api("用户模块")
@RequestMapping("/ddky/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody User user,@RequestParam String verifyCode, HttpSession session){
        return userService.loginUser(user,verifyCode,session);
    }

    @GetMapping("/getAll")
    @ApiOperation(notes = "查询所有用户", value = "查询所有用户")
    public R getAll() {
        return userService.getUsers();
    }

    @GetMapping("/findUserById/{id}")
    @ApiOperation("根据ID查询用户")
    public R findUserById(@PathVariable int id){
        return userService.findUserById(id);
    }

    @GetMapping("/deleteUserById/{id}")
    @ApiOperation("根据ID删除用户")
    public R deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/findUserByName")
    @ApiOperation("根据名字模糊查询")
    public R findUserByName(String account){
        return userService.findUserByName(account);
    }

    @PostMapping("/addUser")
    @ApiOperation("注册用户")
    public R addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户")
    public R updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping("/uploadImg")
    @ApiOperation("更新头像")
    public R uploadImg(Integer id,MultipartFile imgfile, HttpServletRequest request){
        String filePath = FileUtil.uploadFile(imgfile, request);
        System.out.println("{\"path\":\"" + filePath + "\"}");
        return userService.uploadImg(id,filePath);
    }
}
