package ru.unn.agile.queue.viewmodel;

import ru.unn.agile.queue.model.Queue;

import java.util.List;
import java.util.NoSuchElementException;

public class ViewModel<T> {

    private final Queue<T> queue;

    private T value;
    private String result;

    public ViewModel() {
        queue = new Queue<>();
    }

    public void add() {
        if (isEmptyValue()) {
            result = "Value is empty!";
            return;
        }
        queue.enqueue(value);
        result = "'" + value + "' was added successfully";
    }

    public void remove() {
        if (queue.isEmpty()) {
            result = "Queue is empty! You don't delete value from empty queue!";
            return;
        }
        if (isEmptyValue()) {
            result = "Value is empty!";
            return;
        }
        if (queue.remove(value)) {
            result = "'" + value + "' was removed successfully";
        } else {
            result = "Value '" + value + "' is absent in the queue!";
        }
    }

    private boolean isEmptyValue() {
        return value.toString().isEmpty();
    }

    public void search() {
        if (queue.isEmpty()) {
            result = "Queue is empty!";
            return;
        }
        if (isEmptyValue()) {
            result = "Value is empty!";
            return;
        }
        try {
            int position = queue.searchElement(value);
            result = "The position of '" + value + "' is " + position;
        } catch (NoSuchElementException nsex) {
            nsex.getCause();
            result = "The value '" + value + "' is not found in queue";
        }
    }

    public void getSize() {
        if (queue.isEmpty()) {
            result = "Queue is empty!";
        } else {
            result = "The size of queue is " + queue.getSize();
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public List<T> getQueue() {
        return queue.getQueueAsList();
    }

    public String getResult() {
        return result;
    }
}
