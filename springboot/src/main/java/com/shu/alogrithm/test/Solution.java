package com.shu.alogrithm.test;

import com.shu.aleetcode.LeetCode;
import org.junit.Test;

import java.util.*;

/**
 * @author shuxibing
 * @date 2019/8/9 21:15
 * @uint d9lab
 * @Description:
 */
public class Solution {


    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int[][]  arr=new int[matrix.length][matrix.length];
        ArrayList<Integer> list=new ArrayList<>();
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix.length;j++){

            }
        }

        return list;
    }


    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> arrayList=new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        ArrayList<ArrayList<Integer>> dfs = dfs(root, arrayList, list, target);
        return dfs;
    }

    public static  ArrayList<ArrayList<Integer>> dfs(TreeNode root, ArrayList<ArrayList<Integer>> arrayList,ArrayList<Integer> list,int target){
        if (root==null){
            return arrayList;
        }
        System.out.println(root.val);
        target=target- root.val;
        list.add(root.val);
        //边界条件
        if (root.left==null&&root.right==null&&target==0){
                //新建一个表，防止他们的引用地址是一样的
                arrayList.add(new ArrayList<>(list));
        }
        if (root.left!=null){
            dfs(root.left,arrayList,list,target);
        }
        if (root.right!=null){
            dfs(root.right,arrayList,list,target);
        }
        list.remove(list.size()-1);
        return arrayList;

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

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
     * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>() ;
        if(str==null || str.length()==0) { return result ; }

        char[] chars = str.toCharArray() ;
        TreeSet<String> temp = new TreeSet<>() ;
        Permutation(chars, 0, temp);
        result.addAll(temp) ;
        return result ;
    }

    public void Permutation(char[] chars, int begin, TreeSet<String> result) {
        if(chars==null || chars.length==0 || begin<0 || begin>chars.length-1) { return ; }

        if(begin == chars.length-1) {
            //自动去重而且排序
            result.add(String.valueOf(chars)) ;
        }else {
            for(int i=begin ; i<=chars.length-1 ; i++) {
                swap(chars, begin, i) ;

                Permutation(chars, begin+1, result);
                //复原数组
                swap(chars, begin, i) ;
            }
        }
    }

    public void swap(char[] x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，
     * 因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * 递增查找数字
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {

        if(index<=0){
            return 0;
        }
        int[] result = new int[index];
        int count = 0;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        result[0] = 1;
        int tmp = 0;
        while (count < index-1) {
            tmp = min(result[i2] * 2, min(result[i3] * 3, result[i5] * 5));
            if(tmp==result[i2] * 2){
                i2++;//三条if防止值是一样的，不要改成else的
            }
            if(tmp==result[i3] * 3){
                i3++;
            }
            if(tmp==result[i5]*5){
                i5++;
            }
            result[++count]=tmp;
        }
        return result[index - 1];
    }


    public int InversePairs(int [] array) {
        int count=0;
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i]>array[j]){
                    count++;
                }
            }
        }
        return count%1000000007;
    }

    private int min(int a, int b) {
        return (a > b) ? b : a;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        while (pHead1.val!=pHead2.val){
            pHead1=(pHead1==null? null:pHead1.next);
            pHead2=(pHead2==null? null:pHead2.next);
        }
        return pHead1;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return super.toString();
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

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;

    }




    public int lengthOfLastWord(String s) {
        s=s.trim();
        String[] s1 = s.split(" ");
        return s1[s1.length-1].length();
    }

    public void rotate(int[] nums, int k) {
        for (int i=0;i<k;i++){
            int last=nums[nums.length-1];
            for (int j=nums.length-1;j>0;j--){
                nums[j]=nums[j-1];
            }
            nums[0]=last;
        }
    }

    /**
     * dfs
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root==null){
            return 0;
        }
        ArrayList<String> leaf = isLeaf(new ArrayList<>(), root, "");
        Integer sum=0;
        for (String s : leaf) {
            Integer s1 = Integer.valueOf(s);
            sum+=s1;
        }
        return sum;
    }




    public ArrayList<String> isLeaf(ArrayList<String> list,TreeNode treeNode,String s){
        s+=treeNode.val;
        if (treeNode.left==null&&treeNode.right==null){
            list.add(new String(s));
        }
        if (treeNode.left!=null){
            isLeaf(list,treeNode.left,s);
        }

        if (treeNode.right!=null){
            isLeaf(list,treeNode.right,s);
        }
        return list;

    }


    /**
     * 这道题我们拿到基本就可以确定是图的 dfs、bfs 遍历的题目了。题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 OO 要特殊处理，只要把边界上的 OO 特殊处理了，那么剩下的 OO 替换成 XX 就可以了。问题转化为，如何寻找和边界联通的 OO，我们需要考虑如下情况。
     *
     * 作者：Ac_pipe
     * 链接：https://leetcode-cn.com/problems/surrounded-regions/solution/bfsdi-gui-dfsfei-di-gui-dfsbing-cha-ji-by-ac_pipe/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param board
     * @param i
     * @param j
     *
     * 思路：将边界的O设置成#
     */
    public void dfs(char[][] board,int i,int j){
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]=='X'||board[i][j]=='#'){
            //跳出边界条件
            return;
        }
        //将边界的O设置成#
        board[i][j]='#';
        dfs(board,i--,j);
        dfs(board,i++,j);
        dfs(board,i,j--);
        dfs(board,i,j++);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i=0;i<gas.length;i++){
            int pass = isPass(i, gas, cost);
            if (pass!=-1){
                return pass;
            }
        }

        return -1;
    }

    /**
     * 将数组变成环
     * @param i
     * @param gas
     * @param cost
     * @return
     */
    public int isPass(int i,int[] gas,int[] cost){
        int num=i;
        int len=cost.length;
        int count=0;
        int oil=0;
        int j=i;
        while (true){
            if (count==cost.length){
                break;
            }
            int index=j%gas.length;
            oil=oil+gas[index]-cost[index];
            if (oil<0){
                return -1;
            }
            count++;
            j++;

        }
        if (oil>=0){
            return num;
        }
        return -1;
    }


    public int[] singleNumber(int[] nums) {
       List<Integer> list=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
       for (int i=0;i<nums.length;i++){
           int num=nums[i];
          if (map.containsKey(num)){
              map.put(num,map.get(num)+1);
          }else {
              map.put(num,1);
          }
       }
        for (Integer key : map.keySet()) {
            if (map.get(key)==1){
                list.add(key);
            }
        }
        Object[] objects = list.toArray();
        int[] arr={(int)objects[0],(int)objects[1]};
        return arr;
    }



    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (q==null&&p==null){
            return true;
        }
        if((p!=null&&q==null)||(q!=null&&p==null)||(p.val!=q.val)){
            return  false;
        }

        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    public static int[][] calucateNum(int[][] arr,int m,int n){
        int[][] newArr=new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (arr[i][j]==1){
                    int num = calucateMinLen(arr, m, n, i, j);
                    newArr[i][j]=num;
                }
            }
        }
        return newArr;
    }

    public static int calucateMinLen(int[][] arr, int m,int n,int x,int y){
        int min=m+n;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                boolean flag=!(i==x&&j==y);
                if (flag&&arr[i][j]==0){
                    int tempLen=Math.abs(i+j-x-y);
                    if (tempLen<min){
                        min=tempLen;
                    }
                }
            }
        }
        return min;
    }


    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc，则输出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
     *
     * 可以这样想：固定第一个字符a，求后面两个字符bc的排列。当两个字符bc的排列求好之后，我们把第一个字符a和后面的b交换，得到bac;
     * 接着我们固定第一个字符b，求后面两个字符ac的排列。现在是把c放到第一位置的时候了。记住前面我们已经把原先的第一个字符a和后面的b做了交换，
     * 为了保证这次c仍然是和原先处在第一位置的a交换，我们在拿c和第一个字符交换之前，先要把b和a交换回来。在交换b和a之后，再拿c和处在第一位置的a进行交换，得到cba。
     * 我们再次固定第一个字符c，求后面两个字符b、a的排列。这样写成递归程序如下：
     * @param strArrs
     * @param i
     */
    public static void permutateSequence(char[] strArrs,int i){

        if (strArrs==null||i>strArrs.length||i<0){
            return;
        }else if (i==strArrs.length){
            System.out.println(strArrs);
        }else {
            //交换生成排列组合
            char temp;
            for(int j=i;j<strArrs.length;j++){
                //固定前面的字符串，然后交换出新的组合
                temp = strArrs[j];//
                strArrs[j] = strArrs[i];
                strArrs[i] = temp;
                permutateSequence(strArrs, i+1);
                //字符串还原
                temp = strArrs[j];//
                strArrs[j] = strArrs[i];
                strArrs[i] = temp;
            }
        }

    }

    public static int uniquePaths(int m, int n) {
        int[][] num=new int[m][n];
        for(int i=0;i<m;i++){
            num[i][0]=1;
        }
        for(int j=0;j<n;j++){
            num[0][j]=1;
        }
        for (int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                //典型的动态规划，由之前的状态决定
                num[i][j]=num[i-1][j]+num[i][j-1];
            }
        }
        return num[m-1][n-1];
    }

    /**
     * 找出他们前n个字符串的共有的长度
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                //慢慢缩减长度
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }


    public static boolean isPalindrome(int x) {
        String s=String.valueOf(x);
        int i=0;
        int j=s.length()-1;
        while (i<j){
            if (s.charAt(i)==s.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }


    public boolean isValid(String s) {
        if (s.length()%2==1){
            return false;
        }
        Stack<Character> stack=new Stack<Character>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='['){
                stack.push(s.charAt(i));
                continue;
            }
            if (s.charAt(i)==')'){
                if (stack.empty()||stack.pop()!='('){
                    return false;
                }
            } if (s.charAt(i)=='}'){
                if (stack.empty()||stack.pop()!='{'){
                    return false;
                }
            } if (s.charAt(i)==']'){
                if (stack.empty()||stack.pop()!='['){
                    return false;
                }
            }
        }
        if (stack.empty()){
            return true;
        }
        return false;
    }




    public static int cutRope(int target) {

        int max=0;
        for(int i=2;i<target;i++){
            int n = target / i;
            int y=target%i;
            int temp=(int)(Math.pow(n,i-y)*Math.pow(n+1,y));
            max=Math.max(temp,max);
        }

        return max;
    }







    public static int count(int[][] data,int num){
        int len=data.length;
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<data.length;i++){
            for (int j=0;j<data[i].length;j++){

                //顺时针的多只兔子
                for (int n=i+1;n<i+num;n++){
                    //取模进行计算,形成环状
                    for (int m=0;m<data[n%len].length;m++){
                        //两只兔子进行比较
                        if (data[i][j] == data[n%len][m]){
                            set.add(data[i][j]);
                        }
                    }

                }
            }
        }

       return set.size();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode out=new ListNode(0);
        ListNode head=out;
        int in=0;
        while (!(l1  == null && l2 == null)) {
            int x = (l1 == null ? 0 : l1.val);
            int y = (l2 == null ? 0 : l2.val);
            int sum = x + y + in;
            in = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            out.next = listNode;
            out = out.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (in >0){
            ListNode listNode = new ListNode(in);
            out.next = listNode;
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode out=new ListNode(0);
        ListNode head=out;
        while(l1 != null || l2 !=null){
            int x=(l1 == null?Integer.MAX_VALUE:l1.val);
            int y=(l2 == null?Integer.MAX_VALUE:l2.val);
            if (y < x){
                ListNode listNode=new ListNode(y);
                out.next=listNode;
                out=out.next;
                if (l2 != null){
                    l2=l2.next;
                }
            }else {
                ListNode listNode=new ListNode(x);
                out.next=listNode;
                out=out.next;
                if (l1 != null){
                    l1=l1.next;
                }
            }
        }
        return head.next;
    }





    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head =null;
        for (int i=0; i< lists.length;i++){
            head=mergeTwoLists(head,lists[i]);
        }
        return head;
    }





    public int[][] merge(int[][] intervals) {
        for(int i=0; i< intervals.length; i++){

        }

        return intervals;
    }


    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length; i++){
            for (int j=0; j<grid[i].length ;j++){
                if (grid[i][j] == '1'){
                    count++;
                    dfs(i,j,grid);
                }
            }
        }

        return count;
    }
    public void dfs(int i, int j, char[][] data){
        if (i<0 || i>=data.length || j<0 || j>= data[i].length || data[i][j] !='1'){
            return;
        }
        data[i][j]='0';
        dfs(i,j-1,data);
        dfs(i,j+1,data);
        dfs(i-1,j,data);
        dfs(i+1,j,data);
    }


    public int findKthLargest(int[] nums, int k) {

      Arrays.sort(nums);
      return nums[nums.length-1-k];
    }


    public boolean canJump(int[] nums) {
        int max=0;
        for(int i=0; i<nums.length;i++){
            if (i<=max){
                max=Math.max(max,i+nums[i]);
                if (max>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }




    public boolean searchMatrix(int[][] matrix, int target) {
        Integer m=null;
        Integer n=null;

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                if (matrix[i][j] == target){
                    m=i;
                    n=j;
                }
            }
        }
        if (n==null && m==null){
            //没有找到元素
            return false;
        }

        return true;
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


    public String decodeString(String s) {
        String out="";
        boolean instack=false;
        Stack<Character> stack=new Stack<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c>='0' && c<='9'){
               instack=true;
            }
            //出栈
            if (c==']'){
                instack=false;
                //开始出栈
                String pingjie="";
                String temp="";
                char pop=stack.pop();
                while(pop!='['){
                    temp=pop+temp;
                    pop=stack.pop();
                }

                //开始出栈数字
                String num="";
                pop=stack.pop();
                while (!stack.empty() && (pop>='0'&& pop<='9')){
                    num=pop+num;
                    pop=stack.pop();
                    if (!(pop>='0'&& pop<='9')){
                        stack.push(pop);
                    }
                }
                Integer n=Integer.valueOf(num);
                //开始拼接字符串
                while (n>0){
//                    System.out.println(n);
                    pingjie=pingjie+temp;
                    n--;
                }
                //如果栈不为空，继续入栈
                if (!stack.empty()){
                    for (int k=0;k<pingjie.length();k++){
                        stack.push(pingjie.charAt(k));
                    }
                }else {
                    //为空直接拼接到out
                    out=out+pingjie;
                }

                continue;
            }
            //进站
            if (instack){
                stack.push(c);
            }else{
                out=out+c;
            }

        }
        return out;
    }


    @Test
    public void test1111(){
        System.out.println(decodeString("3[a2[c]]"));
    }








}
