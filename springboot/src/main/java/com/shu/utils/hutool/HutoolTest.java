package com.shu.utils.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author shuxibing
 * @date 2019/9/24 14:51
 * @uint d9lab
 * @Description:   hutool开发文档 http://hutool.mydoc.io/#text_319403
 */
public class HutoolTest {
    public static void main(String[] args){

    }

    @Test
    public void date(){
        Date date=new Date();
        FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getDefault());
        System.out.println(instance.format(date));
    }

    /**
     * 使用concurrenthashmap作为底层的存储容器
     */
    @Test
    public void cache(){
        FIFOCache<Object, Object> cache = CacheUtil.newFIFOCache(10);
        cache.put("name","zhangsan");
        cache.put("name","lisi");
        cache.put("age",18);
        System.out.println(cache.get("age"));
    }


    /**
     * 图片验证码
     */
    @Test
    public void crypto(){
        CaptchaUtil.createCircleCaptcha(20, 20);
    }


    /**
     * 转换类可以打印输出，非常好用
     */
    @Test
    public void ConvertTest(){
        long[] l={122,333,444};
        String[] shu={"zhangsan","lisi"};
        String s = Convert.toStr(shu);
        System.out.println(s);
    }


    /**
     * 身份证校验工具
     */
    @Test
    public void idCard(){
        String ID_18 = "321083197812162119";
        String ID_15 = "150102880730303";

        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        boolean valid15 = IdcardUtil.isValidCard(ID_15);

        //转换
        String convert15To18 = IdcardUtil.convert15To18(ID_15);
        System.out.println(convert15To18);
        Assert.assertEquals(convert15To18, "150102198807303035");

        //年龄
        DateTime date = DateUtil.parse("2017-04-10");

        int age = IdcardUtil.getAgeByIdCard(ID_18, date);
        System.out.println(age);
        Assert.assertEquals(age, 38);

        int age2 = IdcardUtil.getAgeByIdCard(ID_15, date);
        Assert.assertEquals(age2, 28);

        //生日
        String birth = IdcardUtil.getBirthByIdCard(ID_18);
        Assert.assertEquals(birth, "19781216");

        String birth2 = IdcardUtil.getBirthByIdCard(ID_15);
        Assert.assertEquals(birth2, "19880730");

        //省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);
        Assert.assertEquals(province, "江苏");

        String province2 = IdcardUtil.getProvinceByIdCard(ID_15);
        Assert.assertEquals(province2, "内蒙古");


    }

    /**
     * id生成工具
     */
    @Test
    public void Id(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println(id);
    }
    /**
     *邮件工具测试
     */
    @Test
    public void MailTest(){
        MailAccount mailAccount=new MailAccount();
        mailAccount.setFrom("shuxibing@126.com");
        mailAccount.setAuth(true);
        mailAccount.setPort(25);
        mailAccount.setHost("smtp.126.com");
        mailAccount.setUser("shuxibing@126.com");
        mailAccount.setPass("shu123456789");
        MailUtil.send(mailAccount,"616048151@qq.com", "测试", "shuxibing 给你发邮件了", false);
    }


    @Test
    public void httpTest(){
        String url="https://face.ceks100.com/group1/M01/73/82/cx2hu1mOYvCAYzC3AFzP43a0kF0803.jpg";
        HttpResponse  httpResponse= HttpRequest.get(url).execute();
        if (httpResponse.getStatus()==200){
            File file=new File("c:\\Users\\Administrator\\Desktop\\data\\temp.jpg");
            httpResponse.writeBody(file);
            System.out.println("返回成功");
        }else {
            System.out.println("请求失败");
        }
    }









}
