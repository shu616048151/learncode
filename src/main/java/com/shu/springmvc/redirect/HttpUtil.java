package com.shu.springmvc.redirect;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * com.whut.athena.util
 * Created by YTY on 2016/4/5.
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    public static void httpRequest(String hostPrefix, String url, HttpServletRequest request, HttpServletResponse response,
                                   List<NameValuePair> additionalMap) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        HttpPost httpPost = new HttpPost(RedirectUtil.getHost(hostPrefix, url));
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.startsWith("accept") || headerName.startsWith("cookie")) {
                httpPost.addHeader(headerName, request.getHeader(headerName));
            }
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value!=null&&!value.equals("null")) params.add(new BasicNameValuePair(parameterName, value));
                //logger.info(parameterName + ":" + value);
            }
        }
        params.addAll(additionalMap);
        for (NameValuePair map : additionalMap) {
            //logger.info(map.getName() + ":" + map.getValue());
        }
        HttpResponse httpResponse = null;
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, RedirectUtil.SERVER_CHARSET);
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    //logger.info(responseEntity.toString());
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());//或许可以解决重定向乱码(好像没影响)
                }
            }
            response.setHeader("Server", "nginx");
        }
    }

    public static String httpRequest(String hostPrefix, String url, List<NameValuePair> params) {
        CloseableHttpClient httpClient = SSLUtils.createSSLClientDefault();
        logger.info(url);
        HttpPost httpPost = new HttpPost(RedirectUtil.getHost(hostPrefix, url));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, RedirectUtil.SERVER_CHARSET);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity, RedirectUtil.SERVER_CHARSET);
                    logger.info(result);
                    return result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
