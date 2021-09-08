package main;

import java.util.*;

public class MyStack<T> {
    ArrayList a = new ArrayList<>();

    public boolean isEmpty() {
        return a.isEmpty();
    }

    public T peek() {
        return (T) a.get(0);
    }

    public T pop() {
        return (T) a.remove(0);
    }

    public void push(T element) {
        a.add(0, element);
    }

    public int size() {
        return a.size();
    }

    public void clear() {
        a.clear();
    }
}
