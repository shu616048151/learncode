package com.shu.fastdfs.video;

import lombok.Data;

import java.util.Date;

/**
 * @Author shuxibing
 * @Date 2020/11/3 20:49
 * @Uint d9lab_2019
 * @Description:
 */
@Data
public class Bili {
    Integer id;
    Integer comment;
    Integer typeid;
    String title;
    String play;
    String pic;
    String description;
    String copyright;
    String review;
    String author;
    Long mid;
    Long created;
    String length;
    String url;
    Integer video_review;
    Long aid;
    Date createTime;
    Date uploadTime;
    String createdStr;
}
