package com.systop.ddky.admin.service;

import com.systop.ddky.common.pojo.Admin;
import com.systop.ddky.common.utils.R;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminService {
    R findAdmins();
    R saveAdmin(Admin admin);
    R getAdminByName(String name);
    R updateAdmin(Admin admin);
    R deleteAdmin(Integer id);
    R login(Admin admin, String verifyCode, HttpSession session);
    R findAdminsByPage(Integer pageNum,Integer pageSize);

}
