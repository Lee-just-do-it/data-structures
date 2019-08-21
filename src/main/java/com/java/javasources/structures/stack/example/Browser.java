package com.java.javasources.structures.stack.example;

/**
 * @author 木子Lee
 * @desc 栈实现浏览器的前进后台功能
 * @date 2019/8/21 22：39
 * @since 1.0
 */
@SuppressWarnings("all")
public class Browser {

    private String cur;
    private Stack back;
    private Stack forward;

    public Browser() {
        this.back = new Stack();
        this.forward = new Stack();
    }

    /**
     * 打开页面
     */
    public void openPage() {
        if (cur != null) {
            back.push(cur);
            forward.clear();
        }
        show(cur, "open");
    }

    /**
     * 回退
     *
     * @return
     */
    public String goBack() {
        if (checkBack()) {
            forward.push(cur);
            String back = this.back.pop();
            show(back, "back");

            return back;
        }
        return null;
    }

    /**
     * 前进
     *
     * @return
     */
    public String goForward() {
        if (checkForward()) {
            back.push(cur);
            String forward = this.forward.pop();
            show(forward, "forward");
            return forward;
        }
        return null;
    }

    public void show(String url, String prefix) {
        cur = url;
    }

    private boolean checkBack() {
        return back.size() > 0;
    }

    private boolean checkForward() {
        return forward.size() > 0;
    }

    public static class Stack {

        private int size;
        private Node top;

        static Node create(String item, Node next) {
            return new Node(item, next);
        }

        public void clear() {
            this.top = null;
            this.size = 0;
        }

        public void push(String item) {
            Node node = create(item, this.top);
            this.top = node;
            size++;
        }

        public String pop() {
            Node node = this.top;
            if (node == null) {
                return null;
            }
            this.top = node.next;
            if (size > 0) {
                size--;
            }
            return node.item;
        }

        public int size() {
            return size;
        }

        public static class Node {
            private String item;
            private Node next;

            public Node(String item, Node next) {
                this.item = item;
                this.next = next;
            }
        }
    }
}
