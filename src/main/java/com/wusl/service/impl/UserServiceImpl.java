package com.wusl.service.impl;

import com.wusl.dao.UserRepository;
import com.wusl.pojo.User;
import com.wusl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User checkUser(String username, String password) {
        String md5PassWord =password+"password"+"1234qwer";
        try {
            md5PassWord = DigestUtils.md5DigestAsHex(md5PassWord.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(md5PassWord);
        User user = userRepository.findByUsernameAndPassword(username, md5PassWord);
        return user;
    }
}
