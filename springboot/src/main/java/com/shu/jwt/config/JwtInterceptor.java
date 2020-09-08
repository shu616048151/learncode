package com.shu.jwt.config;

import com.shu.jwt.JwtTest;
import com.shu.jwt.annoation.PassLogin;
import com.shu.jwt.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author shuxibing
 * @Date 2020/9/8 14:45
 * @Uint d9lab_2019
 * @Description:
 */
public class JwtInterceptor implements HandlerInterceptor {

    //拦截器处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
       String token = request.getHeader("token");
       JwtTest jwtTest=new JwtTest();
       try {
            Claims claims = jwtTest.parseJWT(token);
            String audience = claims.getAudience();
            if (audience != null){
                return true;
            }
       }catch (Exception e){
           System.out.println("出现异常");
       }
       if (!handlerMethod.hasMethodAnnotation(PassLogin.class)){
           return false;

       }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
