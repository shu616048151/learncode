package com.shu.alogrithm;

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
        if (root == null){
            return 0;
        }
        if ((root.left == null) && (root.right == null)){
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left,right)+1;
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
}
