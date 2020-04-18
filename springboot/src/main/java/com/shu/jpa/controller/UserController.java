package com.shu.jpa.controller;

import com.shu.jpa.model.User;
import com.shu.jpa.repostiory.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuxibing
 * @date 2019/12/28 11:33
 * @uint d9lab
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Api(tags = "user",description = "B站video的api",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userRepository;

    @ApiOperation("用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",paramType ="form",dataType = "int",required = true)
    })
    @RequestMapping(value = "/getOne",method = RequestMethod.POST)
    public User getListByUserName(Integer id)throws Exception{
        logger.info(""+id);
        User one = userRepository.findOne(id);
        return one;
    }
    @ApiOperation("用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value = "username",paramType ="form",dataType = "string",required = true)
    })
    @RequestMapping(value = "/findByUserName",method = RequestMethod.POST)
    public List<User> findByUserName(String username)throws Exception{
        logger.info(""+username);
       return userRepository.findByUserNameLike(username);
    }

    @ApiOperation("用户信息")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/getAllUser",method = RequestMethod.POST)
    public List<User> getAllUser()throws Exception{
        return (List<User>) userRepository.findAll();
    }

}
