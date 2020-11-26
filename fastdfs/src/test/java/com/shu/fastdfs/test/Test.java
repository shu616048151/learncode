package com.shu.fastdfs.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.UUID;

/**
 * @Author shuxibing
 * @Date 2020/11/24 17:10
 * @Uint d9lab_2019
 * @Description:
 */
public class Test {



//    code=073Ou31w3dZGlV2Oqs1w32zsJa1Ou315
//    code=073IZqGa1Hb41A0wAUFa1mx0YG2IZqG5
//    GET https://yjsxx.whut.edu.cn/wx/checkRegister HTTP/1.1
//    Host: yjsxx.whut.edu.cn
//    Connection: keep-alive
//    Cookie: JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376
//    User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat
//    X-Tag: flyio
//    content-type: application/json
//    Referer: https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html
//    Accept-Encoding: gzip, deflate, br



    public static void main(String[] args){
        HttpRequest get = HttpUtil.createGet("https://yjsxx.whut.edu.cn/wx/checkRegister");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","application/json");
        get.header("Accept-Encoding","gzip, deflate, br");

        get.cookie("JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376");

        HttpResponse execute = get.execute();
        System.out.println(execute.body());

    }
//
//    POST https://yjsxx.whut.edu.cn/wx/listPage HTTP/1.1
//    Host: yjsxx.whut.edu.cn
//    Connection: keep-alive
//    Content-Length: 2
//    Cookie: JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376
//    User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat
//    X-Tag: flyio
//    content-type: application/json
//    Referer: https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html
//    Accept-Encoding: gzip, deflate, br


    @org.junit.Test
    public void test(){
        HttpRequest get = HttpUtil.createPost("https://yjsxx.whut.edu.cn/wx/listPage");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("Content-Length","2");

        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html");
        get.header("Accept-Encoding","gzip, deflate, br");

        get.cookie("JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376");

        HttpResponse execute = get.execute();
        System.out.println(execute.body());
    }







//    POST https://yjsxx.whut.edu.cn/wx/monitorRegister HTTP/1.1
//    Host: yjsxx.whut.edu.cn
//    Connection: keep-alive
//    Content-Length: 374
//    Cookie: JSESSIONID=aa1133b8-e720-49fe-8533-d1ee8f207009
//    User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat
//    X-Tag: flyio
//    content-type: application/json
//    Referer: https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html
//    Accept-Encoding: gzip, deflate, br
//
//    {"diagnosisName":"","relationWithOwn":"","currentAddress":"湖北省武汉市江岸区沿江大道188号","remark":"","healthInfo":"正常","isDiagnosis":0,"isFever":0,"isInSchool":0,"isLeaveChengdu":0,"isSymptom":0,"temperature":"36°C以下","noonTemperature":"36°C以下","eveningTemperature":"36°C以下","province":"湖北省","city":"武汉市","county":"江岸区"}


    @org.junit.Test
    public void test2(){
        String param=" {\"diagnosisName\":\"\",\"relationWithOwn\":\"\",\"currentAddress\":\"湖北省武汉市洪山区武汉理工大学鉴湖校区\",\"remark\":\"\",\"healthInfo\":\"正常\",\"isDiagnosis\":0,\"isFever\":0,\"isInSchool\":0,\"isLeaveChengdu\":0,\"isSymptom\":0,\"temperature\":\"36°C以下\",\"noonTemperature\":\"36°C以下\",\"eveningTemperature\":\"36°C以下\",\"province\":\"湖北省\",\"city\":\"武汉市\",\"county\":\"洪山区\"}";
        HttpRequest get = HttpUtil.createPost("https://yjsxx.whut.edu.cn/wx/monitorRegister");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("Content-Length","374");

        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html");
        get.header("Accept-Encoding","gzip, deflate, br");
        get.header("content-type","application/json");
        //舒细兵
        get.cookie("JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376");
        get.body(param);

        System.out.println(get);

        HttpResponse execute = get.execute();
        System.out.println(execute.body());
    }


