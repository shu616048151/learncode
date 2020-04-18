package com.shu.springmvc.redirect;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Created by #{QiuYW }on 2017/1/14.
 * SSLContextBuilder在4.3版本后被作者移到org.apache.http.ssl包里去了
 */
public class SSLUtils {
    private static Logger logger = Logger.getLogger(SSLUtils.class);
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            logger.error("SSLUtilsErrorKetManage");
        } catch (NoSuchAlgorithmException e) {
            logger.error("SSLUtilsErrorNOAlgorithm");
        } catch (KeyStoreException e) {
            logger.error("SSLUtilsErrorKeyStore");
        }
        return HttpClients.createDefault();
    }
}
