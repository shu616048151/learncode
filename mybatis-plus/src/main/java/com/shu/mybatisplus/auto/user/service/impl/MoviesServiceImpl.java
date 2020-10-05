package com.shu.mybatisplus.auto.user.service.impl;

import com.shu.mybatisplus.auto.user.entity.Movies;
import com.shu.mybatisplus.auto.user.mapper.MoviesMapper;
import com.shu.mybatisplus.auto.user.service.IMoviesService;
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
public class MoviesServiceImpl extends ServiceImpl<MoviesMapper, Movies> implements IMoviesService {

}
