package ru.unn.agile.Stack.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.Stack.model.Stack;

public class ViewModel {
    private final StringProperty txtinput = new SimpleStringProperty();
    private final StringProperty txtprint = new SimpleStringProperty();
    private final StringProperty txtlog = new SimpleStringProperty();
    private final StringProperty txttop = new SimpleStringProperty();

    private final Stack stk;

    private final StringProperty log = new SimpleStringProperty();
    private ILogger logger;

    public StringProperty txtinputProperty() {
        return txtinput;
    }

    public StringProperty txtprintProperty() {
        return txtprint;
    }

    public final String getTxtprint() {
        return txtprint.get();
    }

    public StringProperty txtlogProperty() {
        return txtlog;
    }

    public final String getTxtlog() {
        return txtlog.get();
    }

    public StringProperty txttopProperty() {
        return txttop;
    }

    public final String getTxttop() {
        return txttop.get();
    }

    public String getLog() {
        return log.get();
    }

    public StringProperty logProperty() {
        return log;
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        txtinput.set("");
        txtprint.set("");
        txtlog.set("");
        txttop.set("");
        log.set("");
        stk = new Stack();
    }

    private void log(final String s) {
        logger.log(s);
        log.set(logger.getLog());
    }

    public void push() {
        try {
            int number = Integer.parseInt(txtinput.get());
            stk.push(number);
            log(LogMessages.PUSHED + String.valueOf(number));
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
            int number = stk.pop();
            log(LogMessages.POP + String.valueOf(number));
        }
    }

    public void top() {
        if (stk.isEmpty()) {
            txttop.set("Stack is empty! Cannot top!");
        } else {
            String number = stk.top().toString();
            txttop.set(number);
            log(LogMessages.TOP + number);
        }
    }

    public boolean isEmpty() {
        if (stk.isEmpty()) {
            txtlog.set("Stack is empty!");
            log(LogMessages.IS_EMPTY.toString());
            return true;
        } else {
            txtlog.set("Stack is not empty!");
            log(LogMessages.IS_NOT_EMPTY.toString());
            return false;
        }
    }

    public void print() {
        txtprint.set(stk.print());
        log(LogMessages.PRINTED.toString());
    }
}

