package com.java.javasources.structures.list;

/**
 * @author 木子Lee
 * @desc 单链表相关 包含头结点的循环链表，即头结点不存储数据
 * 链接<com.java.javasources.structures.list.SingleLinkedList/> 功能点做补充说明
 * 以及 头尾结点 循环链表特定标识结点等
 * @date 2019/8/12 23：47
 * @since 1.0
 */
public class SingleIsHeadLinkedList<E> {

    /**
     * 头结点
     */
    private Node<E> head;

    /**
     * 最后一个结点
     */
    private Node<E> last;

    private int size;
    /**
     * 循环结点 指定为 rear = last 方便时间复杂度O（1）的定位 head last结点
     * 思考：假如没有头结点，在循环的时候通过 rear 结点是不是可以O(1)定位 链表的第一个结点 和 最后一个结点
     */
    private Node<E> rear;

    public SingleIsHeadLinkedList() {
        head = new Node<E>();
    }

    /**
     * 添加在链表尾结点
     * 相比没有头结点和没有尾结点的 <com.java.javasources.structures.list.SingleLinkedList/> addLast 时间复杂度从O(n)降为了O(1)
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> node = null;
        if (head.next == null) {
            node = new Node<>(e);
            head.next = node;
        } else {
            node = new Node<>(e, last);
        }
        last = rear = node;
        size++;
    }

    /**
     * 在头结点处添加
     *
     * @param e
     */
    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        if (head.next == null) {
            head.next = node;
            last = rear = node;
        } else {
            Node<E> cur = head.next;
            node.next = cur;
            head.next = node;
        }
        size++;
    }

    public Node<E> getFirst() {
        return head.next;
    }

    /**
     * 相比没有尾结点的 <com.java.javasources.structures.list.SingleLinkedList/> getLast 时间复杂度从O(n)降为了O(1)
     *
     * @return
     */
    public Node<E> getLast() {
        return last;
    }

    /**
     * 删除指定位置结点
     *
     * @param index
     */
    public void delete(int index) {
        checkElementIndex(index, true);
        int pos = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            if (pos == index) {
                x.next = x.next.next;
                break;
            }
            pos++;
        }
    }

    private void checkElementIndex(int index, Boolean seq) {
        if (!isElementIndex(index, seq)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * @param index
     * @param seq   TRUE获取第n个元素 FALSE:获取倒数第n个元素
     * @return
     */
    private boolean isElementIndex(int index, Boolean seq) {
        return seq ? (index >= 0 && index < size) : (index >= 0 && index <= size);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 静态内部类封装链表结点
     *
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
         * 空构造器用于设置头结点地址（JVM知识），不存储树数据
         */
        public Node() {
        }

        public Node(E e) {
            this.item = e;
        }

        public Node(E item, Node<E> node) {
            this.item = item;
            node.next = this;
        }
    }
}
