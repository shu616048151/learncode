package com.shu.hibernate.dao;

import com.shu.domain.User;
import com.shu.hibernate.base.dao.BaseDaoImpl;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
