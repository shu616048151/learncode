package com.shu.docker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxibing
 * @Date 2020/9/15 21:22
 * @Uint d9lab_2019
 * @Description:
 */
@RestController
public class DockerController {

    @RequestMapping("/test")
    public String test(String name){
        System.out.println(name);
        return name;
    }
}
