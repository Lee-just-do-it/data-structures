package com.java.javasources.structures.array;

import java.util.Arrays;

/**
 * @author 木子Lee
 * @desc 动态扩容数组的简单实现
 * @since 1.0
 */
public class Array<E> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_LENGTH = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 数组元素量
     */
    private int length;
    /**
     * 数组容量(下标)
     */
    private int c;
    private E[] elements = null;

    public Array(int minCapacity) {
        if (minCapacity > 0) {
            elements = (E[]) new Object[minCapacity];
        } else if (minCapacity == 0) {
            elements = (E[]) new Object[DEFAULT_LENGTH];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + minCapacity);
        }
        c = minCapacity;
    }

    public Array() {
        this(DEFAULT_LENGTH);
    }

    public boolean add(E e) {
        ensureCapacityInternal(length + 1);
        elements[length++] = e;
        return Boolean.TRUE;
    }

    private E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    private int length() {
        return length;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity >= c) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < oldCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > MAX_ARRAY_SIZE && minCapacity > 0) {
            newCapacity = minCapacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= c) {
            throw new ArrayIndexOutOfBoundsException("Illegal array index" + index);
        }
    }
}
