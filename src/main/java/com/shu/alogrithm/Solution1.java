package com.shu.alogrithm;

import org.junit.Test;
import sun.net.NetHooks;

import java.awt.image.Kernel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shuxibing
 * @date 2019/9/10 11:12
 * @uint d9lab
 * @Description: 牛客网的LeetCode的算法知识
 */
public class Solution1 {
    public static void main(String[] args) {
        String str="NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp";
        System.out.println(str.length());
        int index= FirstNotRepeatingChar("NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp");
        System.out.println(index);
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseListNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newhead = null;
        ListNode temp = null;
        while (head != null) {
            temp = head.next;
            head.next = newhead;
            newhead = head;
            head = temp;
        }
        return newhead;
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode listNode1 = reverseListNode(listNode);
        while (listNode1 != null) {
            list.add(listNode1.val);
            listNode1 = listNode1.next;
        }
        return list;
    }

    @Test
    public void test2() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        listNode1.next = listNode2;
        ArrayList<Integer> integers = printListFromTailToHead(listNode1);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void MoreThanHalfNum_Solution() {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int k = array[i];
            if (map.containsKey(k)) {
                int num = map.get(k) + 1;
                if (num > (array.length / 2)) {
                    System.out.println(k);
                }
                map.put(k, num);
            } else {
                map.put(k, 1);
            }
        }
        System.out.println(0);
    }

    @Test
    public int NumberOf1Between1AndN_Solution(int n) {
        int num = 0;
        for (int i = 0; i <= n; i++) {
            String str = "" + i;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    num++;
                }
            }
        }
        return num;
    }

    @Test
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
        return "";
        }
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }

    @Test
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<array.length;i++){
            int k=array[i];
            if (set.contains(k)){
                set.remove(k);
            }else {
                set.add(k);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        num1[0]=iterator.next();
        num1[1]=iterator.next();
    }

    public boolean isNumeric(char[] str) {
        String s = str.toString();
        return s.matches("[\\-\\+][0-9]*(\\.[0-9]*)?([eE][\\+\\-]?\\d+)?");
    }



    public int GetNumberOfK(int [] array , int k) {
        int num=0;
        for (int datum : array) {
            if (datum==k){
                num++;
            }
        }

        return num;
    }

    public  static int FirstNotRepeatingChar(String str) {
        Map<Character,Integer> map=new LinkedHashMap<>(0);
        Map<Character,Integer> locationMap=new LinkedHashMap<>(0);
        for (int i=0;i<str.length();i++){
            Character c=str.charAt(i);
            if (map.containsKey(c)){
                int count=map.get(c)+1;
                map.put(c,count);
            }else {
                map.put(c,1);
                locationMap.put(c,i);
            }
        }
        for (Character key:map.keySet()){
            if (map.get(key)==1){
                return locationMap.get(key);
            }
        }
        return -1;
    }



    public ArrayList<String> Permutation(String str) {
        //todo
        ArrayList<String> list=new ArrayList<>();
        int k=0;
        for (int i=0;i<str.length();i++){

        }
        return null;
    }

    public int InversePairs(int [] array) {
        int num=0;
        for (int i=array.length-1;i>=0;i--){
            for (int j=i-1;j>=0;j--){
                if (array[j]>array[i]){
                    num++;
                }
            }
        }
        return num%1000000007;
    }


    @Test
    public void currentHashMap(){
        Map<String,String> map=new ConcurrentHashMap<>(0);
    }



}