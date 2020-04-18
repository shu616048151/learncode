package com.shu.hibernate.dao;

import com.shu.domain.User;
import com.shu.hibernate.base.dao.BaseDaoImpl;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
