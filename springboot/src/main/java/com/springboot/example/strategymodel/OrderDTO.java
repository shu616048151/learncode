package com.springboot.example.strategymodel;

import java.math.BigDecimal;

/**
 * @author shuxibing
 * @date 2019/7/31 14:35
 * @uint d9lab
 * @Description:
 */
public class OrderDTO {
    private String code;
    private BigDecimal price;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
