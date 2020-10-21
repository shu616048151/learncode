package com.shu.docker;

import cn.hutool.Hutool;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import pay.d9lab.api.model.BaseResponseModule;
import pay.d9lab.api.model.PrepayRequestModel;
import pay.d9lab.api.model.QueryRequestModel;
import pay.d9lab.api.model.RefundRequestModel;
import pay.d9lab.api.util.Api;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author shuxibing
 * @Date 2020/10/14 19:30
 * @Uint d9lab_2019
 * @Description:
 */
public class PayTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String,Object> map = new PrepayRequestModel()
                .app_id("APPPKCMlK1zFYmXmyn8HNU4BsDZo34OwtpyqZ")
                .pay_platform("WxPay")
                .trade_type("APP")
                .out_trade_no("700820865420")
                .title("可乐")
                .total_amount("0.019")
                .notify_url("https://pay.d9lab.net/pay/api/pay/notify/test")
                .open_id("oBiyl5EU1CzukzZusbMpbQ6Lr1UU")
                .creatSign("CPKgKku519MJGrqJ9IyS6ojRQRAXs0MS20autWBdy4AirOGdE0XTG2z2olcpqA3FOFq");  //生成随机字符串并加签
        //请求
        String response = Api.prepay(map);
        //接参
        BaseResponseModule responseModule = new BaseResponseModule(response);
        boolean success = responseModule.isSuccess();
        System.out.println(success);
        int code = responseModule.getCode();
        System.out.println(code);
        System.out.println(responseModule.getMessage());
        //获取结果键值对
        Map<String,Object> valueMap =  responseModule.getValueMap();
        for (Map.Entry<String,Object> entry:valueMap.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }
    }



    @Test
    public void aliPre() throws NoSuchAlgorithmException {
        Map<String,Object> map = new PrepayRequestModel()
                .app_id("APPPKCMlK1zFYmXmyn8HNU4BsDZo34OwtpyqZ")
                .pay_platform("AliPay")
                .trade_type("APP")
                .out_trade_no("700820865420")
                .title("可乐")
                .total_amount("0.019")
                .notify_url("https://pay.d9lab.net/pay/api/pay/notify/test")
                .open_id("oBiyl5EU1CzukzZusbMpbQ6Lr1UU")
//                .is_alipay_cert(true)
                .creatSign("CPKgKku519MJGrqJ9IyS6ojRQRAXs0MS20autWBdy4AirOGdE0XTG2z2olcpqA3FOFq");  //生成随机字符串并加签
        //请求
        String response = Api.prepay(map);
        //接参
        BaseResponseModule responseModule = new BaseResponseModule(response);
        boolean success = responseModule.isSuccess();
        System.out.println(success);
        int code = responseModule.getCode();
        System.out.println(code);
        System.out.println(responseModule.getMessage());
        //获取结果键值对
        Map<String,Object> valueMap =  responseModule.getValueMap();
        for (Map.Entry<String,Object> entry:valueMap.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }
    }

    @Test
    public void test() throws NoSuchAlgorithmException {

        //封装参数
        Map<String,Object> map = new QueryRequestModel()
                .app_id("APPPKCMlK1zFYmXmyn8HNU4BsDZo34OwtpyqZ")
                .out_trade_no("09861602593967006")
                .platform_out_trade_no("09861602593967006")
                .creatSign("CPKgKku519MJGrqJ9IyS6ojRQRAXs0MS20autWBdy4AirOGdE0XTG2z2olcpqA3FOFq");  //生成随机字符串并加签
        //请求
        String response = Api.query(map);
        //接参
        BaseResponseModule responseModule = new BaseResponseModule(response);
        System.out.println(responseModule.getMessage());
        //获取结果键值对
        Map<String,Object> valueMap =  responseModule.getValueMap();
        for (Map.Entry<String,Object> entry:valueMap.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }

    }


    @Test
    public void test1() throws NoSuchAlgorithmException {

        //封装参数
        Map<String,Object> map = new  RefundRequestModel()
                .app_id("APPPKCMlK1zFYmXmyn8HNU4BsDZo34OwtpyqZ")
                .out_trade_no("01651602681266713")
                .platform_out_trade_no("01651602681266713")
                .refund_amount("0.01")
                .creatSign("CPKgKku519MJGrqJ9IyS6ojRQRAXs0MS20autWBdy4AirOGdE0XTG2z2olcpqA3FOFq");  //生成随机字符串并加签
        //请求
        String response = Api.refund(map);
        //接参
        BaseResponseModule responseModule = new BaseResponseModule(response);

        System.out.println(JSONUtil.toJsonStr(responseModule));

    }



}
