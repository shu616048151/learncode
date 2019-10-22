package com.shu.elasticsearch.controller;

import com.shu.elasticsearch.dao.UserDao;
import com.shu.elasticsearch.model.User;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shuxibing
 * @date 2019/9/19 10:11
 * @uint d9lab
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/add")
    public String add( User user){
        userDao.save(user);
        return "新增数据成功";
    }
    @RequestMapping("/find")
    public User find(String id){
       return userDao.findOne(id);
    }

    @RequestMapping("/search")
    public List<User> search(String keywords){
        QueryStringQueryBuilder queryStringQueryBuilder=new QueryStringQueryBuilder(keywords);
        System.out.println("查询关键字："+keywords);
        Iterable<User> search = userDao.search(queryStringQueryBuilder);
        Iterator<User> iterator = search.iterator();
        List<User> userList=new ArrayList<>(0);
        while (iterator.hasNext()){
            userList.add(iterator.next());
        }
        return userList;
    }
    @RequestMapping("/searchByWeight")
    public List<User> searchUserByWeight(@RequestParam(value = "keywords") String searchContent) {
        // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(10))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
        Iterator<User> iterator = searchResult.iterator();
        List<User> list=new ArrayList<User>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @RequestMapping("/fuzzySearch")
    public List<User> fuzzySearch(String keywords){
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", keywords);
        Iterable<User> search = userDao.search(fuzzyQueryBuilder);
        Iterator<User> iterator = search.iterator();
        List<User> userList=new ArrayList<>(0);
        while (iterator.hasNext()){
            userList.add(iterator.next());
        }
        return userList;
    }
    @RequestMapping("/match")
    public List<User> match(String keywords){
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "name", "description");
        Iterable<User> search = userDao.search(multiMatchQueryBuilder);
        Iterator<User> iterator = search.iterator();
        List<User> userList=new ArrayList<>(0);
        while (iterator.hasNext()){
            userList.add(iterator.next());
        }
        return userList;
    }
    @RequestMapping("/matchall")
    public List<User> matchall(String keywords){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        Iterable<User> search = userDao.search(matchAllQueryBuilder);
        Iterator<User> iterator = search.iterator();
        List<User> userList=new ArrayList<>(0);
        while (iterator.hasNext()){
            userList.add(iterator.next());
        }
        return userList;
    }
    @RequestMapping("/term")
    public List<User> term(String keywords){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", keywords);
        Iterable<User> search = userDao.search(termQueryBuilder);
        Iterator<User> iterator = search.iterator();
        List<User> userList=new ArrayList<>(0);
        while (iterator.hasNext()){
            userList.add(iterator.next());
        }
        return userList;
    }



}
