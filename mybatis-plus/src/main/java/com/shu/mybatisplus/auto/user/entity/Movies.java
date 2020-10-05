package com.shu.mybatisplus.auto.user.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuxibing
 * @since 2020-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Movies implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String year;

    private String country;

    private String lan;

    private String doubanLink;

    private Double doubanScore;

    private String introduce;

    private String mainActor;

    private String downloadUrl;

    private String imgUrl;


}
