package com.shu.springmvc.webexception;

import com.shu.springmvc.vo.ResponseMap;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static Logger logger = Logger.getLogger(WebExceptionHandler.class);
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResponseMap map = ResponseMap.getInstance();
        map.put("e", e.getMessage());
        return map.putFailure("could_not_read_json", "请求参数解析失败", HttpStatus.BAD_REQUEST.value());
    }
    
    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResponseMap map = ResponseMap.getInstance();
        map.put("e", e.getMessage());
        return map.putFailure("request_method_not_supported", "不支持当前请求方法", HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Map handleHttpMediaTypeNotSupportedException(Exception e) {
        ResponseMap map = ResponseMap.getInstance();
        map.put("e", e.getMessage());
        return map.putFailure("content_type_not_supported", "不支持当前媒体类型", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map handleException(Exception e) {
        ResponseMap map = ResponseMap.getInstance();
        e.printStackTrace();
        map.put("e", e.getMessage());
        return map.putFailure("server_error", "服务运行异常", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}