package com.king.li.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程的虚假唤醒：
 * 当线程在某个条件变量下等待时,即使其他线程没有broadcast or signaled 这个条件变量,该线程仍然可能被唤醒
 * 产生原因：
 * 由于唤醒和获取锁不是原子性的，如果使用if条件判断，只会判断一次，然后如果获取不到锁就被阻塞等待唤醒，唤醒后继续往下执行，需要将wait()放入while循环中
 * 缺点：
 * 会导致系统性能下降
 * <p>
 * 此方法改用JUC的lock，并精准唤醒指定线程
 *
 * @author li
 * @create 2021-05-25-10:20
 */
public class SpuriousWakeup_lock {
    public static void main(String[] args) {
        Data_lock data = new Data_lock();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increament();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decreament();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.recordExecutes();
            }
        }, "C").start();
    }
}

class Data_lock {
    private int product;
    private int num;
    private int executes;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    void increament() {
        try {
            lock.lock();
            while (product > 0) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + ",product => " + product);
            product++;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void decreament() {
        try {
            lock.lock();
            while (product == 0) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + ",product => " + product);
            product--;
            num++;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void recordExecutes() {
        try {
            lock.lock();
            while (num == 0){
                condition3.await();
            }
            executes++;
            System.out.println(Thread.currentThread().getName() + ",executes => " + executes);
            num--;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
