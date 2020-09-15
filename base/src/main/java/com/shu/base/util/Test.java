package com.shu.base.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shuxibing
 * @Date 2020/9/11 10:00
 * @Uint d9lab_2019
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        for (int i=0;i<1000;i++){
//            HttpRequest post = HttpUtil.createPost("https://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text=DPK210157871350");
//            HttpResponse execute = post.execute();
//            if (execute.getStatus() == 200){
//                System.out.println(execute.getCookies().toString());
//                System.out.println(execute.body());
//            }
//        }


//
//        HttpRequest post = HttpUtil.createGet("https://www.kuaidi100.com/query?type=zhongtong&postid=75379117524686&id=19&valicode=&temp="+Math.random()+"&phone=");
////            post.cookie("sortStatus=0; WWWID=WWWA886A4D1B7117660924847CB90CB23B7; Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c=1599809614,1599809709,1599809718,1599811202; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c=1599811202");
//            post.cookie("csrftoken=RxMcO-jfbQzy4CTxqrjZ4GEFWW7v29dPE_wj4DfkFrg; sortStatus=0; WWWID=WWWA886A4D1B7117660924847CB90CB23B7; Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c=1599811202,1599812148,1599813033,1599813132; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c=1599815676");
//
//            Map<String,String> map=new HashMap<>();
//            map.put("Host","www.kuaidi100.com");
//            post.addHeaders(map);
//
//            HttpResponse execute = post.execute();
//
//              System.out.println(execute.getStatus());
//            if (execute.getStatus() == 200){
//                System.out.println(execute.getCookieStr());
//                System.out.println(execute.body());
//
//
//            }

    }


    @org.junit.Test
    public void test() throws Exception {
        KdniaoTrackQueryAPI api=new KdniaoTrackQueryAPI();

        String LogisticCode="DPK210157871350";

        String requestData= "{'OrderCode':'','CustomerName':'','ShipperCode':'','LogisticCode':'" + LogisticCode + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", api.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", "test1671622");
        params.put("RequestType", "8002");
        String dataSign=api.encrypt(requestData, "8c773ad2-6ec9-4d79-a472-8377f1f544d0", "UTF-8");
        params.put("DataSign", api.urlEncoder(dataSign, "UTF-8"));

        String result=api.sendPost("http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx", params);

        System.out.println(result);
    }

}
