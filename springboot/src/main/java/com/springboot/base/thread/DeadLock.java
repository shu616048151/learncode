package com.springboot.base.thread;

/**
 * @author shuxibing
 * @date 2019/9/27 20:53
 * @uint d9lab
 * @Description: 造成死锁的例子
 *
 * 死锁条件：互斥条件：进程要求对所分配的资源进行排它性控制，即在一段时间内某资源仅为一进程所占用。
         * 请求和保持条件：当进程因请求资源而阻塞时，对已获得的资源保持不放。
         * 不剥夺条件：进程已获得的资源在未使用完之前，不能剥夺，只能在使用完时由自己释放。
         * 环路等待条件：在发生死锁时，必然存在一个进程--资源的环形链。
 */
public class DeadLock implements Runnable{
    //静态变量是共有的对象
    public static Object o1=new Object();
    public static Object o2=new Object();
    private int flag;

    public static void main(String[] args) {
        DeadLock deadLock1=new DeadLock();
        DeadLock deadLock2=new DeadLock();
        deadLock1.setFlag(1);
        deadLock2.setFlag(0);
        Thread thread1=new Thread(deadLock1);
        Thread thread2=new Thread(deadLock2);
        thread1.start();
        thread2.start();
    }

    /**
     * 对相同资源进行加锁造成死锁的情况
     */
    @Override
    public void run() {
        System.out.println("flag="+flag);
        if (flag==1){
            synchronized (o1){
                try {
                    System.out.println("执行A操作");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if (flag == 0){
            synchronized (o2){
                try {
                    System.out.println("执行B操作");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }

            }
        }
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }


}
