package com.shu.mybatisplus.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.mybatisplus.APP;
import com.shu.mybatisplus.mapper.UserMapper;
import com.shu.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shuxibing
 * @Date 2020/10/4 20:32
 * @Uint d9lab_2019
 * @Description:  单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 插入操作
     */
    @Test
    public void testInsert() {
        System.out.println(("----- insert method test ------"));
        User user=new User();
        user.setAge(18);
        user.setEmail("61048151@qq.com");
        user.setName("李四");

        int i = userMapper.insert(user);
        System.out.println(i);
    }

    /**
     * 查询操作
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    @Test
    public void testSelect1() {
        System.out.println(("----- selectById method test ------"));
        User user = userMapper.selectById(1);
        System.out.println(user);
    }


    @Test
    public void testselectBatchIds() {
        System.out.println(("----- selectById method test ------"));
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
    }


    @Test
    public void testselectByMap() {
        System.out.println(("----- selectByMap method test ------"));
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age","19");
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/5 11:19
     * @Uint d9lab
     * @Description:  分页操作需要使用分页插件
     *
     */
    @Test
    public void testselectPage() {
        System.out.println(("----- selectPage method test ------"));
        Page<User> page=new Page<>(1,5);
        userMapper.selectPage(page,null);

        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println("total:"+page.getTotal());
        page.getRecords().forEach(System.out::println);

    }

    @Test
    public void testselectWrapper() {
        System.out.println(("----- selectPage method test ------"));

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("create_time");
        queryWrapper.in("id",1,2,3,4);

        queryWrapper.orderByAsc("update_time");

        List<User> userList = userMapper.selectList(queryWrapper);

        userList.forEach(System.out::println);

    }


    /**
     * 删除操作
     */
    @Test
    public void testDeleted() {
        System.out.println(("----- selectAll method test ------"));
        int i = userMapper.deleteById(1);
        System.out.println(i);
    }

    /**
     * 修改操作
     */
    @Test
    public void testUpdate() {
        System.out.println(("----- updateById method test ------"));
        User user=new User();
        user.setId(3L);
        user.setAge(18);
        user.setEmail("61048151@qq.com");
        user.setName("李四");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testUpdateWrapper() {
        System.out.println(("----- selectAll method test ------"));
        User user=new User();
        user.setId(1L);
        user.setAge(18);
        user.setEmail("61048151@qq.com");
        user.setName("李四");

        UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
        userUpdateWrapper.lambda().eq(User::getId,1);
        int i = userMapper.update(user,userUpdateWrapper);
        System.out.println(i);
    }



}
