package com.shu.jwt;

import com.shu.jwt.annoation.PassLogin;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author shuxibing
 * @Date 2020/9/8 14:41
 * @Uint d9lab_2019
 * @Description:
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
public class JwtApp {
    public static void main(String[] args){
        SpringApplication.run(JwtApp.class,args);
    }


    @RequestMapping("/test/login")
    @PassLogin
    public void login(Integer id, String password, HttpServletResponse response) throws Exception {
        JwtTest jwtTest=new JwtTest();
        String jwt = jwtTest.createJWT("" + id, "" + id, password, 1000);
        response.setHeader("token",jwt);
    }


    @RequestMapping("/test/logout")
    public void logout(Integer id, String password, HttpServletResponse response) throws Exception {
        JwtTest jwtTest=new JwtTest();
        String jwt = jwtTest.createJWT("" + id, "" + id, password, 1000);
        response.setHeader("token",jwt);
    }
}