    @org.junit.Test
    public void test111(){
        String param=" {\"diagnosisName\":\"\",\"relationWithOwn\":\"\",\"currentAddress\":\"湖北省武汉市洪山区武汉理工大学鉴湖校区\",\"remark\":\"\",\"healthInfo\":\"正常\",\"isDiagnosis\":0,\"isFever\":0,\"isInSchool\":0,\"isLeaveChengdu\":0,\"isSymptom\":0,\"temperature\":\"36°C以下\",\"noonTemperature\":\"36°C以下\",\"eveningTemperature\":\"36°C以下\",\"province\":\"湖北省\",\"city\":\"武汉市\",\"county\":\"洪山区\"}";
        HttpRequest get = HttpUtil.createPost("https://yjsxx.whut.edu.cn/wx/monitorRegister");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("Content-Length","374");

        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html");
        get.header("Accept-Encoding","gzip, deflate, br");
        get.header("content-type","application/json");
        //陈浩然
        get.cookie("sessionId=b08a06eb-6bfc-4e04-99cc-387c86091ee4");
        get.body(param);

        System.out.println(get);

        HttpResponse execute = get.execute();
        System.out.println(execute.body());
    }



    @org.junit.Test
    public void test3(){
        String param=" {\"diagnosisName\":\"\",\"relationWithOwn\":\"\",\"currentAddress\":\"湖北省武汉市江岸区沿江大道188号\",\"remark\":\"\",\"healthInfo\":\"正常\",\"isDiagnosis\":0,\"isFever\":0,\"isInSchool\":0,\"isLeaveChengdu\":0,\"isSymptom\":0,\"temperature\":\"36°C以下\",\"noonTemperature\":\"36°C以下\",\"eveningTemperature\":\"36°C以下\",\"province\":\"湖北省\",\"city\":\"武汉市\",\"county\":\"江岸区\"}";
        HttpRequest get = HttpUtil.createPost("https://yjsxx.whut.edu.cn/wx/monitorRegister");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("Content-Length","374");

        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html");
        get.header("Accept-Encoding","gzip, deflate, br");
        get.header("content-type","application/json");

        //李婷婷
        get.cookie("JSESSIONID=ded7c530-5f4f-4df4-ac38-46abd2643e7b");
        get.body(param);

        System.out.println(get);

        HttpResponse execute = get.execute();
        System.out.println(execute.body());
    }


    @org.junit.Test
    public void test4(){
        HttpRequest get = HttpUtil.createPost("https://yjsxx.whut.edu.cn/wx/listPage");
        get.header("Host","yjsxx.whut.edu.cn");
        get.header("Connection","keep-alive");
        get.header("X-Tag","flyio");
        get.header("Content-Length","2");

        get.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        get.header("Referer","https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html");
        get.header("Accept-Encoding","gzip, deflate, br");

        get.header("Cookie","JSESSIONID=5d9bb0fe-f082-44b1-99f0-3ebf6d6c4376");

        HttpResponse execute = get.execute();
        System.out.println(execute.body());
    }




//    POST https://yjsxx.whut.edu.cn/wx/api/login/bindUserInfo HTTP/1.1
//    Host: yjsxx.whut.edu.cn
//    Connection: keep-alive
//    Content-Length: 208
//    Cookie: JSESSIONID=ef3bafab-7a40-4fe1-a1a2-8740755dda55
//    User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat
//    X-Tag: flyio
//    content-type: application/json
//    Referer: https://servicewechat.com/wx225eb50c34f6f98e/6/page-frame.html
//    Accept-Encoding: gzip, deflate, br
//
//    {"sn":"1049731904357","idCard":"155731","avatarurl":"https://thirdwx.qlogo.cn/mmopen/vi_32/dicl9nic71po0pJvnnA3maecCFVzlGcgjxVibAeSaVBVdu2ribm72BF0uaPGThUTcs6jEAj2OqiaKSXdqYjAba6PoGQ/132","nickname":"时光"}




//    PVGTTQTUQLEFRMDE
    @org.junit.Test
    public void mail(){
        try {
            MailAccount account = new MailAccount();
            account.setHost("smtp.qq.com");
            account.setPort(587);
            account.setAuth(true);
            account.setFrom("616048151@qq.com");
            account.setUser("616048151@qq.com");
            account.setPass("eqcyiwyzymqvbdhc"); //邮箱设置获取到的授权码
            MailUtil.send(account, CollUtil.newArrayList("shuxibing@126.com"),
                    "sss", "aaaa", false);
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void mail1(){
        try {
            MailAccount account = new MailAccount();
            account.setHost("smtp.126.com");
            account.setPort(25);
            account.setAuth(true);
            account.setFrom("shuxibing@126.com");
            account.setUser("shuxibing@126.com");
            account.setPass("PVGTTQTUQLEFRMDE"); //邮箱设置获取到的授权码
            MailUtil.send(account, CollUtil.newArrayList("616048151@qq.com"),
                    "体温填报通知", "体温填报成功", false);
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
