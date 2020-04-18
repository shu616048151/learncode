package com.shu.threadpool;

import com.sun.xml.internal.bind.v2.model.core.ReferencePropertyInfo;

/**
 * @author shuxibing
 * @date 2019/5/16 0016 14:32
 * @uint d9lab
 * @Description:
 */
public class MyRunnable implements Runnable {
    private int num;

    public MyRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {
            try {
                System.out.println("myrunnnable的线程代号：" + num);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
