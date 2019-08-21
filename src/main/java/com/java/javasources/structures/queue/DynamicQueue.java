package com.java.javasources.structures.queue;

/**
 * @author 木子Lee
 * @desc 动态队列
 * @date 2019/8/21 23：15
 * @since 1.0
 */
public class DynamicQueue {

    private String[] items;
    private int n = 0;

    private int head = 0;
    private int tail = 0;

    public DynamicQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        if (tail == n) {
            if (head == 0) {
                return false;
            }
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        tail++;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String ret = items[head++];
        return ret;
    }

}
