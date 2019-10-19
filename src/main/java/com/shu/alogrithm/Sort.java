package com.shu.alogrithm;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * @author shuxibing
 * @date 2019/9/12 9:43
 * @uint d9lab
 * @Description:   java的几种排序的方式
 */
public class Sort {
    public static void main(String[] args){
        int[] array={9,1,3,2,5,6,4,8};
        int[] ints = selectSort(array);
        //int[] ints = quickSort(array,0,array.length-1);
        //打印数组
        for (int i=0;i<ints.length;i++){
            System.out.println(ints[i]);
        }

    }

    /**
     * 选择排序的基本思路：每次循环找到最小或者最大的数字。
     * 默认为升序排序
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array){
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                //交换顺序
                if (array[i]>array[j]){
                    int temp=array[j];
                    array[j]=array[i];
                    array[i]=temp;
                }
            }
        }
        return array;
    }

    /**
     * 冒泡排序的基本思路：每次循环找到找到最大的数，两个数之间相互比较即可
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        for (int i=0;i<array.length;i++){
            for (int j=1;j<array.length-i;j++){
                if (array[j-1]>array[j]){
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }

    /**
     * 插入排序：前面为有序区，后面是无序区，将后面的无序区的第一个数，进行与有序区的最后一个数进行比较，
     * 如果该数小于最后的一个数，将最后的一个数进行向后移一位，浮动前进，直到找到合适的位置，将数据进行插入。
     * @param shu
     * @return
     */

    public static int[] insertSort(int[] shu){
        for (int i = 0; i < shu.length; i++) {
           int temp=shu[i];
            int j=i-1;
            //j表示已经排好序的有序去长度
            //寻找合适的插入点，当找到某两个数的中间值就可以退出循环，找到temp的值得合适的位置
            while (j>=0&&shu[j]>temp) {
                shu[j+1]=shu[j];
                j--;
            }
            shu[j+1]=temp;
        }
        return shu;

    }


    /**
     * 快速排序的基本思路：每次循环找到每个数在整个排序数组中正确的位置,同时前面的数都小于temp，后面的数都大于temp
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSort(int[] array,int start,int end){
        if (start<0||end>=array.length){
            System.out.println("输入的参数有误");
        }
        int i=start;
        int j=end;
        if (start<end){
            int temp=array[i];
            while (i!=j){
                while (i<j&&array[j]>temp){
                    j--;
                }
                //交换数据
                array[i]=array[j];
                while (i<j&&array[i]<temp){
                    i++;
                }
                array[j]=array[i];
            }
            //找到temp的合适位置
            array[i]=temp;
            quickSort(array,start,i-1);
            quickSort(array,i+1,end);
        }
        return array;
    }

    /**
     * 堆排序的基本思路：先从非叶子节点构建一个堆。即所以得子节点作为底堆，然后首尾交换元素，循环调整堆，最终排好序。
     * @param arr
     * @return
     */
    public static void heapSort(int[] arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，

            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length){
        while(2*i+1<length){
            int k=2*i+1;
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >arr[i]){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                int temp=arr[i];
                arr[i] = arr[k];
                arr[k]=temp;
                i = k;	//破坏了原来的堆，需要重新调整，直接向下调整，直到调整完成。
            }else{
                break;
            }
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }










}
