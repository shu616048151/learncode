package com.shu.shiro.dao;

import com.shu.shiro.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shuxibing
 * @date 2021/1/29 15:56
 * @uint d9lab
 * @Description:
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    /** 通过username查找用户信息*/
    public UserInfo findByUsername(String username);
}