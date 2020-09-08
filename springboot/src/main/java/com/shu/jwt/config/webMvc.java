package com.shu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author shuxibing
 * @Date 2020/9/8 14:43
 * @Uint d9lab_2019
 * @Description:
 */
@Configuration
public class webMvc extends WebMvcConfigurerAdapter {


    //拦截器处理
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createJwtInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


    @Bean
    public JwtInterceptor createJwtInterceptor(){
        return new JwtInterceptor();
    }
}
