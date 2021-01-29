package com.shu.shiro.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author shuxibing
 * @date 2021/1/29 15:44
 * @uint d9lab
 * @Description:
 */
@Component
public class MySQLConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}