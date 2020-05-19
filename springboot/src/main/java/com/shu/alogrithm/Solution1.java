package com.shu.alogrithm;

import org.junit.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;
import sun.net.NetHooks;

import java.awt.image.Kernel;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shuxibing
 * @date 2019/9/10 11:12
 * @uint d9lab
 * @Description: 牛客网的LeetCode的算法知识
 */
public class Solution1 {


    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void reOrderArray(int [] array) {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        for (int i=0;i<array.length;i++){
            int num=array[i];
            if(num%2==0){
                //偶数
                stack1.push(num);
            }else {
                //基数
                stack2.push(num);
            }
        }
        for (int i=array.length;i>=0;i--){
            if (!stack1.empty()){
                Integer pop = stack1.pop();
                array[i]=pop;
            }else {
                Integer pop = stack2.pop();
                array[i]=pop;
            }
        }

    }


    public static char FirstAppearingOnce()
    {
        String s1="google";
        Map<Character,Integer> map=new LinkedHashMap<>();
        for (int i=0;i<s1.length();i++){
            char c = s1.charAt(i);
            if (!map.containsKey(c)){
                map.put(c,1);
            }else {
                Integer integer = map.get(c);
                map.put(c,integer+1);
            }
        }

        for (Character key : map.keySet()) {
            Integer integer = map.get(key);
            if (integer==1){
                return key;
            }
        }
        return '#';
    }


    public int[] multiply(int[] A) {
       int[] C=new int[A.length];
       int[] B=new int[A.length];
       C[0]=A[0];
        for (int i=1;i<A.length;i++){
            C[i]=C[i-1]*A[i];
        }

        int j=0;
        int k=A.length-1;
        boolean flag=false;
        for (int i=A.length-1;i>=0;i--){
            if (flag){
                B[k]=C[i];
                k--;
                flag=false;
            }else {
                B[j]=C[i];
                j++;
                flag=true;
            }
        }
        return B;
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
        Map<String,String> map=new ConcurrentHashMap<>();
    }


    @Test
    public void test1(){
        int[][] array={{0,1,1},{0,0,1},{0,0,1}};
        int max=0;
        int red=0;
        int black=0;
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                int temp=array[j][i];
                if (temp==0){
                    red++;
                }
                if (temp==1){
                    black++;
                }
            }
            int tempMax = Math.max(red, black);
            red=0;
            black=0;
            if (tempMax>max){
                max=tempMax;
            }
        }
        System.out.println(max);
    }

    @Test
    public void test3(){
        int[] array={-2,4,-3,4,-6,-5};
        int max=array[0];
        int temp=0;
        for (int i=0;i<array.length;i++){
            temp+=array[i];
            if (temp>max){
                max=temp;
            }
            if (temp<0){
                temp=0;
            }
        }
        System.out.println(max);
    }

    @Test
    public void test4() throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("1");
            }
        });
        thread.start();
        thread.join();
        System.out.print("2");
    }

    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        String str=String.valueOf(x);
        int i=0;
        int j=str.length()-1;
        while (i<j){
            if (str.charAt(i) == str.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode temp=head;

        return null;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        int count=1;
        int length1 = nums1.length;
        int length2 = nums2.length;
        while (i<length1 && j<length2){
            if ((length1+length2)%2 == 0){
                //为偶数
                if (count == (length1+length2)/2){
                    return ((double) nums1[i]+(double)nums2[j])/2;
                }
            }else {
                //为奇数
                if (count == (length1+length2+1)/2){
                    return Math.max(nums1[i],nums2[j]);
                }
            }

            if (nums1[i]>nums2[j]){
                if (j == length2-1){
                    i++;
                }else {
                    j++;
                }
            }else {
                if (i == length1-1){
                    j++;
                }else {
                    i++;
                }
            }

            count++;

        }
        return 0;
    }


    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;

    }

    public static void main(String[] args){
        String str1=String.valueOf(Integer.MAX_VALUE);
        System.out.println(str1);
        System.out.println(str1.compareTo("646324351"));
//        System.out.println(Integer.valueOf("9646324351").intValue());
    }



}