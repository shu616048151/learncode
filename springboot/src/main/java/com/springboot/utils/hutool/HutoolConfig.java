package com.springboot.utils.hutool;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author shuxibing
 * @date 2019/10/4 16:15
 * @uint d9lab
 * @Description:
 */
@Configuration
public class HutoolConfig extends WebMvcConfigurerAdapter {
    //解决返回中文乱码

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
