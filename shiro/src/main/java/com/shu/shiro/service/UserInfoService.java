package com.shu.shiro.service;

import com.shu.shiro.model.UserInfo;

/**
 * @author shuxibing
 * @date 2021/1/29 15:55
 * @uint d9lab
 * @Description:
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
}
