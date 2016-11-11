package com.unn.queue.model;

import java.util.LinkedList;
import java.util.NoSuchElementException;

class Queue<T> {

    private final LinkedList<T> list;

    Queue() {
        list = new LinkedList<>();
    }

    public T getHead() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void enqueue(final T lastElement) {
        list.addLast(lastElement);
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public boolean remove(final T element) {
        return list.remove(element);
    }

    public int searchElement(final T target) {
        for (T element : list) {
            if (element.equals(target)) {
                return list.indexOf(element);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    public void addElements(final Queue<? extends T> newQueue) {
        while (!newQueue.isEmpty()) {
            enqueue(newQueue.dequeue());
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (T element : list) {
            str.append(element.toString() + ", ");
        }
        return str.append("]").toString().replace(", ]", "]");
    }
}
