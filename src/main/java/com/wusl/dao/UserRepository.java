package com.wusl.dao;

import com.wusl.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User,Long> {


    /*获取name 和 pwd*/
    User findByUsernameAndPassword(String username,String password);



}
