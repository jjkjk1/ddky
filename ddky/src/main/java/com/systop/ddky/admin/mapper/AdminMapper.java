package com.systop.ddky.admin.mapper;

import com.systop.ddky.common.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    //查询所有管理员
    List<Admin> findAdmins();
    //添加
    int saveAdmin(Admin admin);
    //根据名字查询
    List<Admin> getAdminByName(String name);
    //修改
    int updateAdmin(Admin admin);
    //删除
    int deleteAdmin(Integer id);
    //登录
    Admin login(String account);
    //分页查询
    List<Admin> findAdminsByPage();

}
