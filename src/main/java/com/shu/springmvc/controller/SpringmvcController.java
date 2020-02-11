package com.shu.springmvc.controller;

import com.shu.springmvc.exception.RequestLimitException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuxibing
 * @date 2020/2/11 11:41
 * @uint d9lab
 * @Description:
 */
@RestController
@RequestMapping(value = "/springmvc")
public class SpringmvcController {

    @RequestMapping(value = "/test")
    public void test()throws  Exception{
        throw new RequestLimitException("访问被限制");
    }
}
