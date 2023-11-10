package com.systop.ddky.user.service;

import com.systop.ddky.common.pojo.User;
import com.systop.ddky.common.utils.R;

import javax.servlet.http.HttpSession;


public interface UserService {

    R getUsers();

    R deleteUserById(int id);

    R findUserById(int id);

    R findUserByName(String account);

    R loginUser(User user, String verifyCode, HttpSession session);

    R addUser(User user);

    R updateUser(User user);

    R uploadImg(Integer id,String filePath);

}
