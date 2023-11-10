package com.systop.ddky.user.service.impl;

import com.systop.ddky.common.pojo.User;
import com.systop.ddky.common.utils.JwtUtils;
import com.systop.ddky.common.utils.R;
import com.systop.ddky.user.mapper.UserMapper;
import com.systop.ddky.user.service.UserService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public R getUsers() {
        List<User> list = userMapper.findUsers();
        return R.ok().put("users", list);
    }

    @Override
    public R deleteUserById(int id) {
      int result = userMapper.delectUserById(id);
        if(result > 0){
            return R.ok("删除成功!");
        }else {
            return R.error("删除失败!");
        }
    }

    @Override
    public R findUserById(int id) {
       User user = userMapper.findUserById(id);
        return R.ok().put("user",user);
    }

    @Override
    public R findUserByName(String account) {
        User name = userMapper.findUserByName("%"+account+"%");
        return R.ok().put("name",name);
    }

    @Override
    public R loginUser(User user,String verifyCode, HttpSession session) {
        long userId = 0;
        User userx = userMapper.loginUser(user.getUsername());
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if(userx==null){return R.error("用户不存在，请注册！");}
        if (user.getUsername().equals(userx.getUsername()) && user.getPassword().equals(userx.getPassword())){
            if(verifyCode.equals(kaptchaCode)){
                userId = 1;
                //生成token
                String token = jwtUtils.generateToken(userId);
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("expire", jwtUtils.getExpire());
                return R.ok("登陆成功！").put("map",map);
            }else {
                return R.error("验证码错误");
            }
        }else {
            return R.error("密码错误！");
        }
    }

    @Override
    public R addUser(User user) {
        int result = userMapper.addUser(user);
        if(result > 0){
            return R.ok("添加成功!");
        }else {
            return R.error("添加失败!");
        }

    }

    @Override
    public R updateUser(User user) {
        int result = userMapper.updateUser(user);
        if(result > 0){
            return R.ok("更新成功!");
        }else {
            return R.error("更新失败!");
        }
    }


    @Override
    public R uploadImg(Integer id,String filePath) {
        int result = userMapper.uploadImg(id,filePath);
        if(result > 0){
            return R.ok("更新成功!");
        }else {
            return R.error("更新失败!");
        }
    }
}
