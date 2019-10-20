package com.shu.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author shuxibing
 * @date 2019/9/19 9:32
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication()
@EnableElasticsearchRepositories(basePackages = "com.shu.elasticsearch.dao")
public class ElasticSearchApp {
    public static void main(String[] args){
        SpringApplication.run(ElasticSearchApp.class,args);
    }
}
