package com.shu.base.util;


import com.shu.base.util.common.SmsPlatform;
import com.shu.base.util.common.SmsType;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: zouy
 * Unit: D9lab
 * Date: 2018-09-15 09:43
 */

public class Message implements Serializable{
    private String phone;
    private SmsType type;
    private SmsPlatform platform;
    private String code;
    private String text;
    private String content;
    private Date createTime;

    public Message() {
    }

    //发验证码快速模板
    public Message(String phone, String code, String content) {
        this.phone = phone;
        this.code = code;
        this.content = content + code;
        this.type = SmsType.验证码;
        this.platform = SmsPlatform.YiTD;
        this.text = "";
        this.createTime = new Date();
    }

    //普通方式
    public Message(String phone, SmsType type, SmsPlatform platform, String code, String text, String content) {
        this.phone = phone;
        this.type = type;
        this.platform = platform;
        this.code = code;
        this.text = text;
        this.content = content + (type.equals(SmsType.验证码) ? code :
                (type.equals(SmsType.普通短信) ? text : ""));
        this.createTime = new Date();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SmsType getType() {
        return type;
    }

    public void setType(SmsType type) {
        this.type = type;
    }

    public SmsPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SmsPlatform platform) {
        this.platform = platform;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
