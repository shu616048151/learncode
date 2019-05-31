package com.shu.threadpool;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author shuxibing
 * @date 2019/5/16 0016 11:53
 * @uint d9lab
 * @Description:
 */

/**
 * 使用线程池的四种包装的静态方法。
 */
public class ThreadPooTest {
    public static void main(String[] args){
        long time1=System.currentTimeMillis();
        //可以选择几种方式
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<150;i++){
            executorService.execute(new MyRunnable(i));
        }
        executorService.shutdown();
        while(true){
            if(executorService.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                //当前main主线程休眠，等待线程关闭
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long time2=System.currentTimeMillis();
        System.out.println("总共耗时："+(time2-time1));

    }

    /**
     * 使用ThreadPoolExecutor的方式产生线程池
     */
    @Test
    public void ThreadPoolExecutorTest(){
       ExecutorService executorService=new ThreadPoolExecutor(10, 20,  500, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(512) , new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i=0;i<150;i++){
            executorService.execute(new MyRunnable(i));
        }
        executorService.shutdown();
        while (true){
            if (executorService.isTerminated()){
                System.out.println("线程池的线程已经完全结束");
                break;
            }
            try {
                Thread.sleep( 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
