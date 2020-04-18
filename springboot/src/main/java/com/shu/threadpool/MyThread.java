package com.shu.threadpool;

/**
 * @author shuxibing
 * @date 2019/5/16 0016 11:39
 * @uint d9lab
 * @Description:
 */
public class MyThread extends Thread {
    private int num;

    public MyThread(int num){
        this.num=num;
    }

    @Override
    public void run() {
        try {
             System.out.println("当前的线程代号："+num);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
