package com.shu.elasticsearch.dao;

import com.shu.elasticsearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author shuxibing
 * @date 2019/9/20 15:19
 * @uint d9lab
 * @Description:
 */
public interface ItemDao extends ElasticsearchRepository<Item,Integer> {
}
