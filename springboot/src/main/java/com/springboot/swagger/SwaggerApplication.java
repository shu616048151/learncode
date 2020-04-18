package com.springboot.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author shuxibing
 * @date 2019/9/27 19:24
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2//必须存在
@RestController
@Api(tags = "启动类",description = "一个启动类",produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerApplication {
    public static void main(String[] args){
        SpringApplication.run(SwaggerApplication.class,args);
    }


    /**
     * produces 是返回信息的数据格式和编码方式
     * @param name
     * @return
     */
    @ApiOperation("测试swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value = "姓名",paramType ="form",dataType = "string",required = true)
    })
    @RequestMapping(value = "/testswagger",method = RequestMethod.POST)
    public String testswagger(String name) {
        System.out.println(name);
        return "hello,"+name;
    }
}
