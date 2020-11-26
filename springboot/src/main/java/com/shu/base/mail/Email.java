package com.shu.base.mail;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

/**
 * @Author shuxibing
 * @Date 2020/11/26 12:36
 * @Uint d9lab_2019
 * @Description:
 */
public class Email {

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
