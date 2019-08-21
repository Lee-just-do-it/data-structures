package com.java.javasources.structures.list.twin;

import java.util.NoSuchElementException;

/**
 * @author 木子Lee
 * @desc 定义双向链表 ：这里更多的是给出双向链表的定义
 * 不同于单链表的是，可以方便的找到当前结点的前一个结点，不至于向单链表一样需要再次遍历
 * 这里仅仅只是给出双向链表的定义
 * 变化结点需要改变 Node  指向上一个结点指针指向：<pre>  指向下一个结点指针指向：<next>
 *       其他的操作请参考单链表的思想  以及其他操作  可直接 参看java源代码 <java.util.LinkedList> 的底层实现
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

    /**
     * 获取第一个结点
     *
     * @return
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    /**
     * 获取最后一个结点
     *
     * @return
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * 移除某个结点
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 转换
     *
     * @param a
     * @param <T>
     * @return
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * 获取size
     *
     * @return
     */
    public int size() {
        return size;
    }

    private E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.pre;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.pre = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.pre = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
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
