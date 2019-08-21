package com.java.javasources.uml;

/**
 * @author 木子Lee
 * @desc UML类图简介
 * @date 2019/8/17 22：12
 * @since 1.0
 */
public abstract class AbstractHandler {

    protected abstract void uml(String content);

    protected void open(String s) {
        uml(s);
        close();
    }

    private void close() {

    }

    ;
}
