package com.java.javasources.structures.list.twin;

/**
 * @author 木子Lee
 * @desc 定义双向链表
 * 不同于单链表的是，可以方便的找到当前结点的前一个结点，不至于向单链表一样需要再次遍历
 * 这里仅仅只是给出双向链表的定义
 * 变化结点需要改变 Node  指向上一个结点指针指向：<pre>  指向下一个结点指针指向：<next>
 *       其他的操作请参考单链表的思想  或者 直接 参看java源代码 <java.util.LinkedList> 的底层实现
 * @date 2019/8/15 14:02
 * @since 1.0
 */
public class TwinLinkedList<E> {

    private Node<E> first;
    private Node<E> last;

    private int size;

    /**
     * 添加在头结点
     *
     * @param e
     */
    public void addFirst(E e) {
        Node<E> cur = first;
        Node<E> node = new Node(null, e, cur);
        first = node;
        if (null == cur) {
            last = node;
        } else {
            cur.pre = node;
        }
        size++;
    }

    /**
     * 添加在尾结点
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> cur = last;
        Node<E> node = new Node(cur, e, null);
        last = node;
        if (null == cur) {
            first = node;
        } else {
            cur.next = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    class Node<E> {
        E item;
        Node<E> pre;
        Node<E> next;

        public Node(Node<E> pre, E e, Node<E> next) {
            this.item = e;
            this.pre = pre;
            this.next = next;
        }
    }
}
