package com.shu.alogrithm.mianshi;

/**
 * @Author shuxibing
 * @Date 2020/9/4 18:54
 * @Uint d9lab_2019
 * @Description:
 */
public class Bzhan2021 {
    public static void main(String[] args){
        int[] data={2,1,2,2,3};

        test1(data);

    }



    public static void test1(int[] data){
        int[] out=new int[data.length];
        int count=0;
        for (int i=0;i<data.length;i++){
            if (i>0 && i<data.length-1){
                if (data[i]<=data[i-1] && data[i]<=data[i+1]){
                    out[i]=1;
                    count++;
                }
            }
        }
        for (int i : out) {
            System.out.println(i);
        }
        while (count != data.length){
            int temp=0;
            for(int i=1;i<data.length-1;i++){
                if (out[i]!=0 ){
                    temp++;
                }
                if (data[0]>data[1] && out[1]!=0){
                    out[0]=out[1]+1;
                }else {
                    out[0]=1;
                }

                if (data[data.length-1]>data[data.length-2] && out[data.length-2]!=0){
                    out[data.length-1]=out[data.length-2]+1;
                }else {
                    out[data.length-1]=1;
                }

                if (data[i]>data[i-1] && data[i]<data[i+1] && out[i-1] != 0){
                    out[i]=out[i-1]+1;
                }

                if (data[i]<data[i-1] && data[i]>data[i+1] && out[i+1] != 0){
                    out[i]=out[i+1]+1;
                }

                if (data[i]>=data[i-1] && data[i]>=data[i+1] && out[i-1] != 0 && out[i+1] !=0) {
                    out[i] = Math.max(out[i + 1], out[i - 1]) + 1;
                }
            }
            count=temp+2;
        }

        for (int i : out) {
             System.out.println(i);
        }
    }

}
