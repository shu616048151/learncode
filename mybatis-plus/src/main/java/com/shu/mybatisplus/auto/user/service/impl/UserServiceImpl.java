package com.shu.mybatisplus.auto.user.service.impl;

import com.shu.mybatisplus.auto.user.entity.User;
import com.shu.mybatisplus.auto.user.mapper.UserMapper;
import com.shu.mybatisplus.auto.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuxibing
 * @since 2020-10-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
