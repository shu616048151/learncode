package com.shu.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


/**
 * @author shuxibing
 * @date 2019/9/19 10:03
 * @uint d9lab
 * @Description:
 */
@Data
@Document(indexName = "myname",type ="user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Integer sex;
    private String description;
    private Date createTime;
    private Date updateTime;
}
