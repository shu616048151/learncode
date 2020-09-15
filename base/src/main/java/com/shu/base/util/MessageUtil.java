package com.shu.base.util;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Author: zouy
 * Unit: D9lab
 * Date: 2018-09-15 10:40
 * 封装一层，便于在多个短信平台切换
 */

public class MessageUtil {
    public static final Logger logger = Logger.getLogger(MessageUtil.class);

    //判断是否是有效的手机号
    public static boolean isPhone(String phone){
        if(phone == null) return false;
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[0-9]))\\d{8}$";
        return phone.trim().matches(regex);
    }

    //判断是否是6位数字验证码
    public static boolean isSixCode(String code){
        return code != null && code.trim().matches("^\\d{6}$");
    }

}
