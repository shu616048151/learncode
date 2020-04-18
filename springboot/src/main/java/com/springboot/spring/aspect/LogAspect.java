package com.springboot.spring.aspect;


import cn.hutool.json.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: zouy
 * Unit: D9lab
 * Date: 2018-07-27 19:53
 * aop可以做一些权限校验问题
 */
@Component
@Aspect
public class LogAspect {
    private static final Logger logger = Logger.getLogger(LogAspect.class);

    @Pointcut("@annotation(edu.whut.pocket.log.aspect.LogAnnotation)")
    private void authAccess() {
    }

    //这里写的为环绕触发,可自行根据业务场景选择@Before @After
    //触发条件为：(edu.whut.pocket.*.controller包下面所有类且)含有注解@LogAnnotation
    @Around(value = "authAccess() && @annotation(logAnnotation)", argNames = "pjp, logAnnotation")
    public Object doAroundMethod(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) throws Throwable {
        JSONObject jsonObject = new JSONObject();
        long startTime = System.currentTimeMillis();//开始时间
        jsonObject.put("method_starttime", startTime);

        //annotation信息
        String opType = logAnnotation.opType();
        int opUser = logAnnotation.opUser();
        String description = logAnnotation.description();


        //请求类,方法
        String classType = pjp.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        jsonObject.put("clazz", clazzName.substring(clazzName.lastIndexOf(".") + 1));
        String methodName = pjp.getSignature().getName();
        jsonObject.put("method", methodName);

        //请求参数名-值
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] paramsKey = methodSignature.getParameterNames();
        Object[] paramsValue = pjp.getArgs();
        JSONObject paramsJson = new JSONObject();
        for (int i = 0; i < paramsKey.length; i++) {
            paramsJson.put(paramsKey[i], paramsValue[i] == null ? "" : paramsValue[i].toString());
        }
        jsonObject.put("params", paramsJson.toString());

        //request对象
        HttpServletRequest request = (HttpServletRequest) paramsValue[0];
        jsonObject.put("user_id", opUser == -1 ? 0 :paramsValue[opUser]);

        //###################上面代码为方法执行前#####################
        Object result = pjp.proceed();//执行方法，获取返回参数
        //###################下面代码为方法执行后#####################
        jsonObject.put("result", result.toString());

        long endTime = System.currentTimeMillis();//结束时间
        double excTime = (double) (endTime - startTime) / 1000;
        jsonObject.put("method_timetaking", excTime);
        //如果是登录操作,需要重新put user_id

        return result;
    }

}
