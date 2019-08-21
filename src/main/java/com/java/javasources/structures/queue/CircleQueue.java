package com.java.javasources.structures.queue;

/**
 * @author 木子Lee
 * @desc 循环队列
 * 1.设置变量flag,空队列 head = tail,并且flag = 0;   head = tail,并且flag = 1 队列满;
 * 2.留空一个位置的办法：队列空 tail = head；队列满的时候，数组元素中还有一个空位置表示
 * tail 可能比 head大，也可能小。所以他们相差一个位置时就满的情况，但也可能相差一整圈
 * 设 队列但的大小为 size ,队列满的条件就是 （tail + 1) % size = head
 * 当tail > head 时候，队列长为 tail - head ;反之，队列长度为 tail - head + size
 * 则：（tail - head + size) % size
 * @date 2019/8/21 23：15
 * @since 1.0
 */
public class CircleQueue {

    private String[] items;
    private int n = 0;

    private int head = 0;
    private int tail = 0;

    public CircleQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}
