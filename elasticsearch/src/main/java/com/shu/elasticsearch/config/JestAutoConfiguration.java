package com.shu.elasticsearch.config;//package com.shu.elasticsearch.config;
//
//import com.google.gson.Gson;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestClientFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
//import org.springframework.boot.autoconfigure.elasticsearch.jest.JestProperties;
//import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import  java.util.List;
//
///**
// * @author shuxibing
// * @date 2019/9/20 15:30
// * @uint d9lab
// * @Description:
// */
//@Configuration
//@ConditionalOnClass(JestClient.class)
//@EnableConfigurationProperties(JestProperties.class)
//@AutoConfigureAfter(GsonAutoConfiguration.class)
//public class JestAutoConfiguration {
//
//    private final JestProperties properties;
//
//    private final ObjectProvider<Gson> gsonProvider;
//
//    private final List<HttpClientConfigBuilderCustomizer> builderCustomizers;
//
//    public JestAutoConfiguration(JestProperties properties, ObjectProvider<Gson> gson,
//                                 ObjectProvider<List<HttpClientConfigBuilderCustomizer>> builderCustomizers) {
//        this.properties = properties;
//        this.gsonProvider = gson;
//        this.builderCustomizers = builderCustomizers.getIfAvailable();
//    }
//
////    @Bean(destroyMethod = "shutdownClient")
////    @ConditionalOnMissingBean
////    private JestClient jestClient() {
////        JestClientFactory factory = new JestClientFactory();
////        factory.setHttpClientConfig(createHttpClientConfig());
////        return factory.getObject();
////    }
//}