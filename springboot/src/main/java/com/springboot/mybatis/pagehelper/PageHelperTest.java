package com.springboot.mybatis.pagehelper;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.domain.User;
import com.springboot.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author shuxibing
 * @date 2019/10/4 19:11
 * @uint d9lab
 * @Description:
 */
public class PageHelperTest {
    private SqlSession sqlSession;
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
    public void test(){
        PageHelper.startPage(1,3);
        PageHelper.orderBy("age");
        List<User> userList = userMapper.getAll();
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        List<User> list = pageInfo.getList();
        System.out.println(Convert.toStr(pageInfo));
        System.out.println(Convert.toStr(list));
        System.out.println("数据总页数："+pageInfo.getPages());
        System.out.println("数据总条数："+pageInfo.getTotal());
    }
}
