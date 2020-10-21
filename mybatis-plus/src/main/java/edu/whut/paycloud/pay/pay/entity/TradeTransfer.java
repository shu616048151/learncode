package edu.whut.paycloud.pay.pay.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuxibing
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TradeTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String platformAppId;

    private String companyName;

    private String title;

    private String transAmount;

    /**
     * 支付平台（维信，支付宝）
     */
    private String platform;

    /**
     * 支付场景
     */
    private String tradeType;

    private String transTradeNo;

    /**
     * 平台单号
     */
    private String platformTransTradeNo;

    /**
     * 第三方公司单号
     */
    private String tradeNo;

    /**
     * 支付状态
     */
    private String payStatus;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private LocalDateTime payTime;

    private Integer isDeleted;

    /**
     * 入参
     */
    private String inParam;

    /**
     * 第三平台传过来的参数
     */
    private String outParam;


}
