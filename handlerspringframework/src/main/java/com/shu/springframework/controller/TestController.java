package com.shu.springframework.controller;

import com.shu.springframework.annotation.ZYController;
import com.shu.springframework.annotation.ZYRequestMapping;
import com.shu.springframework.annotation.ZYRequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author shuxibing
 * @Date 2020/3/11 14:56
 * @Uint d9lab_2019
 * @Description:
 */
@ZYController
@ZYRequestMapping("/test")
public class TestController {

    @ZYRequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response, String name)  {
        System.out.println(name);
        try {
            response.getWriter().write("hello,"+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ZYRequestMapping("/test1")
    public void test1(HttpServletRequest request,HttpServletResponse response,@RequestParam @ZYRequestParam("name") String name,@ZYRequestParam("test") String
                      test)  {
        System.out.println(name+":"+test);
        try {
            response.getWriter().write(name+":"+test);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
