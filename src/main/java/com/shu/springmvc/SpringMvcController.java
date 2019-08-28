package com.shu.springmvc;

/**
 * @author shuxibing
 * @date 2019/8/2 12:41
 * @uint d9lab
 * @Description:
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringMvcController {
    @RequestMapping("/user/shu")
    public String shu(String test){
        return "shu";
    }
}
