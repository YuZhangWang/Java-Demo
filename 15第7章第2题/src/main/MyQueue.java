package main;

import java.util.*;

public class MyQueue {
    private Object data[];
    private int size;

    public MyQueue() {
        data = new Object[10];
    }

    public boolean add(Object obj) {
        expandList(size + 1);
        data[size++] = obj;
        return true;
    }

    public Object remove(int index) {
        Rangcheck(index);
        Object obj = data[index];
        int num = size - index - 1;
        if (num > 0) {
            System.arraycopy(data, index + 1, data, index, num);
        }
        data[--size] = null;
        return obj;
    }

    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public Object get(int index) {
        Rangcheck(index);
        return data[index];
    }

    public Object set(int index, Object obj) {
        Rangcheck(index);
        Object oldobject = data[index];
        data[index] = obj;
        return oldobject;
    }

    public int size() {
        return size;
    }

    //扩容
    public void expandList(int addindex) {
        int oldindex = data.length;
        if (addindex > oldindex) {
            int newindex = (oldindex * 3) / 2 + 1;//此处是每次扩容的方式
            if (addindex > newindex) newindex = addindex;
            data = Arrays.copyOf(data, newindex);
        }
    }

    public void Rangcheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
