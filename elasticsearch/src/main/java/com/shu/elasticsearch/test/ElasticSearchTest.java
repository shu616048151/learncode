package com.shu.elasticsearch.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author shuxibing
 * @date 2019/10/20 11:22
 * @uint d9lab
 * @Description:
 */
public class ElasticSearchTest {
    private static final String HOST = "192.168.25.128";
    private static final int PORT = 9300;

    private static final String INDEX = "myname";
    private static final String TYPE = "user";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private TransportClient client = null;

    @Before
    public void getClient() throws UnknownHostException {
        client = TransportClient.builder()
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
    }

    @After
    public void closeClinet() {
        if (client != null) {
            client.close();
        }
    }


    // 字段匹配查询,单个字段(会进行分词然后查询)
    @Test
    public void matchQuery() {
        SearchResponse searchResponse = this.client.prepareSearch(INDEX).setTypes(TYPE)
                .setQuery(QueryBuilders.matchQuery("title", "zhang"))
                .get();

        // 查询的总数(命中数)
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数: " + totalHits);
        // 遍历查询的结果
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            SearchHit next = iterator.next();
            // System.out.println(next.getSourceAsString());
            String id = next.getId();
            Map<String, Object> source = next.getSource();
            Integer productId = (Integer) source.get("id");
            String productTitle = (String) source.get("title");

            System.out.println("Document ID: " + id);
            System.out.println("商品的id: " + productId);
            System.out.println("商品的title: " + productTitle);
        }
    }




}
