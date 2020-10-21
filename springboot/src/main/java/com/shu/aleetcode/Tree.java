package com.shu.aleetcode;

import com.shu.alogrithm.test.Solution2;
import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author shuxibing
 * @Date 2020/10/15 16:45
 * @Uint d9lab_2019
 * @Description:
 */
public class Tree {

    /**
     *
     * 二叉树中，深度优先遍历其实就是前序遍历的递归版本
     *  广度优先遍历就是层次遍历的版本
     *
     *
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/20 10:16
     * @Uint d9lab
     * @Description:  时间复杂度过高
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode treeNode=null;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //二叉树层次遍历
        while (queue.size()>0){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                boolean f= hasChild(poll, p) && hasChild(poll, q);
                if (f){
                    //该节点满足要求
                    treeNode=poll;
                    //说明节点就在自己的子树下面，去除后面的遍历，剪枝操作
                    queue.clear();
                }
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
                if (f){
                    //结束本层循环
                    break;
                }
            }
        }
        return treeNode;
    }





    public boolean hasChild(TreeNode root,TreeNode child){
        if (root == null){
            return false;
        }
        if (root.equals(child)){
            return true;
        }
        return hasChild(root.left,child) ||  hasChild(root.right,child);
    }

    /**递归实现---
     *
     *
     * 根结点 ---> 左子树 ---> 右子树
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:48
     * @Uint d9lab
     * @Description:  前序遍历
     *
     */
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.val+"  ");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:49
     * @Uint d9lab
     * @Description:
     * 非递归版本，使用队列的方式实现
     *
     */
    public void preOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                //得到中间节点
                System.out.print(pNode.val+"  ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }



    /**
     * 左子树 ---> 根结点 ---> 右子树
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:52
     * @Uint d9lab
     * @Description: 中序遍历
     *
     */
    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.left);
            System.out.print(root.val+"  ");
            inOrderTraverse1(root.right);
        }
    }



    public void inOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                System.out.print(node.val+"  ");
                pNode = node.right;
            }
        }
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:56
     * @Uint d9lab
     * @Description: 后序遍历
     *
     */
    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            postOrderTraverse1(root.left);
            postOrderTraverse1(root.right);
            System.out.print(root.val+"  ");
        }
    }


    /**
     *
     *
     *
     *
     * 每次将自己的子节点都存在链表中，同时取出进入队列的数据。
     * 将二叉树变成数组类型，按顺序进入队列中
     *
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:56
     * @Uint d9lab
     * @Description:  层次遍历
     *
     */
    public void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        //链表，队列的方式
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //取出一个几点
            TreeNode node = queue.poll();
            System.out.print(node.val+"  ");
            //同时将当前节点的子节点添加到队列中
            if (node.left != null) {
                queue.push(node.left);
            }
            if (node.right != null) {
                queue.push(node.right);
            }
        }
    }

}
