//package com.shu.elasticsearch.test;
//
//import com.google.gson.JsonObject;
//import com.shu.elasticsearch.ElasticSearchApp;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestResult;
//import io.searchbox.client.JestResultHandler;
//import io.searchbox.indices.ClearCache;
//import io.searchbox.indices.CreateIndex;
//import io.searchbox.indices.DeleteIndex;
//import io.searchbox.indices.Optimize;
//import io.searchbox.indices.aliases.AddAliasMapping;
//import io.searchbox.indices.aliases.GetAliases;
//import io.searchbox.indices.aliases.ModifyAliases;
//import io.searchbox.indices.mapping.GetMapping;
//import io.searchbox.indices.mapping.PutMapping;
//import io.searchbox.indices.settings.GetSettings;
//import io.searchbox.indices.settings.UpdateSettings;
//import io.searchbox.indices.template.GetTemplate;
//import io.searchbox.indices.template.PutTemplate;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author shuxibing
// * @date 2019/9/20 11:55
// * @uint d9lab
// * @Description: jest方式
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ElasticSearchApp.class)
//public class JestTest {
//    private static String indexName = "shu";
//    private static String typeName = "user";
//    private static String elasticIps="http://192.168.25.128:9300";
//
//    @Autowired
//    private JestClient jestClient;
//
//    @Test
//    public void testCreateIndex() throws IOException {
//        JestResult execute = jestClient.execute(new CreateIndex.Builder(indexName).build());
//        System.out.println(execute.isSucceeded());
//    }
//
//    @Test
//    public void deleteIndex() {
//        try {
//            JestResult jestResult = jestClient.execute(new DeleteIndex.Builder(indexName).build());
//            System.out.println("deleteIndex result:{}" + jestResult.isSucceeded());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public void createIndexMapping(String index, String type, String mappingString) {
//        //mappingString为拼接好的json格式的mapping串
//        PutMapping.Builder builder = new PutMapping.Builder(index, type, mappingString);
//        try {
//            JestResult jestResult = jestClient.execute(builder.build());
//            System.out.println("createIndexMapping result:{}" + jestResult.isSucceeded());
//            if (!jestResult.isSucceeded()) {
//                System.err.println("settingIndexMapping error:{}" + jestResult.getErrorMessage());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    //获取index的mapping
//    public String getMapping(String indexName, String typeName) {
//        GetMapping.Builder builder = new GetMapping.Builder();
//        builder.addIndex(indexName).addType(typeName);
//        try {
//            JestResult result = jestClient.execute(builder.build());
//            if (result != null && result.isSucceeded()) {
//                return result.getSourceAsObject(JsonObject.class).toString();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    //获取索引index设置setting
//    public boolean getIndexSettings(String index) {
//        try {
//            JestResult jestResult = jestClient.execute(new GetSettings.Builder().addIndex(index).build());
//            System.out.println(jestResult.getJsonString());
//            if (jestResult != null) {
//                return jestResult.isSucceeded();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //更改索引index设置setting
//    public boolean updateIndexSettings(String index) {
//        String source;
//        XContentBuilder mapBuilder = null;
//        try {
//            mapBuilder = XContentFactory.jsonBuilder();
//            mapBuilder.startObject().startObject("index").field("max_result_window", "1000000").endObject().endObject();
//            source = mapBuilder.string();
//            JestResult jestResult = jestClient.execute(new UpdateSettings.Builder(source).build());
//            System.out.println(jestResult.getJsonString());
//            if (jestResult != null) {
//                return jestResult.isSucceeded();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    //获取索引 别名
//    public boolean getIndexAliases(String index) {
//        try {
//            JestResult jestResult = jestClient.execute(new GetAliases.Builder().addIndex(index).build());
//            System.out.println(jestResult.getJsonString());
//            if (jestResult != null) {
//                return jestResult.isSucceeded();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    //添加索引别名
//    public void addAlias(List<String> index, String alias) {
//        try {
//            AddAliasMapping build = new AddAliasMapping.Builder(index, alias).build();
//            JestResult jestResult = jestClient.execute(new ModifyAliases.Builder(build).build());
//            System.out.println("result:" + jestResult.getJsonString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test(){
////        List<String> index=new ArrayList<>();
////        index.add("myname");
////        addAlias(index, "myname_test");
//        getIndexAliases("myname");
//
//    }
//    //获取索引模版
//    public void getTemplate(String template) {
//        try {
//            JestResult jestResult = jestClient.execute(new GetTemplate.Builder(template).build());
//            System.out.println("result:" + jestResult.getJsonString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    //添加索引模版
//    public void putreturnreportTemplate() {
//        String source;
//        XContentBuilder mapBuilder = null;
//        try {
//            mapBuilder = XContentFactory.jsonBuilder();
//            mapBuilder.startObject().field("template", "df_returnreport*").field("order", 1)//
//                    .startObject("settings").field("number_of_shards", 5)//五个分片
//                    .startObject("index").field("max_result_window", "1000000")//一次查询最大一百万
//                    .endObject()//
//                    .endObject()//
//                    .startObject("mappings")//
//
//                    .startObject("df_returnreport")//type名
//                    .startObject("properties")//
//                    .startObject("id").field("type", "long").endObject()//
//                    .startObject("username").field("type", "keyword").endObject()//
//                    .startObject("content").field("type", "text").field("analyzer", "ik_max_word").endObject()//
//                    .startObject("returntime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()//
//                    .startObject("gateway").field("type", "integer").endObject()//
//                    .endObject()//
//                    .endObject()//
//
//                    .endObject()//
//                    .startObject("aliases").startObject("df_returnreport").endObject().endObject()//别名
//                    .endObject();//
//            source = mapBuilder.string();
//            JestResult jestResult = jestClient.execute(new PutTemplate.Builder("my_returnreport", source).build());
//            System.out.println("result:" + jestResult.getJsonString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    //索引优化
//    public void optimizeIndex() {
//        Optimize optimize = new Optimize.Builder().build();
//        jestClient.executeAsync(optimize, new JestResultHandler<JestResult>() {
//            @Override
//            public void completed(JestResult jestResult) {
//                System.out.println("optimizeIndex result:{}" + jestResult.isSucceeded());
//            }
//            @Override
//            public void failed(Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    //清理缓存
//    @Test
//    public void clearCache() {
//        try {
//            ClearCache clearCache = new ClearCache.Builder().build();
//            jestClient.execute(clearCache);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
