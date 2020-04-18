package com.springboot.jpa.repostiory;

import com.springboot.jpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuxibing
 * @date 2019/12/28 15:15
 * @uint d9lab
 * @Description:
 */
@Repository
public interface UserDao extends CrudRepository<User,Integer> {

    List<User> findByUserName(String usernName);
    @Query(value = "select * from user where username like  CONCAT('%',?1,'%')",nativeQuery = true)
    List<User> findByUserNameLike(String usernName);
}
