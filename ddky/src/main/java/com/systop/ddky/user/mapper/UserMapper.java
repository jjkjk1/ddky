package com.systop.ddky.user.mapper;

import com.systop.ddky.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findUsers();

    int delectUserById(Integer id);

    User findUserById(Integer id);

    User findUserByName(String account);

    User loginUser(String username);

    int addUser(User user);

    int updateUser(User user);

    int uploadImg(Integer id,String headimage);
}
