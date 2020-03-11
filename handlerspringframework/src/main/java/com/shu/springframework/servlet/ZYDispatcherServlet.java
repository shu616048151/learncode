package com.shu.springframework.servlet;


import com.shu.springframework.annotation.*;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.util.SystemPropertyUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/3/10 21:57
 * @Uint d9lab_2019
 * @Description:
 */
public class ZYDispatcherServlet extends HttpServlet {

    private Map<String,Object> ioc=new HashMap<>();

    private Properties p=new Properties();

    private List<String> classNames=new ArrayList<>();

    private Map<String,Method> handlerMapping=new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req,resp);
    }

    private <parameterType> void doDispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (handlerMapping.isEmpty()){return;}

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url=url.replace(contextPath,"").replaceAll("/+","/");

        if (!handlerMapping.containsKey(url)){
            response.getWriter().write("404");
            return;
        }

        //request传入的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Method method = handlerMapping.get(url);

        //获取参数类型列表
        Class<?>[] parameterTypes = method.getParameterTypes();


        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        System.out.println("parameterTypes:"+parameterTypes.length);
        System.out.println("parameterAnnotations:"+parameterAnnotations.length);


        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        //参数值
        Object[] parameterValues=new Object[parameterTypes.length];

        for (int i=0;i<parameterTypes.length;i++){
            Class<?> parameterType = parameterTypes[i];
            if (parameterType==HttpServletRequest.class){
                parameterValues[i]=request;
                continue;
            } else if (parameterType==HttpServletResponse.class){
                parameterValues[i]=response;
                continue;
            } else{
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                if (parameterAnnotation.length>0){
                    for (Annotation annotation : parameterAnnotation) {
                        if (annotation instanceof ZYRequestParam){
                            ZYRequestParam zyRequestParam = (ZYRequestParam) annotation;
                            String name = zyRequestParam.value();
                            System.out.println(name);
                            System.out.println(parameterMap.get(name));
                            String value = Arrays.toString(parameterMap.get(name)).replaceAll("\\[|\\]","")
                                    .replaceAll(",\\s",",");
                            parameterValues[i]=value;

                            break;
                        }
                    }
                } else {
                    System.out.println(parameterNames[i]);
                    String value=Arrays.toString(parameterMap.get(parameterNames[i])).replaceAll("\\[|\\]","")
                            .replaceAll(",\\s",",");
                    parameterValues[i]=value;
                }



            }

        }
            try {
                String beanName = toFirstLowerCase(method.getDeclaringClass().getSimpleName());

                method.invoke(ioc.get(beanName),parameterValues);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //初始化Servlet
        //第一步加载web.xml配置文件
        DoLoadConfig(config.getInitParameter("contextConfigLocation"));
        //第二步扫描相关的包
        DoScanner(p.getProperty("scanPackage"));
        //第三步 初始化相关实例
        DoInstance();
        //第四步 依赖注入对象
        DoAutowired();
        //第五步  构造handlermapping 执行方法
        System.out.println("DoInitHandlerMapping 前");
        DoInitHandlerMapping();
        System.out.println("DoInitHandlerMapping 后");
    }

    private void DoInitHandlerMapping() {
        if (ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();

            if (!clazz.isAnnotationPresent(ZYController.class)){return;}

            String baseUrl="";
            if (clazz.isAnnotationPresent(ZYRequestMapping.class)){
                ZYRequestMapping annotation = clazz.getAnnotation(ZYRequestMapping.class);
                baseUrl = annotation.value();
                System.out.println("baseUrl:"+baseUrl);
            }

            Method[] methods = clazz.getMethods();

            for (Method method : methods) {
                if ( method.isAnnotationPresent(ZYRequestMapping.class)){
                    ZYRequestMapping annotation = method.getAnnotation(ZYRequestMapping.class);
                    String url = ("/"+baseUrl+"/"+annotation.value()).replaceAll("/+","/");
                    handlerMapping.put(url,method);
                    System.out.println("mapping:"+url);

                }
            }
        }
    }

    private void DoAutowired() {
        if (ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();

            for (Field field:declaredFields){
                if (!field.isAnnotationPresent(ZYAutowired.class)){
                    return;
                }
                ZYAutowired annotation = field.getAnnotation(ZYAutowired.class);
                String beanName = annotation.value().trim();
                if ("".equals(beanName)){
                    beanName=field.getName();
                }

                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }

    private void DoInstance() {
        if (classNames.size()==0){
            return;
        }
        try {
            for (String className:classNames){
                Class<?> clazz=Class.forName(className);
                if (clazz.isAnnotationPresent(ZYController.class)){
                    String beanName = toFirstLowerCase(clazz.getSimpleName());
                    ioc.put(beanName,clazz.newInstance());
                }else if (clazz.isAnnotationPresent(ZYService.class)){
                    ZYService zyService = clazz.getAnnotation(ZYService.class);
                    String beanName = zyService.value();
                    if (!"".equals(beanName.trim())){
                        ioc.put(beanName,clazz.newInstance());
                        continue;
                    }

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class i:interfaces){
                         beanName = toFirstLowerCase(i.getSimpleName());
                        ioc.put(beanName,clazz.newInstance());
                    }
                }else {
                    continue;
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void DoScanner(String scanPackage) {
        URL resource = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File dir=new File(resource.getFile());
        for (File file:dir.listFiles()){

            if (file.isDirectory()){
               DoScanner(scanPackage+"."+file.getName());
            }else {
                classNames.add(scanPackage+"."+file.getName().replaceAll(".class","").trim());
            }

        }

    }

    private void DoLoadConfig(String contextConfigLocation) {
        System.out.println(contextConfigLocation);
        InputStream ls=null;
        try {
             ls = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            p.load(ls);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=ls){
                try {
                    ls.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String toFirstLowerCase(String str){
        char[] chars = str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);

    }
}
