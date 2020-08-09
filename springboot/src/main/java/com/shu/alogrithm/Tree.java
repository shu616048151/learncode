package com.shu.alogrithm;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shuxibing
 * @Date 2020/4/26 19:39
 * @Uint d9lab_2019
 * @Description:
 */
public class Tree {
    public class TreeNode {
      int val;
      TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        }
        if ((p == null &&q != null)||(p!=null && q== null)){
            return false;
        }
        if (p.val != q .val){
            return false;
        }
        return isSameTree(p.right,q.right)&&isSameTree(p.left,q.left);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        sum-=root.val;
        if ((root.right == null) && (root.left == null)){
            return sum ==0;
        }
        return  hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        dfs(root,sum,new ArrayList<Integer>(),lists);
        return lists;
    }

    public void dfs(TreeNode root,int sum ,List<Integer> list,List<List<Integer>> listList){
        //回溯算法+深度搜索
        if (root == null){
            return;
        }
        list.add(root.val);
        sum-= root.val;
        //判断当前节点是不是叶子几点
        if ((root.left == null) && (root.right ==null)){
            if (sum == 0){
                listList.add(new ArrayList<>(list));
            }
        }
        dfs(root.left,sum,list,listList);
        dfs(root.right,sum,list,listList);
        list.remove(list.size()-1);
    }




    public int nthUglyNumber(int n) {
        int a=0,b=0,c=0;
        int[] num=new int[n];
        num[0]=1;
        for (int i=1;i<n;i++){
            int n2=num[a]*2;
            int n3=num[b]*3;
            int n5=num[c]*5;
            num[i]=Math.min(Math.min(n2,n3),n5);
            if (num[i] == n2){
                a++;
            }
            if (num[i] == n3){
                b++;
            }
            if (num[i] == n5){
                c++;
            }
        }
        return num[n-1];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] num=new int[n];
        int[] len=new int[primes.length];

        for (int i=0;i<n;i++){
            for(int j=0;j<primes.length;j++){
                
            }

        }

        return num[n-1];

    }

    public boolean isUgly(int num) {
        if (num == 0){
            return  false;
        }
        while (num != 1){
            if (num%2 == 0 || num%3==0 || num%5==0){
                if (num%2==0){
                    num=num/2;
                }
                if (num%3== 0){
                    num=num/3;
                }
                if (num%5==0){
                    num=num/5;
                }

            }else {
                return false;
            }
        }
        return true;
    }





}
