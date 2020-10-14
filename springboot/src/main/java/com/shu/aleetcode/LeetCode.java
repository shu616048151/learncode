package com.shu.aleetcode;

import com.alibaba.druid.sql.visitor.functions.Hex;
import com.alibaba.druid.sql.visitor.functions.If;
import com.graphbuilder.math.func.AbsFunction;

import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/10/10 16:59
 * @Uint d9lab_2019
 * @Description:
 */
public class LeetCode {


    public static  class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0){
            return "";
        }
        String common=strs[0];
        for (int i=1;i<strs.length;i++){
            common = getCommonStr(common, strs[i]);
        }
        return common;
    }

    public  String getCommonStr(String str1,String str2){
        String common="";
        int i=0;
        while (i<str1.length() && i<str2.length()){
            if (str1.charAt(i) == str2.charAt(i)){
                common+=str1.charAt(i);
                i++;
            }else {
                break;
            }
        }
        return common;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp2=new ListNode(0);
        temp2.next=head;
        int sum=0;
        ListNode temp=head;
        while (temp !=null){
            temp=temp.next;
            sum++;
        }

        sum-=n;
        ListNode first = temp2;
        while (sum > 0) {
            sum--;
            first = first.next;
        }
        first.next = first.next.next;

        return temp2.next;
    }


    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos!=null){
            if (visited.contains(pos)){
                return pos;
            }else {
                visited.add(pos);
            }
            pos=pos.next;

        }
        return null;
    }


    public List<String> generateParenthesis(int n) {
        List<String> list=new ArrayList<>();
        backtrace(list,"",0,0,n);
        return list;
    }
    public void backtrace(List<String> list,String str,int open,int close,int n){
        if (str.length() == n*2){
            list.add(str);
            return;
        }
        if (open<n){
            str=str+"(";
            backtrace(list,str,open+1,close,n);
            //回溯去除
            str=str.substring(0,str.length()-1);
        }
        if (open>close){
            str=str+")";
            backtrace(list,str,open,close+1,n);
            str=str.substring(0,str.length()-1);
        }
    }


    public int minPathSum(int[][] grid) {
        if (grid== null){
            return 0;
        }
        int[][] record=new int[grid.length][grid[0].length];
        record[0][0]=grid[0][0];
        for (int i=1;i<grid.length;i++){
            record[i][0]=record[i-1][0]+grid[i][0];
        }

        for (int j=1;j<grid[0].length;j++){
            record[0][j]=record[0][j-1]+grid[0][j];
        }


        for (int i=1;i<grid.length;i++){
            for (int j=1;j<grid[0].length;j++){
                record[i][j]=Math.min(record[i-1][j],record[i][j-1])+grid[i][j];
            }
        }

        return record[grid.length-1][grid[0].length-1];
    }


    public int uniquePaths(int m, int n) {
        int[][] record=new int[m][n];
        for (int i=0;i<m;i++){
            record[i][0]=1;
        }

        for (int j=0;j<n;j++){
            record[0][j]=1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                record[i][j]=record[i-1][j]+record[i][j-1];
            }
        }


        return record[m-1][n-1];
    }





    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 ){
            return null;
        }
        ListNode head=lists[0];
        for (int i=1;i<lists.length;i++){
            head=mergeTwoLists(head,lists[i]);
        }

        return head;
    }
    public static ListNode mergeTwoLists(ListNode listNode1,ListNode listNode2){
        ListNode listNode=new ListNode(0);
        ListNode head=listNode;
        while (listNode1 != null || listNode2 != null){
            if (listNode1 != null && listNode2 !=null){
                if (listNode1.val <listNode2.val){
                    listNode.next=listNode1;
                    listNode=listNode.next;
                    listNode1=listNode1.next;
                }else {
                    listNode.next=listNode2;
                    listNode=listNode.next;
                    listNode2=listNode2.next;
                }
            }
            if (listNode1 == null && listNode2 !=null){
                listNode.next=listNode2;
                break;
            }
            if (listNode2 == null && listNode1 !=null){
                listNode.next=listNode1;
                break;
            }
        }
        return head.next;
    }

    public static void main(String[] args){
        ListNode listNode1=new ListNode(1);
        listNode1.next=new ListNode(2);

        ListNode listNode2=new ListNode(1);
        listNode2.next=new ListNode(2);
        ListNode listNode = mergeTwoLists(listNode1, listNode2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }


    public void nextPermutation(int[] nums) {

    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/12 11:28
     * @Uint d9lab
     * @Description:  二分查找,每次丢弃一个数据
     *
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int climbStairs(int n) {
        if (n==1){
            return 1;
        }
        if (n==2){
         return 2;
        }
        int[] data=new int[n+1];
        data[1]=1;
        data[2]=2;
        for(int i=3;i<n+1;i++){
            data[i]=data[i-1]+data[i-2];
        }
        return data[n];
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/12 12:29
     * @Uint d9lab
     * @Description:  回溯算法+搜索树实现
     *
     */
    public boolean exist(char[][] board, String word) {

        for (int i=0; i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                int[][] use=new int[board.length][board[0].length];
                if (dfs(board,i,j,use,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board,int i,int j,int[][] use,String s,int start){

        //首先判断出界情况
        if (i<0 || i>=board.length || j<0 || j>=board[0].length || use[i][j]== 1|| board[i][j] != s.charAt(start)){
            return false;
        }
        //符合条件退出情况
        if (start == s.length()-1){
            return true;
        }

        //回溯算法，递归实现
        use[i][j]=1;
        boolean b = dfs(board, i + 1, j, use, s, start + 1) ||dfs(board, i - 1, j, use, s, start + 1) ||dfs(board, i, j + 1, use, s, start + 1) ||dfs(board, i, j - 1, use, s, start + 1);
        use[i][j]=0;
        return b;
    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> lastList=null;
        for (int i=0; i< numRows;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<=i;j++){
                if (j==0|| j==i){
                    list.add(1);
                }else {
                    list.add(lastList.get(j)+lastList.get(j-1));
                }
            }
            lists.add(list);
            lastList=list;
        }
        return lists;
    }

    public class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }


    //左右互为镜像
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }

        return check(root.left,root.right);
    }

    public boolean check(TreeNode left,TreeNode right){
        if (left == null && right ==null){
            return true;
        }
        if ((left == null && right != null) || (left != null && right ==null)){
            return false;
        }
        return left.val == right.val && check(left.left,right.right)&&check(left.right,right.left);
    }


    public boolean hasCycle(ListNode head) {
        Set<ListNode> set=new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return true;
            }else {
               set.add(head);
               head=head.next;
            }
        }

        return false;
    }



    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> last=new ArrayList<>();
        last.add(triangle.get(0).get(0));
        for (int i=1; i<triangle.size();i++){
            List<Integer> tempList = triangle.get(i);
            List<Integer> list=new ArrayList<>();
            for (int j=0; j<tempList.size();j++){
                if (j==0){
                    int num=tempList.get(j)+last.get(j);
                    list.add(num);
                    continue;
                }

                if (j == tempList.size()-1){
                    int num=tempList.get(j)+last.get(j-1);
                    list.add(num);
                    continue;
                }
               if (j>0 && j < tempList.size()-1){
                   //上一层的最短路径
                   int num=tempList.get(j)+Math.min(last.get(j),last.get(j-1));
                   list.add(num);
                   continue;
               }

            }
            last=list;

        }
        int min=Integer.MAX_VALUE;
        for (Integer integer : last) {
            if (integer<min){
                min=integer;
            }
        }

        return min;
    }



    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        List list=new ArrayList();
        backtrace(lists,list,0,nums);
        return lists;
    }

    public void backtrace(List<List<Integer>> listList ,List<Integer> list,int start,int[] nums){
        //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
        listList.add(new ArrayList<>(list));
        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯,选择下一个元素
            backtrace(listList,list,j+1,nums);
            list.remove(list.size()-1);
        }
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/14 10:08
     * @Uint d9lab
     * @Description:  回溯算法加上剪枝
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists=new ArrayList<>();
        List list=new ArrayList();
        backtrace(lists,list,0,candidates,target);
        return lists;
    }


    public void backtrace(List<List<Integer>> listList ,List<Integer> list,int start,int[] nums,int target){
        if (target <0){
            return;
        }
        if (start == nums.length){
            return;
        }
        if (target == 0){
        //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
            listList.add(new ArrayList<>(list));
            return;
        }

        for (int j=start;j<nums.length;j++){
                //回溯算法
            list.add(nums[j]);
            //向后回溯
            //可以重复选中参数，因为元素是可以重复选中的
            backtrace(listList,list,j,nums,target-nums[j]);
            list.remove(list.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }

        backtrace1(lists,list,0,nums);
        return lists;
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/14 10:27
     * @Uint d9lab
     * @Description:  全排列
     *
     */
    public void backtrace1(List<List<Integer>> listList ,List<Integer> list,int start,int[] nums){

        //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
        if (start == nums.length){
            listList.add(new ArrayList<>(list));
        }
        for (int j=start;j<nums.length;j++){
            //回溯算法
           Collections.swap(list,start,j);
            //使用回溯的数据填充的方法，此时填充start+1这个数，继续向下填充数据
            backtrace1(listList,list,start+1,nums);
            Collections.swap(list,start,j);
        }
    }

    public void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


    public void sortColors(int[] nums) {
        for (int i=0;i<nums.length;i++){
            for (int j=i;j<nums.length;j++){
                if (nums[j]<nums[i]){
                    int temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
        }
    }


    public boolean isValidBST(TreeNode root) {

        return check(root,null,null);
    }

    public boolean check(TreeNode root,Integer low,Integer upper){
        if (root ==null ){
            return true;
        }
        int val=root.val;
        if (low != null && val<=low){
            return false;
        }
        if (upper != null && val >= upper){
            return false;
        }
        if (!check(root.right,val,upper)){
            return false;
        }

        if (!check(root.left,low,val)){
            return false;
        }

       return true;
    }


    public void flatten(TreeNode root) {
        ListNode listNode=new ListNode(0);
        ListNode head=listNode;
        helper(root,listNode);
        TreeNode treeNode=new TreeNode(0);
        //去除第一节点
        head=head.next;
        while (head!= null){
        }
    }

    public void helper(TreeNode root,ListNode listNode){
        if (root == null){
            return;
        }

        listNode.next=new ListNode(root.val);
        listNode=listNode.next;
        helper(root.left,listNode);
        helper(root.right,listNode);
    }


    public int singleNumber(int[] nums) {
        for (int i=0;i<nums.length;i++){
            boolean f=false;
            for (int j=0;j<nums.length;j++){
                if (i!=j && nums[i] == nums[j]){
                    f=true;
                    break;
                }
            }
            if (!f){
                return nums[i];
            }
        }
        return 0;
    }


    public int maxProfit(int[] prices) {
        int max=0;
        for (int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                if (prices[j]-prices[i] >max){
                    max=prices[j]-prices[i];
                }
            }
        }
        return max <0 ? 0:max;
    }


    public boolean wordBreak(String s, List<String> wordDict) {


        return true;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0){
            return new ArrayList();
        }
        Map<String ,List<String>> map=new HashMap<>();
        for (String str : strs) {
            //所有字符串进行重新生成，排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String strNew=String.valueOf(chars);
            if (map.containsKey(strNew)){
                List<String> list = map.get(strNew);
                list.add(str);
            }else {
                List<String> list=new ArrayList<>();
                list.add(str);
                map.put(strNew,list);
            }

        }


        return new ArrayList<>(map.values());

    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        Set<String> set=new HashSet<>();
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                for (int k=j+1;k<nums.length;k++){{
                    if(nums[i]+nums[j]+nums[k] == 0){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);


                        Collections.sort(list);
                        if(!checkRE(lists,list)){
                            lists.add(list);
                        }
                    }
                }
                }
            }
        }
        return lists;
    }

    private boolean checkRE(List<List<Integer>> lists, List<Integer> list) {
        for (List<Integer> integers : lists) {
            if (list.size() == integers.size()){
                boolean f=true;
                //默认相同的
                for (int i=0;i<list.size();i++){
                    if (!list.get(i).equals(integers.get(i))){
                        f=false;
                    }
                }
                if (f){
                    return  f;
                }
            }
        }
        return false;
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/14 14:55
     * @Uint d9lab
     * @Description: 先排序后合并
     *
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        List<int[]> list=new ArrayList<>();
        for (int i=0;i<intervals.length;i++){
            int L=intervals[i][0];
            int R=intervals[i][1];
            if (list.size() == 0 || list.get(list.size()-1)[1]<L){
                list.add(new int[]{L,R});
            }else {
                //修改区间的最大部分
                list.get(list.size()-1)[1]=Math.max(list.get(list.size()-1)[1],R);
            }
        }

        return list.toArray(new int[list.size()][]);
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
                max=Math.max(max,(height[n])*(n-m));
                n--;
            }
        }

        return max;
    }


}
