package com.systop.ddky.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.systop.ddky.admin.mapper.AdminMapper;
import com.systop.ddky.admin.service.AdminService;
import com.systop.ddky.common.pojo.Admin;
import com.systop.ddky.common.utils.JwtUtils;
import com.systop.ddky.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public R findAdmins() {
        List<Admin> list = adminMapper.findAdmins();
        return R.ok().put("admins",list);
    }

    @Override
    public R saveAdmin(Admin admin){
        int result = adminMapper.saveAdmin(admin);
        if(result > 0){
            return R.ok("添加成功！");
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R getAdminByName(String name) {
        List<Admin> list = adminMapper.getAdminByName("%" + name + "%");
        return R.ok().put("admin",list);
    }

    @Override
    public R updateAdmin(Admin admin) {
        int result = adminMapper.updateAdmin(admin);
        if(result > 0){
            return R.ok("更新成功！");
        }else {
            return R.error("更新失败");
        }
    }

    @Override
    public R deleteAdmin(Integer id) {
        int result = adminMapper.deleteAdmin(id);
        if(result > 0){
            return R.ok("删除成功！");
        }else {
            return R.error("删除失败");
        }
    }


    @Override
    public R login(Admin admin,String verifyCode, HttpSession session) {
        long adminId = 0;
        Admin admin1 = adminMapper.login(admin.getAccount());
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (admin1 == null){ return R.error("管理员不存在");}
        if(admin1.getAccount().equals(admin.getAccount()) &&
                admin1.getPassword().equals(admin.getPassword())) {
            if(verifyCode.equals(kaptchaCode)) {
                adminId=1;
                //生成token
                String token = jwtUtils.generateToken(adminId);
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
    }

    @Override
    public R findAdminsByPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> admins = adminMapper.findAdminsByPage();
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        return R.ok().put("admins",pageInfo);
    }


}
