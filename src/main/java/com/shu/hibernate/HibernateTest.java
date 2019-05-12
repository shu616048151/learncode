package com.shu.hibernate;

import com.shu.domain.User;
import com.shu.hibernate.dao.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.List;

public class HibernateTest{
    public static void main(String[] args) throws Exception {


    }
    private ApplicationContext applicationContext;
    private UserDao userDao;
    private HibernateTemplate hibernateTemplate;
    @Before
    public void before(){
        applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-hibernate.xml");
         userDao = applicationContext.getBean(UserDao.class);
         hibernateTemplate=applicationContext.getBean(HibernateTemplate.class);
    }
    @Test
    public void add() throws Exception {
        User user=new User();
        user.setName("王二小");
        user.setAddress("王庄");
        user.setAge(10);
        userDao.save(user);
    }
    @Test
    public void update() throws Exception {
        User user=new User();
        user.setId(1);
        user.setName("王二小");
        user.setAddress("李村");
        user.setAge(10);
        userDao.update(user);
    }
    @Test
    public void search() throws Exception {
        String hql="from User";
        User one = userDao.findOne(hql);
        System.out.println(one);
    }
    @Test
    public void delete() throws Exception {
        User user=new User();
        user.setId(1);
        user.setName("王二小");
        user.setAddress("李村");
        user.setAge(10);
        userDao.delete(user);
    }
    @Test
    public void getFirstOne(){
        final String hql="from User where id=3";
     User user= hibernateTemplate.executeWithNativeSession(new HibernateCallback<User>() {
         public User doInHibernate(Session session) throws HibernateException {
             Query query =session.createQuery(hql);
             return (User) query.setMaxResults(1).uniqueResult();
         }
     });
        System.out.println(user);
    }
}
