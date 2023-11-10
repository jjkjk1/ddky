package com.systop.ddky.common.pojo;

import lombok.Data;

@Data
public class User {

    private Integer userId;
    private String username;
    private String account;
    private String password;
    private String headimage;
    private String phone;
    private String email;
    private int status;
}
