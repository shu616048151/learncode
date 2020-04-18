package com.shu.base.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuxibing
 * @date 2019/9/30 9:57
 * @uint d9lab
 * @Description:  在一些应用场合中，需要等待某个条件达到要求后才能做后面的事情；同时当线程都完成后也会触发事件，以便进行后面的操作。 这个时候就可以使用CountDownLatch
 * 。CountDownLatch最重要的方法是countDown()和await()，前者主要是倒数一次，后者是等待倒数到0，如果没有到达0，就只有阻塞等待了。
 * 一般是主线程控制开关
 */
// 一个CountDouwnLatch实例是不能重复使用的，也就是说它是一次性的，锁一经被打开就不能再关闭使用了，如果想重复使用，请考虑使用CyclicBarrier。
public class CountDownLatchTest {
    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {
// 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
// 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
// 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 如果当前计数为零,则此方法立即返回。
                        // 一直处于等转态
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException e) {
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        // begin减一，开始游戏
        System.out.println("Game Start");
        begin.countDown();
        // 等待end变为0，即所有选手到达终点，也就是等到所有选手的比赛状态
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }
}
