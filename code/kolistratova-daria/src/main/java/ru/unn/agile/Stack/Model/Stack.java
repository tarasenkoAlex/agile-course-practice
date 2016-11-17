package ru.unn.agile.Stack.Model;

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

    public void print() {
        if (isEmpty()) {
            System.out.print("");
        } else if (stackPointer == 0) {
            System.out.print(array[stackPointer]);
        } else {
            final StringBuilder str = new StringBuilder();
            for (int i = 0; i <= stackPointer; i++) {
                str.append(array[i] + " ");
            }
            System.out.print(str);
        }
    }
}
