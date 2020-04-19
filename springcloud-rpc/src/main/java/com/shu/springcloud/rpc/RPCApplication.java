package com.shu.springcloud.rpc;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shu.springcloud.rpc.service.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author shuxibing
 * @Date 2020/4/19 9:44
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients  //开启远程调用方式
@EnableHystrix
public class RPCApplication {
    public static void main(String[] args){
        SpringApplication.run(RPCApplication.class,args);
    }


    @Bean
    @LoadBalanced        // 开启负载均衡,循环调用多个同样的服务
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderFeign orderFeign;

    @HystrixCommand(fallbackMethod = "rpcOrderError")
    @RequestMapping("/rpcOrder")
    public String rpcOrder(){
        String forObject = restTemplate.getForObject("http://order-server/rpcOrder", String.class);
        return forObject;
    }

    public String rpcOrderError(){
        return "rpcOrderError";
    }

    @RequestMapping("/rpcOrderByfeign")
    public String rpcOrderByfeign(){
        String forObject = orderFeign.orderTest();
        return forObject;
    }

    @HystrixCommand
    @RequestMapping("/rpcOrderByfeign1")
    public String rpcOrderByfeign1(){
        String forObject = orderFeign.getOrder1("张三");
        return forObject;
    }
}
