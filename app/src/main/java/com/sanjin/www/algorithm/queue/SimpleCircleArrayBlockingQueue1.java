package com.sanjin.www.algorithm.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个简化版的以数组实现的循环队列
 *
 * 使用显式锁ReentrantLock
 */
public class SimpleCircleArrayBlockingQueue1 {

    private static final int FULL_NUM = 10;

    private static final int EMPTY_NUM = 0;

    private String[] queue;

    private int count = 0;

    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    private int head;
    private int tail;

    public SimpleCircleArrayBlockingQueue1() {
        this.queue = new String[FULL_NUM];

        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    private String take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == EMPTY_NUM) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public void put(String newData) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == FULL_NUM) {
                lock.wait();
            }
            enqueue(newData);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(String newData) {
        final Object[] items = this.queue;
        items[tail] = newData;
        count ++;
        tail = (tail + 1) % FULL_NUM;
        lock.notify();
    }

    private String dequeue() {
        // 为什么要新声明一个items，而不是直接使用成员变量queue ？from Array
        final Object[] items = this.queue;
        String data = (String) items[head];
        items[head] = null;
        count --;
        head = (head + 1) % FULL_NUM;
        lock.notify();
        return data;
    }
}
