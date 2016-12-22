package ru.unn.agile.MyDeque.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.MyDeque.Model.Deque;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ru.unn.agile.MyDeque.viewmodel.ViewModel.Status.*;
import static ru.unn.agile.MyDeque.viewmodel.ViewModel.Operations.*;
import static ru.unn.agile.MyDeque.viewmodel.KeyboardsKeys.*;

public class ViewModel {
    private String value;
    private String result;
    private String status;
    private final StringProperty logs = new SimpleStringProperty();
    private Operations operation;
    private boolean isAcceptButtonEnabled;
    private final Deque deque = new Deque();
    private ILogger logger;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can not be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        value = "";
        status = WAITING;
        result = "";
        operation = PUSH_HEAD;
        isAcceptButtonEnabled = false;
    }

    public String getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }

    public void setValue(final String value) {
        if (value.equals(this.value)) {
            return;
        }
        valueChanged(this.value, value);
        this.value = value;
    }

    public void valueChanged(final String oldValue, final String newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        logger.log(LogMess.EDITING_FINISHED + "oldValue = " + oldValue + " newValue = " + newValue);
    }

    public void textFieldKey(final int codeKey) {
        parseInput();

        if (codeKey == ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {
        if (isAcceptButtonEnabled()) {
            accept();
        }
    }

    public void accept() {
        if (!parseInput()) {
            return;
        }
        StringBuilder mess = new StringBuilder(LogMess.ACTION_WAS_STARTED);
        mess.append(operation);
        switch (operation) {
            case PUSH_HEAD:
                deque.pushHeadElement(parseInt(value));
                result = deque.toString();
                mess.append("; Value = " + value + "; Result = " + result);
                break;
            case PUSH_TAIL:
                deque.pushTailElement(parseInt(value));
                result = deque.toString();
                mess.append("; Value = " + value + "; Result = " + result);
                break;
            case POP_HEAD:
                result = Integer.toString(deque.popHeadElement());
                mess.append("; Result = " + result);
                break;
            case POP_TAIL:
                result = Integer.toString(deque.popTailElement());
                mess.append("; Result = " + result);
                break;
            default:
                mess.append("; Value = " + value + "\n" + "IllegalArgumentException");
                throw new IllegalArgumentException(
                        "Only can only use push & pop operations for head & tail elements");
        }
        logger.log(mess.toString());
        updateLogs();
        status = SUCCESS;
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public String getResult() {
        return result;
    }

    public boolean isAcceptButtonEnabled() {
        return isAcceptButtonEnabled;
    }

    private boolean parseInput() {
        try {
            if (!value.isEmpty()) {
                parseInt(value);
            }
        } catch (Exception e) {
            status = BAD_FORMAT;
            isAcceptButtonEnabled = false;
            return false;
        }

        isAcceptButtonEnabled = isInputAvailable();
        if (isAcceptButtonEnabled) {
            status = READY;
        } else {
            status = WAITING;
        }

        return isAcceptButtonEnabled;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(final Operations operation) {
        this.operation = operation;
    }

    private boolean isInputAvailable() {
        return !value.isEmpty();
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    public void onOperationChanged(final Operations oldValue,
                                   final Operations newValue) throws IOException {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(LogMess.OPERATION_WAS_CHANGED);
        message.append(newValue.toString());
        logger.log(message.toString());
        updateLogs();
    }

    public enum Operations {
        PUSH_HEAD("Push head"),
        POP_HEAD("Pop head"),
        PUSH_TAIL("Push tail"),
        POP_TAIL("Pop tail");
        private final String operateName;

        Operations(final String operateName) {
            this.operateName = operateName;
        }

        public String toString() {
            return operateName;
        }
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() {
        }
    }

    final class LogMess {
        public static final String ACTION_WAS_STARTED = "Action = ";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMess() { }
    }
}
