package com.shu.elasticsearch.dao;

import com.shu.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author shuxibing
 * @date 2019/9/19 10:09
 * @uint d9lab
 * @Description:
 */
public interface UserDao extends ElasticsearchRepository<User,String> {
}
