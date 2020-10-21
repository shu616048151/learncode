package com.shu.aleetcode;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.sql.visitor.functions.Hex;
import com.alibaba.druid.sql.visitor.functions.If;
import com.graphbuilder.math.func.AbsFunction;
import com.graphbuilder.math.func.PowFunction;
import com.ijpay.wxpay.model.TransferModel;
import com.shu.designpattern.adapter.Turkey;
import com.sun.deploy.util.VersionString;
import org.junit.Test;
import sun.plugin.javascript.navig.LinkArray;

import java.time.OffsetDateTime;
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

    public static class TreeNode {
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
     * @Date 2020/10/19 18:36
     * @Uint d9lab
     * @Description:  90. 子集 II
     *
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        List list=new ArrayList();
        Set<String> set=new HashSet<>();
        backtrace7(lists,set,list,0,nums);
        return lists;
    }

    public void backtrace7(List<List<Integer>> listList,Set<String> set,List<Integer> list,int start,int[] nums){
        //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
        String str="";
        //先进行数组排序，保证唯一性
        List<Integer> newList=new ArrayList<>(list);
        Collections.sort(newList);
        for (int i=0; i<newList.size();i++){
            str+=newList.get(i);
        }
        //进行去重剪枝
        if (!set.contains(str)){
            listList.add(newList);
            set.add(str);
        }
        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯,选择下一个元素
            backtrace7(listList,set,list,j+1,nums);
            list.remove(list.size()-1);
        }
    }


    private static void get_Subset(String str, int start, int end, boolean[] b) {
        // TODO Auto-generated method stub
        if(start==end){//当start==end时，即集合中的所有元素都已经在或者不在该子集中，输出该子集后，return跳出该层递归。
            int i = 0;
            for(;i<end;i++){
                if(b[i]){
                    System.out.print(str.charAt(i));
                }
            }
            System.out.print(" ");
            return ;
        }else{
            b[start] = false;//代表数组中索引为start的元素不在该子集中
            get_Subset(str, start+1, end, b);//而后进入递归，元素索引向后加一，同样索引为start+1的元素也有在或者不在该子集中两种可能性
            b[start] = true;
            get_Subset(str, start+1, end, b);
        }
    }


    public boolean backspaceCompare(String S, String T) {
        String NewS = check(S);
        String NewT = check(T);

        return NewS.equals(NewT);
    }

    public String check(String str){
        StringBuilder out=new StringBuilder();
        for (int i=0;i<str.length();i++){
            if (str.charAt(i) != '#'){
                out.append(str.charAt(i));
            }else {
                if (out.length() != 0){
                    out.deleteCharAt(out.length()-1);
                }
            }
        }
        return out.toString();
    }


    public int cuttingRope(int n) {
        int max=1;
        for (int i=1;i<n/2+1;i++){
            int chu = n / i;
            int yu = n % i;
            if (yu == 0){

            }
            max=Math.max(max, (int)(Math.pow(i,chu-yu)*Math.pow(i+1,yu)));
        }
        return max;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> list=new ArrayList<>();
        inOrderTraverse1111(root,list);
        return list;
    }

    public void inOrderTraverse1111(TreeNode root,List<Integer> list ) {
        if (root != null) {
            inOrderTraverse1111(root.left,list);
            list.add(root.val);
            inOrderTraverse1111(root.right,list);
        }
    }


    public List<TreeNode> generateTrees(int n) {

        return null;
    }

    public void dfs(Integer[] num,List<TreeNode> treeNode,int start){
        for (int i=start;i<num.length;i++){

        }
    }


    public int distinctSubseqII(String S) {
        char[] chars = S.toCharArray();
        List<List<Character>> lists=new ArrayList<>();
        List<Character> list=new ArrayList();
        Set<String> set=new HashSet<>();
        backtrace5(lists,set,list,0,chars);

        return lists.size();

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listList=new ArrayList<>();
        if (root == null){
            return listList;
        }
         levelOrderHelp(root,listList);
         return listList;
    }

    public void levelOrderHelp(TreeNode root,List<List<Integer>> listList){
        Queue<TreeNode> queue=new LinkedList<>();
       queue.offer(root);
        while (queue.size() !=0){
            int size = queue.size();
            List<Integer> list=new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode pop = queue.poll();
                list.add(pop.val);
                if (pop.left != null){
                    queue.offer(pop.left);
                }
                if (pop.right != null){
                    queue.offer(pop.right);
                }
            }
            listList.add(list);
        }

    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/19 20:25
     * @Uint d9lab
     * @Description:  前 K 个高频元素
     * 
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new TreeMap<Integer, Integer>();
        for (int i=0; i<nums.length; i++){
            int num=nums[i];
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }


        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;

    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:17
     * @Uint d9lab
     * @Description:  回溯算法去重即可
     *
     */
    public void backtrace5(List<List<Character>> listList,Set<String> set,List<Character> list,int start,char[] nums){
        //剪枝
        String str="";
        for (int i=0 ; i<list.size() ;i++){
            str +=list.get(i);
            if (!set.contains(str)){
                listList.add(new ArrayList<>(list));
                set.add(str);
            }
        }
        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯
            //可以重复选中参数，因为元素是可以重复选中的
            backtrace5(listList,set,list,j+1,nums);
            list.remove(list.size()-1);
        }
    }


    public int maxProfit(int[] prices, int fee) {
        int[] dp=new int[prices.length];
        for (int i=1;i<prices.length ;i++){
            for (int j=0; j<i; j++){
                if (prices[i]-prices[j]-fee>0){
                    dp[i]=Math.max(dp[i],dp[j]+prices[i]-prices[j]-fee);
                }else {
                    dp[i]=Math.max(dp[i],dp[j]);
                }

            }
        }

        return dp[dp.length-1];
    }




    /**
     *
     * @Author shuxibing
     * @Date 2020/10/20 11:20
     * @Uint d9lab
     * @Description:   最佳买卖股票时机含冷冻期
     *
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     */
    public int maxProfit111(int[] prices) {
        for (int i=0;i<prices.length;i++){

        }
        return 0;
    }

    public int maxProfit(int[] prices) {
        int max=0;
        //一次交易
        max= getmMaxProfit(prices);
        //两次交易
        for (int i=0;i<prices.length;i++){
            int[] copyOfRange1= Arrays.copyOfRange(prices, 0, i);
            int[] copyOfRange2= Arrays.copyOfRange(prices, i, prices.length);
            max=Math.max(max,getmMaxProfit(copyOfRange1)+getmMaxProfit(copyOfRange2));
        }
        return max;
    }
    public int getmMaxProfit(int[] prices) {
        int max=Integer.MIN_VALUE;
        for (int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                max=Math.max(max,prices[j]-prices[i]);
            }
        }
        return max<0 ?0:max;
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:33
     * @Uint d9lab
     * @Description:  给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     */
    public List<List<Integer>> combine(int n, int k) {
        int[] nums=new int[n];
        for (int i=0;i<n;i++){
            nums[i]=i+1;
        }
        List<List<Integer>> lists=new ArrayList<>();
        List list=new ArrayList();
        backtrace4(lists,list,0,nums,k);
        return lists;
    }


    public int[] searchRange(int[] nums, int target) {
        Integer start=null;
        Integer end=null;
        boolean f=false;
        for (int i=0;i<nums.length;i++){
            //开始统计
            if (!f &&nums[i] == target) {
                f = true;
                start = i;
            }
            //结束统计
            if (f && nums[i] != target){
                f=false;
                end=i-1;
            }

        }
        if (start == null && end ==null){
            return new int[]{-1,-1};
        }
        return new int[]{start,end};
    }

    public void solve(char[][] board) {

    }


    public int maxProduct(int[] nums) {
        int[][] dp=new int[nums.length][nums.length];
        int max=nums[0];
        for (int k=0;k<nums.length;k++){
            dp[k][k]=nums[k];
        }
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length ;j++){
                dp[i][j]=dp[i][j-1]*nums[j];
                if (dp[i][j]>max ){
                    max=dp[i][j];
                }
                if (nums[j]>max){
                    max=nums[j];
                }
            }
        }
        return max;
    }


    public int findPeakElement(int[] nums) {
        if (nums.length ==1){
            return 0;
        }
        if (nums.length>=2){
            if (nums[0]>nums[1]){
                return 0;
            }
            if (nums[nums.length-1] >nums[nums.length-2]){
                return nums.length-1;
            }
        }
        for (int i=1; i<nums.length-1;i++){
            if (nums[i]>nums[i-1] & nums[i]>nums[i+1]){
                return i;
            }
        }
        return 0;
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/19 21:07
     * @Uint d9lab
     * @Description: 134. 加油站
     *
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i=0;i<gas.length;i++){
            if (canCompleteCircuitHelper(gas,cost,i)){
                return i;
            }
        }
        return -1;
    }

    public boolean canCompleteCircuitHelper(int[] gas,int[] cost,int start){
        int k=gas.length;
        int myGas=0;
        while (k >0){
                k--;
                myGas=myGas+gas[(start)%gas.length];
            if (myGas -cost[(start)%cost.length] <0){
                return false;
            }else {
                myGas=myGas-cost[(start)%cost.length];
            }
            start++;

        }
        return true;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //用于记录前i字符串的拆分情况
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for (int i=0; i<s.length();i++){
            for (int j=i;j<s.length();j++){
                //前i字符串拆分成功，且i,j的字符串拆分成功。
                if (dp[i] && wordDict.contains(s.substring(i,j+1))){
                    dp[j+1]=true;
                }
            }
        }
        return dp[dp.length-1];
    }

    public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            List<ListNode> list = new ArrayList<ListNode>();
            ListNode node = head;
            while (node != null) {
                list.add(node);
                node = node.next;
            }
            int i = 0, j = list.size() - 1;
            while (i < j) {
                list.get(i).next = list.get(j);
                i++;
                if (i == j) {
                    break;
                }
                list.get(j).next = list.get(i);
                j--;
            }
            list.get(i).next = null;
    }



    public boolean isValidSudoku(char[][] board) {

        return true;
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:17
     * @Uint d9lab
     * @Description:  数字限定的排列组合数,使用回溯算法剪枝即可
     *
     */
    public void backtrace4(List<List<Integer>> listList ,List<Integer> list,int start,int[] nums,int target){
        //剪枝
        if (target <0){
            return;
        }
        if (target == 0){
            listList.add(new ArrayList<>(list));
            return;
        }

        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯
            //可以重复选中参数，因为元素是可以重复选中的
            backtrace4(listList,list,j+1,nums,target-1);
            list.remove(list.size()-1);
        }
    }




    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:56
     * @Uint d9lab
     * @Description:  使用最长公共前缀的理念
     *
     * 令 dp[i][j] 表示 A[i:] 和 B[j:] 的最长公共前缀
     *
     */
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }


    public int lengthOfLIS(int[] nums) {
        if (nums.length<2){
            return nums.length;
        }
        //动态规划备忘录，表示包含i的最大长度
        int[] out=new int[nums.length];
        int max=1;
        for (int i=0;i<nums.length;i++){
            out[i]=1;
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    out[i]=Math.max(out[i],out[j]+1);
                }
                max=Math.max(max,out[i]);
            }
        }
        return max;
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:15
     * @Uint d9lab
     * @Description:  组合总和 II
     *
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists=new ArrayList<>();
        List list=new ArrayList();
        Set<String> set=new HashSet<>();
        backtrace3(lists,set,list,0,candidates,target);
        return lists;
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:17
     * @Uint d9lab
     * @Description:  数字只能使用一次,具有去重功能
     *
     */
    public void backtrace3(List<List<Integer>> listList , Set<String> set,List<Integer> list,int start,int[] nums,int target){
        //剪枝
        if (target <0){
            return;
        }
        if (target == 0){
            //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
            String str="";
            for (int i=0; i<list.size() ;i++){
                str += list.get(i);
            }
            if (!set.contains(str)){
                listList.add(new ArrayList<>(list));
                set.add(str);
            }
            return;
        }

        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯
            //可以重复选中参数，因为元素是可以重复选中的
            backtrace3(listList,set,list,j+1,nums,target-nums[j]);
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


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }
        Set<String> set=new HashSet<>();
        backtrace2(lists,set,list,0,nums);
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
    public void backtrace2(List<List<Integer>> listList ,Set<String> set,List<Integer> list,int start,int[] nums){

        //剪枝,存储当前的值,必须使用new 新对象，不然会使得引用的值会变
        if (start == nums.length){
            //去重
            String str="";
            for (int i=0;i<list.size();i++){
                str+=list.get(i);
            }
            if (set.contains(str)){
                return;
            }else {
                listList.add(new ArrayList<>(list));
                set.add(str);
            }
        }
        for (int j=start;j<nums.length;j++){
            //回溯算法
            Collections.swap(list,start,j);
            //使用回溯的数据填充的方法，此时填充start+1这个数，继续向下填充数据
            backtrace2(listList,set,list,start+1,nums);
            Collections.swap(list,start,j);
        }
    }

    public void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }



    public int searchInsert(int[] nums, int target) {
        int index=nums.length;
        for (int i=0;i<nums.length;i++){
            if (nums[i] >= target){
                return i;
            }
        }
        return index;
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


    public int rob(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length ==1){
            return nums[0];
        }

        return Math.max(myrob(Arrays.copyOfRange(nums,0,nums.length-1)),myrob(Arrays.copyOfRange(nums,1,nums.length)));
    }

    public int myrob(int[] data){
        int[] dp=new int[data.length];
        if (data.length ==1){
            return data[0];
        }
        if (data.length ==2){
            return Math.max(data[0],data[1]);
        }
        dp[0]=data[0];
        dp[1]=Math.max(data[0],data[1]);
        for(int i=2 ;i< data.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+data[i]);
        }

        return dp[data.length-1];
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/16 19:22
     * @Uint d9lab
     * @Description:  组合总和 III
     * 
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists=new ArrayList<>();
        if (k>n){
            return lists;
        }
        int[] nums=new int[n-k];
        for (int i=0; i<n-k;i++){
            nums[i]=i+1;
        }

        List list=new ArrayList();
        backtrace6(lists,list,0,nums,n,k);
        return lists;
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 20:17
     * @Uint d9lab
     * @Description:  数字限定的排列组合数,使用回溯算法剪枝即可
     *
     */
    public void backtrace6(List<List<Integer>> listList ,List<Integer> list,int start,int[] nums,int target,int k){
        //剪枝
        if (k == list.size()){
            int sum=0;
            for(int i=0 ;i<list.size();i++){
                sum+=list.get(i);
            }
            if (sum == target){
                listList.add(new ArrayList<>(list));
            }
            return;
        }

        for (int j=start;j<nums.length;j++){
            //回溯算法
            list.add(nums[j]);
            //向后回溯
            //可以重复选中参数，因为元素是可以重复选中的
            backtrace6(listList,list,j+1,nums,target,k);
            list.remove(list.size()-1);
        }
    }


    public int massage(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length ==1 ){
            return nums[0];
        }
        if (nums.length ==2){
            return Math.max(nums[0],nums[1]);
        }
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);

        for(int i=2 ;i< nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[dp.length-1];
    }



    public ListNode sortList(ListNode head) {
        List<ListNode> listNodeList=new ArrayList<>();

        while (head != null){
            listNodeList.add(head);
            head=head.next;
        }

        Collections.sort(listNodeList, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode temp=new ListNode(0);
        ListNode out=temp;
        for (ListNode listNode : listNodeList) {
            temp.next=listNode;
            temp=temp.next;
        }

        return out.next;
    }


    //dp[i]表示已i结尾连续序列的最大值
    public int maxSubArray(int[] nums) {
      int[] dp=new int[nums.length];
       int max=nums[0];
      dp[0]=nums[0];
      for (int i=1;i<nums.length;i++){
          dp[i]=Math.max(dp[i-1],0)+nums[i];
          max=Math.max(dp[i],max);
      }

      return max;
    }

    public int maxValue(int[][] grid) {
        int[][] dp=new int[grid.length][grid[0].length];
        //数据填充
        dp[0][0]=grid[0][0];
       for (int i =1; i<grid.length; i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        for (int j =1; j<grid[0].length; j++){
            dp[0][j]=dp[0][j-1]+grid[0][j];
        }

        for (int i=1; i<grid.length;i++){
            for (int j=1;j<grid[0].length;j++){
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }

        return  dp[grid.length-1][grid[0].length-1];
    }

    public int[] plusOne(int[] digits) {
        int n=1;
        for (int i=digits.length-1;i>=0;i--){
            int num=digits[i]+n;
            digits[i]=num%10;
            n=num/10;
        }
        if (n != 0){
            int[] out=new int[digits.length+1];
            out[0]=n;
            for (int i=0; i<digits.length;i++){
                out[i+1]=digits[i];
            }
            return out;
        }
        return digits;
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/17 18:05
     * @Uint d9lab
     * @Description:  最长公共子序列
     *
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp=new int[text1.length()+1][text2.length()+1];

        for (int i=1; i<text1.length()+1; i++){
            for (int j=1; j<text2.length()+1;j++){
                if (text1.charAt(i-1) ==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
      return null;
    }


    public int countNumbersWithUniqueDigits(int n) {
        n=(int) Math.pow(10,n);
        int sum=0;
        for (int i=0; i< n; i++){
            boolean check = check(i);
            if (check){
                sum++;
            }
        }
        return n-sum;
    }
    public boolean check(int num){
        String strNum=String.valueOf(num);
        char[] chars = strNum.toCharArray();
        Set<Character> set=new HashSet<>();
        for (int i=0 ;i < chars.length;i++){
            if (set.contains(chars[i])){
                return true;
            }else {
                set.add(chars[i]);
            }
        }
        return false;
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
     * @Date 2020/10/20 15:10
     * @Uint d9lab
     * @Description:  删除排序数组中的重复项
     *
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int n= nums.length;
        int last=nums[0];
        int sum=1;
        for (int i=1;i<n ;i++){
            if (nums[i] == last){
                last=nums[i];
                sum=sum+1;
                if (sum>2){
                    n--;
                    //数组向前移动一位
                    moveOneStep(nums,i);
                    //还是从当前开始
                    i=i-1;
                }
            }else {
                last=nums[i];
                sum=1;
            }
        }

        return n;
    }


    public boolean isPalindrome(String s) {

        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr,0,k);
    }

    public void moveOneStep(int[] nums,int start){
        for (int i=start;i<nums.length;i++){
            if (i+1 <nums.length){
                nums[i]=nums[i+1];
            }
        }

    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/20 15:55
     * @Uint d9lab
     * @Description:  739. 每日温度
     *
     */
    public int[] dailyTemperatures(int[] T) {
        int[] out=new int[T.length];
        for (int i=0;i<T.length;i++){
            for (int j=i+1;j<T.length;j++){
                if (T[j]>T[i]){
                    //说明之前的数据已经变过了
                    if (out[i]!=0){
                        out[i]=Math.min(out[i],j-i);
                    }else {
                        out[i]=j-i;
                    }
                }
            }
        }
        return out;
    }

    /**
     *
     * @Author shuxibing
     * @Date 2020/10/19 17:42
     * @Uint d9lab
     * @Description: 88. 合并两个有序数组
     *
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


    public void moveZeroes(int[] nums) {
        LinkedList<Integer> linkedList=new LinkedList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i] != 0){
                linkedList.add(nums[i]);
            }
        }
        Arrays.fill(nums,0);
        for (int i=0;i<linkedList.size();i++){
            nums[i]=linkedList.get(i);
        }
    }

    public void reverseString(char[] s) {
        int i=0;
        int j=s.length-1;
        while (i<j){
            swap(s,i,j);
            i++;
            j--;
        }
    }


    public ListNode getKthFromEnd(ListNode head, int k) {
        int n=0;
        ListNode temp=head;
        while (head != null) {
            head=head.next;
            n++;
        }
        int data=n-k;
        while (data > 0){
               temp=temp.next;
               data--;
        }
        return temp;
    }


    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i=0; i<matrix.length;i++){
            for (int j=0; j<matrix[0].length;j++){
                if (matrix[i][j] == target){
                    return check(matrix,i,j);
                }

            }
        }
        return false;
    }

    public boolean check(int[][] matrix,int i,int j){
        for (int n=1; n<matrix[i].length;n++){
            if (matrix[i][n-1] >matrix[i][n]){
                return false;
            }
        }

        for (int m=1; m<matrix.length;m++){
            if (matrix[m-1][j] >matrix[m][j]){
                return false;
            }
        }
        return true;
    }



    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }
    public void swap(char[] s,int i,int j){
        char c=s[j];
        s[j]=s[i];
        s[i]=c;
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




    @Test
    public void test(){
        ListNode listNode=new ListNode(1);
        listNode.next=new ListNode(2);
        listNode.next.next=new ListNode(3);
        listNode.next.next.next=new ListNode(4);
//        listNode.next.next.next.next=new ListNode(5);

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    /**
     *
     * @Author shuxibing
     * @Date 2020/10/15 16:42
     * @Uint d9lab
     * @Description:  层次遍历
     *
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            int size = queue.size();
            //遍历该层
            for (int i=0; i<size ;i++){
                Node poll = queue.poll();

                //开始连接节点
                if(i< size-1){
                    //队首节点，但是不会取出
                    poll.next=queue.peek();
                }
                if (poll.left != null){
                    queue.add(poll.left);
                }

                if (poll.right != null){
                    queue.add(poll.right);
                }
            }
        }

        // 返回根节点
        return root;
     }





}
