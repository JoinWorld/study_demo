package com.king.li.producer_consumer;

/**
 * 线程的虚假唤醒：
 * 当线程在某个条件变量下等待时,即使其他线程没有broadcast or signaled 这个条件变量,该线程仍然可能被唤醒
 * 产生原因：
 * 由于唤醒和获取锁不是原子性的，如果使用if条件判断，只会判断一次，然后如果获取不到锁就被阻塞等待唤醒，唤醒后继续往下执行，需要将wait()放入while循环中
 * 缺点：
 * 会导致系统性能下降
 *
 * @author li
 * @create 2021-05-25-10:20
 */
public class SpuriousWakeup {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increament();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decreament();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increament();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decreament();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}

class Data {
    private int product;

    synchronized void increament() throws InterruptedException {
//        if (product > 0){
        while (product > 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ",product => " + product);
        product++;
        this.notifyAll();
    }

    synchronized void decreament() throws InterruptedException {
//        if (product == 0){
        while (product == 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ",product => " + product);
        product--;
        this.notifyAll();
    }
}
