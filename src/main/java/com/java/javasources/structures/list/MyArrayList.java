package com.java.javasources.structures.list;

/**
 * @author
 * @desc
 * @date
 * @since
 */
@SuppressWarnings("all")
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] theItems;
    private int theSize;

    /**
     * Construct an empty ArrayList.
     */
    public MyArrayList() {
        doClear();
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    /**
     * Returns true if this collection is empty.
     *
     * @return true if this collection is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public T get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        }
        return theItems[idx];
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        }
        T old = theItems[idx];
        theItems[idx] = newVal;

        return old;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at the specified index.
     *
     * @param x any object.
     * @return true.
     */
    public void add(int idx, T x) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }

        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];

        theItems[idx] = x;
        theSize++;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public T remove(int idx) {
        T removedItem = theItems[idx];

        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;

        return removedItem;
    }

    /**
     * Change the size of this collection to zero.
     */
    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Returns a String representation of this collection.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (T x : this) {
            sb.append(x + " ");
        }
        sb.append("]");

        return new String(sb);
    }

    /**
     * This is the implementation of the ArrayListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyArrayList.
     */
    private class ArrayListIterator implements java.util.Iterator<T> {
        private int current = 0;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }
}

