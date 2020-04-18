package com.shu.springmvc.controller;

import com.shu.springmvc.exception.RequestLimitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;


/**
 * @author shuxibing
 * @date 2020/2/11 11:41
 * @uint d9lab
 * @Description:
 */
@RestController
@RequestMapping(value = "/springmvc")
public class SpringmvcController {

    private static final Logger log= LoggerFactory.getLogger(SpringmvcController.class);

    @RequestMapping(value = "/test")
    public void test()throws  Exception{
        throw new RequestLimitException("访问被限制");
    }
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(MultipartHttpServletRequest request)throws  Exception{
        Iterator<String> fileNames = request.getFileNames();
        log.info("收到请求");
        while (fileNames.hasNext()){
            String fileName = fileNames.next();
            log.info("fileName:"+fileName);
            MultipartFile file = request.getFile(fileName);
            log.info("file大小："+file.getSize());
        }
        return "默认返回信息";

    }


}
