package com.shu.redis.antibrush.aspect;

import com.shu.redis.miaosha.exception.RequestLimitException;
import com.shu.redis.miaosha.util.IPUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author shuxibing
 * @date 2019/10/27 16:50
 * @uint d9lab
 * @Description:  RequestLimit接口处理类,采用redis做
 *
 * 使用滑动窗口限流方法
 * 优点：可以细粒度控制每个ip每个接口的访问量，非常简单，对于小流量的项目非常友好
 * 缺点：对于突发性的巨大流量，效果比较差
 */
@Component
@Aspect
public class RequestAspect {
    private static final Logger logger = Logger.getLogger(RequestAspect.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.shu.redis.antibrush.aspect.RequestLimit)")
    private void authAccess() {
    }

    //这里写的为环绕触发,可自行根据业务场景选择@Before @After
    //触发条件为：(edu.whut.pocket.*.controller包下面所有类且)含有注解@RequestLimit
    @Before(value = "authAccess() && @annotation(requestLimitAnnotation) ",argNames = "joinPoint,requestLimitAnnotation")
    public void doBeforeMethod(JoinPoint joinPoint, RequestLimit requestLimitAnnotation) throws Exception {

        //请求参数名-值
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object[] paramsValue = joinPoint.getArgs();

        String message=requestLimitAnnotation.message();
        int maxCount = requestLimitAnnotation.maxCount();
        int second = requestLimitAnnotation.second();
//        long expireTime = requestLimitAnnotation.expireTime();
        //正是上线可以调整
        long expireTime = 2;

        //request对象
        HttpServletRequest request = (HttpServletRequest) paramsValue[0];
        //对ip做校验
        String ip = IPUtil.getIpAddress(request);

        String requestURI = request.getRequestURI();

        String key="requestLimit:" + requestURI + "-" + ip;

        Object object = redisTemplate.opsForValue().get("riskIp:" + ip);
        if (object!=null){
            Boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey==true){
                //防止出现过期时间为-1的情况，强制删除
                redisTemplate.delete(key);
            }
            //用于提示信息
            throw new RequestLimitException(message);
        }
        if (object==null) {
            //利用redis的存活时间自动对request的请求进行检验
            Integer requestCount = (Integer) redisTemplate.opsForValue().get(key);
            if (requestCount == null||requestCount==0) {
                logger.info("重新加入redisIp:"+ip);
                redisTemplate.opsForValue().set(key, 1, second, TimeUnit.SECONDS);
                return;
            }else {
                if (requestCount >= maxCount) {
                    //请求的时间超时，将这个ip关进小黑屋
                    long timeMillis = System.currentTimeMillis();
                    redisTemplate.opsForValue().set("riskIp:" + ip, timeMillis, expireTime, TimeUnit.SECONDS);
                    throw new RequestLimitException(message);
                }else {
                    //开始放行
                    redisTemplate.opsForValue().increment(key);
                    logger.info("放行ip:"+ip);
                    Long expire = redisTemplate.getExpire(key);
                    logger.info("millexpire:"+expire);
                }
            }
        }

    }

}
