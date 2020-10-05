package com.shu.base.util;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author: zouy
 * Unit: D9lab
 * Date: 2018-09-15 10:40
 */
@Slf4j
public class YiTDSmsUtil {
    /*
    //旧的
    private static final String SMS_SEND_URL = "http://121.40.78.35:8080/yxthttp/sms/sendUTF8";
    private static final String USER_ID = "11086";
    private static final String SMS_CLIENT = "ceks";
    private static final String SMS_PASSWORD = "7TSetw";*/
//    //新的
//    private static final String SMS_SEND_URL = "http://api.yitd.cn/sms/sendUTF8";
//    private static final String USER_ID = "10066";
//    private static final String SMS_CLIENT = "3ceks2";
//    private static final String SMS_PASSWORD = "JXR3n4";  //以前的密码：hcWY5S


    // 新的
    private static final String SMS_SEND_URL = "http://open.yitd.cn/sms/sendUTF8";
    private static final String USER_ID = "434704";
    private static final String SMS_CLIENT = "434704";
    private static final String SMS_PASSWORD = "wxwwyv";

    public static final Logger logger = Logger.getLogger(YiTDSmsUtil.class);

    private static LinkedBlockingQueue<Message> smsLinkedBlockingQueue = new LinkedBlockingQueue<>();

    private void initSmsUtil() {
        new Thread(new YiTDSmsUtil.SendSmsRunnable()).start();
    }

    private class SendSmsRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                Message message;
                try {
                    message = smsLinkedBlockingQueue.take();
                    //发短信
                    Map<String, Object> result = sendMessage(message);
                    //map转json
//                    JSONObject jsonObject = new JSONObject(result);
//                    //日志记录，发给kafka
//                    if (result != null) {
//                        KafkaProducerUtil.producer("logMessage", "sendMessage", jsonObject.toString());
//                    }
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    //后台触发
    public static boolean send(Message msg) {
//        if (!MessageUtil.isPhone(msg.getPhone())) {
//            return false;
//        }
        try {
            smsLinkedBlockingQueue.put(msg);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }


    @Test
    public void test1(){
        Message message = new Message();
        message.setContent("【变购链】我是树先生，为你发这条短信是了答谢你的好意");
        message.setPhone("17786243502");
        sendMessage(message);
    }
    //具体发短信
    private static Map<String, Object> sendMessage(Message msg) {
        //数据预处理
        String phone = msg.getPhone();
        System.out.println(phone);
        String content = msg.getContent();

        try{
            HttpRequest post = HttpUtil.createPost(SMS_SEND_URL);
            post.form("action","send");
            post.form("userid",USER_ID);
            post.form("account",SMS_CLIENT);
            post.form("password",SMS_PASSWORD);
            post.form("mobile",phone);
            post.form("content",content);

            HttpResponse execute = post.execute();
            Map<String, Object> map = xmlToMap( execute.bodyStream());
                System.out.println("打印map的值");
            for(String s : map.keySet()){
                System.out.println(s+" "+map.get(s));
            }
            //额外的返回参数
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("phone", msg.getPhone());
//            map.put("type", msg.getType().toString());
//            map.put("platform", msg.getPlatform().toString());
//            map.put("code", msg.getCode());
//            map.put("text", msg.getText());
//            map.put("content", msg.getContent());
//            map.put("createTime", msg.getCreateTime().getTime());
            return map;
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
                e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> xmlToMap(InputStream input) throws IOException {
        HashMap map = new HashMap();
        SAXReader reader = new SAXReader();
        InputStream ins = input;

        Document doc = null;

        try {
            doc = reader.read(ins);
            Element e1 = doc.getRootElement();
            List list = e1.elements();
            Iterator var7 = list.iterator();

            while (var7.hasNext()) {
                Element e = (Element) var7.next();
                map.put(e.getName(), e.getText());
            }

            HashMap var16 = map;
            return var16;
        } catch (DocumentException e) {
            e.printStackTrace();
//            log.error(e.getMessage(), e);
        } finally {
            ins.close();
        }

        return null;
    }

}
