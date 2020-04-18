package com.springboot.base.concurrent;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuxibing
 * @date 2019/9/30 9:51
 * @uint d9lab
 * @Description:  CyclicBarrier同步线程辅助类,发生某种情况才会统一执行，自动触发，一般用于其他线程
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner(barrier, "1号选手")));
        executor.submit(new Thread(new Runner(barrier, "2号选手")));
        executor.submit(new Thread(new Runner(barrier, "3号选手")));
        executor.shutdown();
    }

}
class Runner implements Runnable {
        // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
        private CyclicBarrier barrier;
        private String name;
        public Runner(CyclicBarrier barrier, String name) {
            super();
            this.barrier = barrier;
            this.name = name;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random()).nextInt(8));
                System.out.println(name + " 准备好了...");
                // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。等所有线程到达的时候才开始执行下面的操作
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " 起跑！");
        }
}
