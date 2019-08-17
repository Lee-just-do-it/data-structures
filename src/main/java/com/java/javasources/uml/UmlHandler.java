package com.java.javasources.uml;

/**
 * @author 木子Lee
 * @desc UML类图简介
 * @date 2019/8/17 22：12
 * @since  1.0
 */
public class UmlHandler extends AbstractHandler {

    private User user;

    public UmlHandler(UmlService service) {
        service = new UmlServiceImpl();
    }

    @Override
    public void uml(String content) {

    }
}
