package com.shu.elasticsearch.test;

import com.shu.elasticsearch.ElasticSearchApp;
import com.shu.elasticsearch.dao.ItemDao;
import com.shu.elasticsearch.dao.UserDao;
import com.shu.elasticsearch.model.Item;
import com.shu.elasticsearch.model.User;
import org.apache.commons.lang.math.RandomUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author shuxibing
 * @date 2019/9/20 10:47
 * @uint d9lab
 * @Description:
 */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = ElasticSearchApp.class)
public class SpringBootTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Item.class);
    }


    @Test
    public void testDeleteIndex(){
        elasticsearchTemplate.deleteIndex(Item.class);
        System.out.println("删除索引成功");
    }



    @Test
    public void testInsert(){
        List<User> userList=new ArrayList<>();
        for (int i=10;i<20;i++){
            User user=new User(""+i,"lisi"+i, RandomUtils.nextInt(20),RandomUtils.nextInt(2), UUID.randomUUID().toString(),new Date(),new Date());
            userList.add(user);
        }
        userDao.save(userList);
    }
    @Test
    public void testInsertItem(){
        List<Item> itemList=new ArrayList<>();
        for (int i=10;i<20;i++){
            Item item=new Item();
            item.setId(i);
            item.setName(UUID.randomUUID().toString());
            itemList.add(item);
        }
        itemDao.save(itemList);
    }

    @Test
    public void search(){
        Iterable<User> all = userDao.findAll();
        Iterator<User> iterator = all.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    /**
     * term是精准查询
     * match是分词查询
     * fuzzy是模糊查询
     */
    @Test
    public void matchQuery(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 在queryBuilder对象中自定义查询
        //matchQuery:底层就是使用的termQuery
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("name","zhangs"));
        //查询，search 默认就是分页查找
        Page<User> page = this.userDao.search(queryBuilder.build());
        //获取数据
        long totalElements = page.getTotalElements();
        System.out.println("获取的总条数:"+totalElements);

        for(User user:page){
            System.out.println(user);
        }

    }

    @Test
    public void testAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("names").field("name"));
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<User> aggPage = (AggregatedPage<User>) this.userDao.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("names");
        // 3.2、获取桶
        List<Terms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (Terms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }

    }


    @Test
    public void testSubAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("sexs").field("sex")
                        .subAggregation(AggregationBuilders.avg("ages").field("age")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<User> aggPage = (AggregatedPage<User>) this.userDao.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        LongTerms agg = (LongTerms) aggPage.getAggregation("sexs");
        // 3.2、获取桶
        List<Terms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (Terms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
            System.out.println("性别："+bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "个");

            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("ages");
            System.out.println("平均年龄：" + avg.getValue());
        }
    }





}
