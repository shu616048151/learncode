package com.shu.jpa.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author shuxibing
 * @date 2019/12/28 15:28
 * @uint d9lab
 * @Description: jpa的一些配置信息
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.shu.jpa.repostiory") //扫包和注解方式都可以实现
public class JpaConfig {
}
