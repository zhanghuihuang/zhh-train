package com.zhh.train.mylist;

import java.util.*;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/22 10:41 上午
 */
public class MyArrayList<E> implements List<E> {
    private Object[] table;
    private static final Object[] EMPTY_TABLE = {};
    private int capacity = 8;
    private float capacityThreshold = 0.75f;  //容量阈值,超过75%时,进行扩容
    private int size = 0;

    public MyArrayList() {
        this.table = new Object[this.capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.table = new Object[this.capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (table[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(table, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        this.table = EMPTY_TABLE;
    }

    @Override
    public E get(int index) {
        return (E) table[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("数组下标越界");
        }
        E oldValue = (E) table[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(table, index + 1, table, index, numMoved);
        }
        table[--size] = null;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
