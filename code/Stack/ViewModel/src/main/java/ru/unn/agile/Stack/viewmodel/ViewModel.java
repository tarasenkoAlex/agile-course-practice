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
        stk.pop();
        txtlog.set("Stack is empty! Cannot pop!");
    }

    public void top() {
        stk.top();
        txtlog.set("Stack is empty! Cannot top!");
    }

    public void isEmpty() {
        stk.isEmpty();
        txtmsg.set("Stack is empty!");
    }

    public void isNotEmpty() {
        txtmsg.set("Stack is not empty!");
    }


    public void print() {
        stk.print();
    }
}
