package main;

/**
 * Created by dkolistr on 08.11.2016.
 */

public class Stack {
    private Integer[] array;
    private int cap = 2;
    private int len = -1;

    public Stack(){
        array = new Integer[cap];
    }

    private void resize(){
        cap *= 2;
        Integer[] arrayCopy = new Integer[cap];
        System.arraycopy(array, 0, arrayCopy, 0, len + 1);
        array = arrayCopy;
    }

    public void push(Integer number) {
        if (len + 1 >= cap) {
            resize();
        }
        array[++len] = number;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        return array[len--];
    }

    public boolean isEmpty() {
        return len < 0;
    }

    public Integer top() {
        if (isEmpty()) {
            return null;
        }
        return array[len];
    }

    public void print() {
        if (isEmpty()) {
            System.out.print("");
        }
        else if (len == 0) {
            System.out.print(array[len]);
        }
        else {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i <= len; i++) {
                str.append(array[i] + " ");
            }
            System.out.print(str);
        }
    }
}
