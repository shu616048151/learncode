package com.shu.alogrithm;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.hssf.record.chart.FontIndexRecord;

import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/4/4 15:48
 * @Uint d9lab_2019
 * @Description:
 */
public class Test {


    public static String countandSay(int n){
        String[] str=new String[n];
        str[0]="1";
        for (int i=1;i<n;i++){
            String s = str[i - 1];
            String newStr="";
            char[] chars = s.toCharArray();
            int countlength=1;
            for (int j=0;j<chars.length;j++){
                if (j<chars.length-1&&chars[j]==chars[j+1]){
                    countlength++;
                    continue;
                }else{
                    if (countlength==1){
                        newStr=newStr+"1"+chars[j];
                    }else {
                        newStr=newStr+countlength+chars[j];
                        countlength=1;
                    }

                }
            }
            str[i]=newStr;
        }
        return str[n-1];
    }

    @org.junit.Test
    public void test(){
        System.out.println(Integer.toBinaryString(964176192));
    }

    public static int hammingWeight(int n) {
        System.out.println(n);
        int count=0;
        String s = ""+n;
        System.out.println(s);
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar==1){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length==0||nums.length==1){
            return nums.length;
        }
        Arrays.sort(nums);
        int max=1;
        int temp=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]!=nums[i-1]){
                if (nums[i]-nums[i-1]==1){
                    temp+=1;
                }else {
                    max=Math.max(max,temp);
                    temp=1;
                }
            }

        }
        return max;

    }


    public String longestCommonPrefix(String[] strs) {
        String str1=strs[0];
        for (String str : strs) {
            if (str.length() < str1.length()){
                str1=str;
            }
        }

        while (true) {
            for (String str : strs) {
                if (!str.startsWith(str1)){
                    str1=str1.substring(0,str1.length()-1);
                    break;
                }
            }
            return str1;
        }

    }


    public int maxArea(int[] height) {
        int max=0;
        int m=0;
        int n=height.length-1;
        for (int i=0; i< height.length;i++){
            if (height[m]<height[n]){
                max=Math.max(max,(height[m])*(n-m));
                m++;
            }else {
                max=Math.max(max,(height[m])*(n-m));
                n--;
            }
        }

        return max;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> listList=new ArrayList<>();
        dfs(candidates,0,candidates.length,target,listList,new ArrayList<>());
        return listList;
    }


    public void dfs(int[] candidates,int i, int length, int target,List<List<Integer>> listList,List<Integer> list){
        if (target == 0){
            listList.add(new ArrayList<>(list));
        }

        for (int k=i;k<length;k++){
            if (candidates[k]-target<0){
                break;
            }
            list.add(candidates[k]);
            dfs(candidates,k,length,target-candidates[k],listList,list);
            list.remove(list.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> listList=new ArrayList<>();
        dfs(0,nums,listList);
        return listList;
    }


    public void dfs(int begin,int[] nums, List<List<Integer>> listList){

        if (begin==nums.length){

            List<Integer> list=new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            listList.add(list);
        }
        for (int i=begin; i<nums.length;i++){
            swap(0,i,nums);
            dfs(i+1,nums,listList);
            swap(0,i,nums);

        }

    }

    public void swap(int i,int j,int[] nums){
        int temp= nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }



    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> listList=new ArrayList<>();
        dfs(1,n,k,listList,new ArrayList<Integer>());
        return listList;
    }


    public void dfs(int begin, int n, int k,List<List<Integer>> listList, ArrayList<Integer> list){
        if (k==0){
            listList.add(new ArrayList<>(list));
            return;
        }
        for (int i=begin;i<=n;i++){
            list.add(i);
            dfs(i+1,n,k-1,listList,list);
            list.remove(list.size()-1);
        }
    }


    public int[] twoSum(int[] nums, int target) {
        int[] data=new int[2];
        for (int i=0; i< nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i]+nums[j]==target){
                    data[0]=i;
                    data[1]=j;
                }
            }
        }
        return data;
    }


    public int lengthOfLongestSubstring(String s) {
        Set<Character> occ=new HashSet<>();
        int end=-1;
        int temp=0;
        for (int start=0;start<s.length();start++){
            if (start != 0 ){
                occ.remove(s.charAt(start-1));
            }
            while (end+1 < s.length() && !occ.contains(s.charAt(end+1))){
                occ.add(s.charAt(end+1));
                end++;
            }
            temp=Math.max(temp,end+1-start);
        }

        return temp;
    }

}



