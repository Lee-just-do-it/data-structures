package com.java.javasources.structures.list;

/**
 * @author 木子Lee
 * @desc 单链表相关 不包含头结点的，即头结点存储数据   【【单链表包含头结点和尾结点的 操作不同点 后续更新】】
 * @date 2019/8/12 20:07
 * @since 1.0
 */
public class SingleLinkedList<E> {

    private Node<E> current;

    private int size;

    /**
     * 添加在链表尾
     * @param e
     */
    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        if (current == null) {
            current = node;
        }else {
            Node cur = current;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }

    /**
     * 添加在链表头部
     * @param e
     */
    public void addFirst(E e){
        current = new Node<>(e, current);
        size++;
    }

    /**
     * 返回第n个结点元素
     * @param index
     * @return
     */
    public E get(int index) {
        checkElementIndex(index, true);
        return node(index).item;
    }

    /**
     * 查找链表倒数第n个结点
     * @param n
     * @return
     */
    private E lastIndexOf(int n){
        checkElementIndex(n, false);
        int index = size - n;
        return get(index);
    }

    /**
     * 链表反转
     * @return
     */
    public Node<E> reserve() {
        if (current == null || current.next == null) {
            return current;
        }
        return reserve(current);
    }

    /**
     * 获取链表长度
     * @return int
     */
    public int size(){
        return size;
    }

    private Node<E> reserve(Node<E> head) {
        Node pre = null;
        Node latter = null;
        while (head != null) {
            latter = head.next;
            head.next = pre;

            pre = head;
            head = latter;
        }
        return pre;
    }

    private Node<E> node(int index){
        Node<E> node = current;
        for (int i = 0;i < size && i != index;i++) {
            node = node.next;
        }
        return node;
    }

    private void checkElementIndex(int index, Boolean seq) {
        if (!isElementIndex(index, seq)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     *
     * @param index
     * @param seq TRUE获取第n个元素 FALSE:获取倒数第n个元素
     * @return
     */
    private boolean isElementIndex(int index, Boolean seq) {
        return seq ? (index >= 0 && index < size) : (index >= 0 && index <= size);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 静态内部类封装链表结点
     * @param <E>
     */
    private static class Node<E> {
        /**
         * 链表元素
         */
        E item;

        /**
         * 链表下个结点
         */
        Node<E> next;

        /**
         * 插入在链表尾last
         * @param item 需要插入的元素
         */
        public Node(E item) {
            this.item = item;
        }

        /**
         * 可用于创建在链表头部first插入元素
         * @param item 需要插入的元素
         * @param cur 当前链表
         */
        public Node(E item, Node<E> cur) {
            this.item = item;
            this.next = cur;
        }
    }
}
