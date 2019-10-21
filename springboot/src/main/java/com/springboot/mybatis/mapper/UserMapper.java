package com.springboot.mybatis.mapper;


import com.springboot.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
   public List<User> getAll();
   public User getUserById(User user);
   public User getUserById(@Param("id") int id);
}
