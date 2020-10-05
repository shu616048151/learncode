package com.shu.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author shuxibing
 * @Date 2020/10/4 20:30
 * @Uint d9lab_2019
 * @Description:
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(type=IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
