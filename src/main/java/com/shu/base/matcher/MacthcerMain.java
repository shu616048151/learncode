package com.shu.base.matcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacthcerMain {
    public static void main(String[] args){
        String reg="\\d+";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher= pattern.matcher("22@asdfd333gdf3");
        String[] split = pattern.split("33&sadfsd999");
        for (String s :split){
            System.out.println(s);
        }
        //String group = matcher.group(0);
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }

    @Test
    public void groupTest(){
        String text = "abaaabccab1234ababcccab432dog";
        String regexp = "(a*b)([0-9]+)";
        Pattern pattern = Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            System.out.println("----------------");
            System.out.println(matcher.group());
            System.out.println(matcher.group(0)); //匹配到的整个字符串
            System.out.println(matcher.group(1)); //第一个括号
            System.out.println(matcher.group(2)); //第二个括号
        }
    }
    @Test
    public void mail(){
        String regexp = "[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}";
        Pattern pattern=Pattern.compile(regexp);
        Matcher matcher = pattern.matcher("shu616048151@qq.com,616048151@whut.edu.com");
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }


    @Test
    public void checkOpenId(){
        String openId="oX8hJ6KKucbmeu62Pi__nAJ4Q6P8";
        System.out.println(openId.length());
        String reg="^([0-9A-F]{28})$";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher = pattern.matcher(openId);
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
