package com.shu.mybatisplus.auto.user.controller;


import com.shu.mybatisplus.auto.user.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuxibing
 * @since 2020-10-05
 */
@RestController
@RequestMapping("/user/movies")
public class MoviesController {

    @Autowired
    private IMoviesService moviesService;

    @RequestMapping("/test")
    public void test1(){
    }

}
