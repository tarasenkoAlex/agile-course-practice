package ru.unn.agile.Stack.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.Stack.model.Stack;

public class ViewModel {
    private final StringProperty txtinput = new SimpleStringProperty();
    private final StringProperty txtprint = new SimpleStringProperty();
    private final StringProperty txtlog = new SimpleStringProperty();
    private final StringProperty txtmsg = new SimpleStringProperty();

    private final Stack stk;

    public StringProperty txtinputProperty() {
        return txtinput;
    }

    public StringProperty txtprintProperty() {
        return txtprint;
    }

    public StringProperty txtlogProperty() {
        return txtlog;
    }

    public StringProperty txtmsgProperty() {
        return txtmsg;
    }

    public ViewModel() {
        txtinput.set("");
        txtprint.set("");
        txtlog.set("");
        txtmsg.set("");
        stk = new Stack();
    }

    public void push() {
        try {
            stk.push(Integer.parseInt(txtinput.get()));
        } catch (NumberFormatException e) {
            txtlog.set("Bad input!");
        }
    }

    public Stack getStack() {
        return stk;
    }

    public void pop() {
        if (stk.isEmpty()) {
            txtlog.set("Stack is empty! Cannot pop!");
        } else {
            stk.pop();
        }
    }

    public void top() {
        if (stk.isEmpty()) {
            txtmsg.set("Stack is empty! Cannot top!");
        } else {
            txtmsg.set(stk.top().toString());
        }
    }

    public boolean isEmpty() {
        if (stk.isEmpty()) {
            txtlog.set("Stack is empty!");
            return true;
        } else {
            txtlog.set("Stack is not empty!");
            return false;
        }
    }

    public void print() {
        txtprint.set(stk.print());
    }
}

