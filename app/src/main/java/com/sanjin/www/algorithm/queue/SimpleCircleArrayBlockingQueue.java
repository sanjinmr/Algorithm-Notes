package com.sanjin.www.algorithm.queue;

/**
 * 一个简化版的以数组实现的循环队列
 *
 * 使用隐式锁Synchronized
 */
public class SimpleCircleArrayBlockingQueue {

    private static final int FULL_NUM = 10;

    private static final int EMPTY_NUM = 0;

    private String[] queue;

    private int count = 0;

    private final Object lock = new Object();

    private int head;
    private int tail;

    public SimpleCircleArrayBlockingQueue() {
        this.queue = new String[FULL_NUM];
    }

    private String take() {
        String result = "";
        synchronized (lock) {
            while (count == EMPTY_NUM) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            result = dequeue();
        }

        return result;
    }

    public void put(String newData) {
        synchronized (lock) {
            while (count == FULL_NUM) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            enqueue(newData);
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
