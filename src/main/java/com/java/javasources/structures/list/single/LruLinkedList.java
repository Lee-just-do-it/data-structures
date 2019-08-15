package com.java.javasources.structures.list.single;

/**
 * @author 木子Lee
 * @desc 基于链表的LRU缓存算法  简单实现
 * LRU：最近最少使用
 * 后续其他数据结构会更新此LRU改进
 * @date 2019/8/13 1：00
 * @since 1.0
 */
public class LruLinkedList<T> {
    /**
     * 容量
     */
    private final static Integer DEFAULT_CAPACITY = 100;

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 链表长度
     */
    private int size;

    /**
     * 链表容量
     */
    private int capacity;

    public LruLinkedList() {
        this.head = new Node<>();
        this.capacity = DEFAULT_CAPACITY;
    }

    public LruLinkedList(int capacity) {
        this.head = new Node<>();
        this.capacity = capacity;
    }

    public void add(T data) {
        Node preNode = findPreNode(data);

        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            if (size >= this.capacity) {
                deleteElemAtEnd();
            }
            intsertElemAtBegin(data);
        }
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(Node preNode) {
        Node temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        size--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void intsertElemAtBegin(T data) {
        Node next = head.getNext();
        head.setNext(new Node(data, next));
        size++;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private Node findPreNode(T data) {
        Node node = head;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getItem())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        Node ptr = head;

        if (ptr.getNext() == null) {
            return;
        }

        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        Node tmp = ptr.getNext();
        ptr.setNext(null);
        size--;
    }

    private class Node<T> {

        private T item;

        private Node next;

        public Node(T t) {
            this.item = t;
        }

        public Node(T t, Node next) {
            this.item = t;
            this.next = next;
        }

        public Node() {
            this.next = null;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
