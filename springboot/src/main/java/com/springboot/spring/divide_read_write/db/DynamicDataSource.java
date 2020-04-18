package com.springboot.spring.divide_read_write.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author shuxibing
 * @date 2019/8/10 11:42
 * @uint d9lab
 * @Description:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
