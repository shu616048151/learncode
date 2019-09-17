package com.shu.alogrithm;

import com.shu.domain.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * @author shuxibing
 * @date 2019/8/9 21:15
 * @uint d9lab
 * @Description:
 */
public class Solution {
    public static void main(String[] args){
        for (int i=0;i<1000;i++) {
            int max = 5, min = 1;
            int ran = (int) (Math.random() * (max - min) + min);
            System.out.println(ran);
        }
    }
    @Test
    public void replaceSpace(StringBuffer str) {

    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode list=new ListNode(0);
        while(true){
            if (list1==null&&list2==null){
                break;
            }

            if (list1.val>=list2.val||(list1==null&&list2!=null)){
                list.next=new ListNode(list2.val);
                list2=list2.next;
            }
            if (list1.val<list2.val||(list1!=null&&list2==null)){
                list.next=new ListNode(list1.val);
                list1=list1.next;
            }
        }

        return list.next;

    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<numbers.length;i++){
            if (!set.contains(numbers[i])){
                set.add(numbers[i]);
            }else{
                duplication[0]=numbers[i];
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list=new ArrayList<>();
        int i=0;
        int j=array.length-1;
        while (i<j){
            if (array[i]+array[j]==sum){
               list.add(array[i]);
               list.add(array[j]);
               return list;
            }
            if (array[i]+array[j]>sum){
                j--;
            }if (array[i]+array[j]<sum){
                i++;
            }
        }

        return list;
    }

    public String LeftRotateString(String str,int n) {
        if (str==null||"".equals(str)){
            return str;
        }
        //取余数真正循环移位的数字
         n=n%str.length();
         String str1=str.substring(0,n-1);
         String str2=str.substring(n-1);
        return str2+str1;
    }
    public String ReverseSentence(String str) {
        if (str==null||"".equals(str)){
            return str;
        }
        int index=str.indexOf(".");
        String str2=str.substring(index);
        String str1="";
        for (int i=str2.length()-1;i>0;i--){
            str1+=str2.charAt(i);
        }
        return str1+str.substring(0,index);
    }

    @Test
    public void listTest(){

    }

}
