package com.java.javasources.structures.queue;

/**
 * @author 木子Lee
 * @desc 链表实现队列
 * @date 2019/8/21 23：15
 * @since 1.0
 */
public class LinkedListToQueue {
    private Node head = null;
    private Node tail = null;

    public void enqueue(String value) {
        if (tail == null) {
            Node newNode = new Node(value, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }

    public String dequeue() {
        if (head == null) {
            return null;
        }

        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
