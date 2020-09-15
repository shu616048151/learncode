package com.shu.base.util;

import cn.hutool.crypto.SecureUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author shuxibing
 * @date 2019/10/10 10:45
 * @uint d9lab
 * @Description:  依赖hutool工具类模块，功能将更加强大
 */
public class WxPayKit {
    private static final String FIELD_SIGN = "sign";
    private static final String FIELD_SIGN_TYPE = "sign_type";

    /**
     * 微信下单 map to xml
     *
     * @param params Map 参数
     * @return xml 字符串
     */
    public static String toXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 针对支付的 xml，没有嵌套节点的简单处理
     *
     * @param  xml 字符串
     * @return 转化后的 Map
     */
    public static Map<String, String> toMap(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }

    /**
     * 组装签名的字段
     *
     * @param params     参数
     * @return {String}
     */
    public static String packageSign(Map<String, String> params) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 生成签名
     *
     * @param params     需要签名的参数
     * @param partnerKey 密钥
     * @return 签名后的数据
     */
    public static String createSign(Map<String, String> params, String partnerKey) throws NoSuchAlgorithmException {
        // 生成签名前先去除sign
        params.remove(FIELD_SIGN);
        String tempStr = packageSign(params);
        String stringSignTemp = tempStr + "&key=" + partnerKey;
        return SecureUtil.md5(stringSignTemp).toUpperCase();
    }

    /**
     * 构建签名
     *
     * @param params     需要签名的参数
     * @param partnerKey 密钥
     * @return 签名后的 Map
     */
    public static Map<String, String> buildSign(Map<String, String> params, String partnerKey) throws NoSuchAlgorithmException {

        String sign = createSign(params, partnerKey);
        params.put(FIELD_SIGN, sign);
        return params;
    }

}
