package com.shu.mybatis;


import com.shu.domain.User;
import com.shu.mapper.UserMapper;
import com.shu.proxy.House;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisMain {
    private  SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void before() throws IOException {
        /*最原始的启动方法*/
//        String path="SqlMapConfig.xml";
//        InputStream inputStream = Resources.getResourceAsStream(path);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//         sqlSession = sqlSessionFactory.openSession();
        /*使用容器启动*/
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        userMapper = applicationContext.getBean(UserMapper.class);

    }
    @Test
    public void test1(){
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setId(2);
        User user1= userMapper.getUserById(2);
        System.out.println(user1);
        sqlSession.close();
    }

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserMapper bean = applicationContext.getBean(UserMapper.class);
//        List<User> all = bean.getAll();
//        System.out.println(all);
//        SqlSession bean = applicationContext.getBean(SqlSessionFactory.class).openSession();
//        List<User> objects = bean.selectList("com.shu.mapper.UserMapper.getAll");
//        System.out.println(objects);
        House bean = applicationContext.getBean(House.class);
        System.out.println(bean.getClass());
    }

}
