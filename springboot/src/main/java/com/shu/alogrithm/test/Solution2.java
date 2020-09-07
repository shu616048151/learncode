package com.shu.alogrithm.test;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shuxibing
 * @date 2019/12/4 18:03
 * @uint d9lab
 * @Description:
 */
public class Solution2 {

    public static void main(String[] args){
//        Scanner scanner=new Scanner(System.in);
//        Set<Integer> set=new TreeSet<>();
//        Integer s =Integer.valueOf(scanner.nextLine());
//        System.out.println("行数："+s);
//        set = readData(s, scanner, set);
//        Integer s1 =Integer.valueOf(scanner.nextLine());
//        set = readData(s1, scanner, set);
//        for (Integer integer : set) {
//            System.out.println(integer);
//        }
        Solution2 solution2= new Solution2();
        String result = solution2.getResult(180);
        System.out.println(result);

    }

    public static Set readData(int row,Scanner scanner,Set set){
        for (int i=0;i<row;i++) {
            Integer data = Integer.valueOf(scanner.nextLine());
            if (!set.contains(data)) {
                set.add(data);
            }
        }
        return set;
    }

    /**
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（如180的质因子为2 2 3 3 5 ）
     *
     * 最后一个数后面也要有空格
     * @param ulDataInput
     * @return
     */
    public String getResult(long ulDataInput){
        int index=2;
        StringBuilder stringBuilder=new StringBuilder();
        while (index<=ulDataInput){
            if (ulDataInput%index==0){
                ulDataInput=ulDataInput/index;
                stringBuilder.append(index).append(" ");
            }else {
                index++;
            }
        }
        return stringBuilder.toString();

    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        deque.add(root);
        while(!deque.isEmpty()){
            TreeNode t = deque.pop();
            list.add(t.val);
            if(t.left != null){
                deque.add(t.left);
            }
            if(t.right != null){

                deque.add(t.right);
            }
        }
        return list;
    }


    public ArrayList<String> Permutation(String str) {
        List<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
//            PermutationHelper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }
        return (ArrayList)res;
    }

    public int GetUglyNumber_Solution(int index) {
        int i=1;
        int num=0;
        while (index!=1){
            i++;
            boolean ugleNum = isUgleNum(i);
            if (ugleNum==true){
                index--;
            }
        }
        return i;
    }

    public boolean isUgleNum(int num){
        if (num==2||num==3||num==5){
            return true;
        }
        int k1=num%2;
        int k2=num%3;
        int k3=num%5;
        if (k1!=0&&k2!=0&&k3!=0){
            return false;
        }else {
            if (k1==0){
                boolean ugleNum = isUgleNum(num / 2);
                return ugleNum;
            }
            if (k2==0){
                boolean ugleNum = isUgleNum(num / 3);
                return ugleNum;
            }
            if (k3==0){
                boolean ugleNum = isUgleNum(num / 5);
                return ugleNum;
            }
        }
        return  false;
    }


    public boolean isNumeric(char[] str) {
        String num=String.valueOf(str);
        System.out.println(num);
        Pattern pattern=Pattern.compile("[+-]?[0-9]+(\\.[0-9]*)?([Ee][+-]?[0-9]+)?");
        Matcher matcher = pattern.matcher(num);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length;
        if (length==1){
            return array[0];
        }
        Map<Integer,Integer> map=new TreeMap<>();
        for (int i:array){
            if (map.containsKey(i)){
                Integer count = map.get(i)+1;
                if (count>length/2){
                    return i;
                }
                map.put(i,count);

            }else {
                map.put(i,1);
            }
        }

        return 0;
    }


    /**
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        int phigh = 2,plow = 1;

        while(phigh > plow){
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            if( cur < sum){
                phigh++;
            }

            if( cur == sum){
                ArrayList<Integer> list1=new ArrayList<>();
                for(int i = plow; i<=phigh; i++)
                {
                    list1.add(i);
                }
                list.add(list1);
                plow++;
            }

            if(cur > sum){
                plow++;
            }
        }
        return list;
    }


    /**
     * 类似地图的递归算法问题都可以使用此类算法
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, flag, threshold);
    }

    private int helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j)  > threshold || flag[i][j] == 1)
        {
            return 0;
        }
        flag[i][j] = 1;
        //此格子没有走，格子数加1
        return helper(i - 1, j, rows, cols, flag, threshold)
                + helper(i + 1, j, rows, cols, flag, threshold)
                + helper(i, j - 1, rows, cols, flag, threshold)
                + helper(i, j + 1, rows, cols, flag, threshold)
                + 1;
    }

    private int numSum(int i) {
        int sum = 0;
        do{
            sum += i%10;
        }while((i = i/10) > 0);
        return sum;
    }


    public Set deleteAndSort(int[] array){
        Set<Integer> set=new TreeSet<>();
       for (int i=0; i<array.length;i++){
           int num=array[i];
           if (!set.contains(num)){
               set.add(num);
           }
       }
       return set;

    }


}
