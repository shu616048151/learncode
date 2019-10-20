package com.shu.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author shuxibing
 * @date 2019/9/20 12:03
 * @uint d9lab
 * @Description:
 */
@Document(indexName = "item",type = "item")
@Data
public class Item {
    @Id
    private Integer id;
    private String name;
}
