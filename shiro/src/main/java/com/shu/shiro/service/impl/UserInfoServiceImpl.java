package com.shu.shiro.service.impl;

import com.shu.shiro.dao.UserInfoDao;
import com.shu.shiro.model.UserInfo;
import com.shu.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shuxibing
 * @date 2021/1/29 15:57
 * @uint d9lab
 * @Description:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}
