package com.shu.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @author shuxibing
 * @date 2019/9/19 10:03
 * @uint d9lab
 * @Description:
 */
@Data
@Document(indexName = "myname",type ="user")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Integer sex;
}
