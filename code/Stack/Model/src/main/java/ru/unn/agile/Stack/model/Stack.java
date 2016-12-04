package ru.unn.agile.Stack.model;

public class Stack {
    private Integer[] array;
    private int capacity = 2;
    private int stackPointer = -1;

    public Stack() {
        array = new Integer[capacity];
    }

    private void resize() {
        capacity *= 2;
        final Integer[] arrayCopy = new Integer[capacity];
        System.arraycopy(array, 0, arrayCopy, 0, stackPointer + 1);
        array = arrayCopy;
    }

    public void push(final Integer number) {
        if ((stackPointer + 1) >= capacity) {
            resize();
        }
        array[++stackPointer] = number;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        return array[stackPointer--];
    }

    public boolean isEmpty() {
        return stackPointer < 0;
    }

    public Integer top() {
        if (isEmpty()) {
            return null;
        }
        return array[stackPointer];
    }

    public String print() {
        final StringBuilder str = new StringBuilder();
        if (isEmpty()) {
            str.append("Stack is empty! Nothing to print!");
        } else if (stackPointer == 0) {
            str.append(array[stackPointer].toString());
        } else {
            for (int i = 0; i <= stackPointer; i++) {
                str.append(array[i].toString() + " ");
            }
        }
        return str.toString();
    }
}
