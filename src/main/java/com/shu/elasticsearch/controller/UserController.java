package com.shu.elasticsearch.controller;
import	java.lang.annotation.Retention;

import com.shu.elasticsearch.dao.UserDao;
import com.shu.elasticsearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuxibing
 * @date 2019/9/19 10:11
 * @uint d9lab
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/add")
    public void add(@RequestBody User user){
        userDao.save(user);
    }
    @RequestMapping("/find")
    public User find(String id){
       return userDao.findOne(id);
    }

    @RequestMapping("/test")
    public List<User> getTest(){
        return (List<User>) userDao.findAll();
    }
}
