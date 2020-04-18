package com.springboot.hibernate.dao;

import com.springboot.domain.User;
import com.springboot.hibernate.base.dao.BaseDaoImpl;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
