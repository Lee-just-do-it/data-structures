package com.java.javasources.structures.list.single;

/**
 * @author 木子Lee
 * @desc 单链表的部分高级操作
 * @date 2019/8/13 1；24
 * @since 1.0
 */
public class LinkedListAlgo<E> {

    public static LinkedListAlgo getInstance() {
        return NodeHodler.INSTANCE;
    }

    /**
     * 链表环检测
     *
     * @param list
     * @return 是否存在环
     */
    public boolean checkCircle(Node<E> list) {
        if (list == null) {
            return false;
        }
        Node fast = list.next;
        Node slow = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除倒数第K个结点
     *
     * @param list
     * @param k
     * @return
     */
    public Node deleteLastKth(Node<E> list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }
        if (fast == null) {
            return list;
        }
        Node slow = list;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    /**
     * 有序链表的合并 【哨兵soldier优化】
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode soldier = new ListNode(0);
        ListNode p = soldier;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return soldier.next;
    }

    /**
     * 仅仅用于 有序链表的合并思想
     *
     * @param <E>
     */
    private static class ListNode<E> {
        private int val;
        ListNode<E> next;

        public ListNode(int i) {
        }

    }

    /**
     * 求中间结点
     *
     * @param list
     * @return
     */
    public Node findMiddleNode(Node<E> list) {
        if (list == null) {
            return null;
        }
        Node<E> fast = list;
        Node<E> slow = list;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static class Node<E> {
        private E data;
        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class NodeHodler {
        private static final LinkedListAlgo INSTANCE = new LinkedListAlgo();
    }
}
