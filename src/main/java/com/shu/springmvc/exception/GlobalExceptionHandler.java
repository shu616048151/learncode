package com.shu.springmvc.exception;

import com.shu.springmvc.vo.ResponseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuxibing
 * @date 2019/10/29 14:41
 * @uint d9lab
 * @Description: 全局异常处理方式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(RequestLimitException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    //返回错误码
    public Map hadndleRequestLimitException(RequestLimitException e){
        ResponseMap map = ResponseMap.getInstance();
        map.put("message",e.getMessage());
        map.put("code",HttpStatus.UNAUTHORIZED.value());
        return map;
    }



}
