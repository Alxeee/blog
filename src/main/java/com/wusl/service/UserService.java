package com.wusl.service;

import com.wusl.pojo.User;

public interface UserService {


    /*检查用户名 和密码*/
    User checkUser(String username,String password);

}
