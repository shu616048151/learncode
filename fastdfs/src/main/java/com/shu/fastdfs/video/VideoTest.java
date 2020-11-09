package com.shu.fastdfs.video;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Calendar;
import java.util.Date;


/**
 * @Author shuxibing
 * @Date 2020/11/2 17:11
 * @Uint d9lab_2019
 * @Description:
 */
public class VideoTest {
    public static void main(String[] args) throws Exception {
        String url="https://api.bilibili.com/x/space/arc/search?mid=37663924&ps=100&tid=0&pn=1&keyword=&order=pubdate&jsonp=jsonp";
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
//        System.out.println(get.execute().body());
        JSONObject jsonObject=JSONObject.parseObject(response.body());
        JSONObject list = jsonObject.getJSONObject("data").getJSONObject("list");
        JSONObject page = jsonObject.getJSONObject("data").getJSONObject("page");


        JSONArray vlist = list.getJSONArray("vlist");
        for (int i=0;i<vlist.size();i++){
            JSONObject json = vlist.getJSONObject(i);
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(json.getLong("created")*1000);
            System.out.println(calendar.getTimeInMillis());
            Bili bili=new Bili();
            bili.setUrl("https://www.bilibili.com/video/av"+json.getInteger("aid"));
            bili.setAid(json.getLongValue("aid"));
            bili.setTitle(json.getString("title"));
            bili.setPlay(json.getString("play"));
            bili.setUploadTime(calendar.getTime());
            bili.setLength(json.getString("length"));
            bili.setDescription(json.getString("description"));
            bili.setMid(json.getLongValue("mid"));
            bili.setComment(json.getIntValue("comment"));
            bili.setTypeid(json.getIntValue("typeid"));
            bili.setAuthor(json.getString("author"));
            bili.setCreateTime(new Date());
            bili.setPic(json.getString("pic"));

            JDBCUtil.insertBili(bili);
            System.out.println(i+"    "+vlist.get(i));
            System.out.println(JSONUtil.toJsonStr(bili));
            System.out.println("-----------------------");
        }


//        System.out.println(list);
//        System.out.println(page);

//        Connection conn=Jsoup.connect(url).timeout(30000);
//        Document doc=conn.header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
//        System.out.println(doc.toString());


    }


    @Test
    public void test() throws Exception {
        Long lastTime=1601184147L;
        while ( lastTime != null){
            String url="https://www.ixigua.com/api/videov2/author/video?_signature=_sdfsdfggafffgwee4e&author_id=5778035052&type=video&max_time="+lastTime;
            System.out.println(url);
            HttpRequest get = HttpUtil.createGet(url);
            get.header("referer","https://www.ixigua.com/home/5778035052/");

            //ip代理
//            SocketAddress address = new InetSocketAddress("195.7.8.10", 8080	);
//            SocketAddress address = new InetSocketAddress("202.150.151.85", 3128	);
//            SocketAddress address = new InetSocketAddress("163.125.223.136", 8088);
//            SocketAddress address = new InetSocketAddress("27.43.186.110", 9999);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
//            get.setProxy(proxy);

            Thread.sleep(1000);


            HttpResponse response = get.execute();

            System.out.println(response);
            JSONObject jsonObject=JSONObject.parseObject(response.body());
            System.out.println(response.getStatus());


            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("data");
            for (int i=0 ;i <list.size();i++){
                JSONObject json = list.getJSONObject(i);

                Calendar calendar=Calendar.getInstance();
                calendar.setTimeInMillis(json.getLong("publish_time")*1000);
                Bili bili=new Bili();
                bili.setUrl(json.getString("display_url"));
                bili.setAid(json.getLongValue("group_id"));
                bili.setTitle(json.getString("title"));
                bili.setPlay(json.getJSONObject("video_detail_info").getString("video_watch_count"));
                bili.setUploadTime(calendar.getTime());
    //            bili.setLength(json.getString("length"));
                bili.setDescription(json.getString("abstract"));
                bili.setMid(json.getJSONObject("user_info").getLongValue("user_id"));
                bili.setComment(json.getIntValue("comment"));
    //            bili.setTypeid(json.getIntValue("typeid"));
                bili.setAuthor(json.getJSONObject("user_info").getString("name"));
                bili.setCreateTime(new Date());
                bili.setPic(json.getJSONObject("video_detail_info").getJSONObject("detail_video_large_image").getString("url"));

                JDBCUtil.insertXiGua(bili);
                if (list.size() == 30 && i == list.size()-1 ){
                    lastTime=json.getLong("publish_time");
                    System.out.println("publish_time:"+json.getLong("publish_time"));
                }else {
                    lastTime=null;
                }
                System.out.println("----------------------------------------");
            }
        }
//        System.out.println(get.execute().body());
    }
}
