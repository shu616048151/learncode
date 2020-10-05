package com.shu.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shu.mybatisplus.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author shuxibing
 * @Date 2020/10/4 20:30
 * @Uint d9lab_2019
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
