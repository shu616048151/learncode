package com.shu.redis.antibrush.aspect;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxibing
 * @Date 2020/12/29 20:49
 * @Uint d9lab_2019
 * @Description:  API限流的实现方式
 *
 *
 * 令牌桶的实现方法
 */


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
@RequestMapping("/test")
public class APILimitAop {
    public static void main(String[] args){
        SpringApplication.run(APILimitAop.class,args);
    }

    //令牌桶算法
    public final RateLimiter rateLimiter=RateLimiter.create(10);

    @RequestMapping("/test")
    public String test() throws Exception {
        if (rateLimiter.tryAcquire()){
            return  "成功请求";
        }else {
           throw  new Exception("请求失败");
        }

    }


}
