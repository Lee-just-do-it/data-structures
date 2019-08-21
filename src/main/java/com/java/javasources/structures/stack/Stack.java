package com.java.javasources.structures.stack;

/**
 * @author 木子Lee
 * @desc 栈的基本定义
 * @date 2019/8/21 20：21
 * @since 1.0
 */
@SuppressWarnings("all")
public class Stack<E> {

    private Node<E> top;

    /**
     * 压入栈顶
     *
     * @param e
     */
    public void push(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (null == top) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * 弹栈
     *
     * @return
     */
    public E pop() {
        if (top == null) {
            return null;
        }
        E e = top.item;
        top = top.next;
        return e;
    }

    private static class Node<E> {
        private E item;
        private Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }

        public E getItem() {
            return item;
        }
    }
}
