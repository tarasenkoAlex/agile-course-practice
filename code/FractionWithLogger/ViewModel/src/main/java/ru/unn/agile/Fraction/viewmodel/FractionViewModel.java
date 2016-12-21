package ru.unn.agile.Fraction.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.Fraction.model.Fraction;
import ru.unn.agile.Fraction.model.Operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FractionViewModel {
    private final StringProperty frac1 = new SimpleStringProperty();
    private final StringProperty frac2 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private List<ValueCachingChangeListener> valueChangedListeners;

    private ILogger logger;

    public FractionViewModel() {
        init();
    }

    public FractionViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    private void init() {
        frac1.set("");
        frac2.set("");
        operation.set(Operation.ADD);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(frac1, frac2);
            }

            @Override
            protected boolean computeValue() {
                if (getInputStatus() == Status.READY) {
                    return true;
                }
                return false;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(frac1);
                add(frac2);
            }
        };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty field : fields) {
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() throws IOException, ArithmeticException {
        if (calculationDisabled.get()) {
            return;
        }

        Fraction f1 = Fraction.valueOf(frac1.get());
        Fraction f2 = Fraction.valueOf(frac2.get());
        try {
            result.set(operation.get().apply(f1, f2).toString());
            status.set(Status.SUCCESS.toString());
        } catch (ArithmeticException ae) {
            if (ae.getMessage().equals("/ by zero")) {
                status.set(Status.DIV_ZERO.toString());
            } else {
                throw ae;
            }
        }

        StringBuilder message = new StringBuilder(Messages.CALCULATE_WAS_PRESSED);
        message.append("Arguments")
                .append(": Frac1 = ").append(frac1.get())
                .append("; Frac2 = ").append(frac2.get())
                .append(" Operation: ").append(operation.get().toString()).append(".");
        logger.toLog(message.toString());
        updateLogs();
    }

    public void onOperationChanged(final Operation oldValue,
                                   final Operation newValue) throws IOException {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(Messages.OPERATION_WAS_CHANGED);
        message.append(newValue.toString());
        logger.toLog(message.toString());
        updateLogs();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) throws IOException {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(Messages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(frac1.get()).append("; ")
                        .append(frac2.get()).append("]");
                logger.toLog(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    public final List<String> getLog() throws IOException {
        return logger.getLog();
    }

    public StringProperty frac1Property() {
        return frac1;
    }

    public StringProperty frac2Property() {
        return frac2;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (frac1.get().isEmpty() || frac2.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!frac1.get().isEmpty()) {
                Fraction.valueOf(frac1.get());
            }
            if (!frac2.get().isEmpty()) {
                Fraction.valueOf(frac2.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        } catch (ArithmeticException ae) {
            if (ae.getMessage().equals("/ by zero")) {
                inputStatus = Status.DIV_ZERO;
            } else {
                throw ae;
            }
        }

        return inputStatus;
    }

    private void updateLogs() throws IOException {
        List<String> fullLog = logger.getLog();

        String record = new String();

        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    private class ValueCachingChangeListener implements ChangeListener<String> {
        private String prevValue = new String();
        private String curValue = new String();
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getInputStatus().toString());
            curValue = newValue;
        }
        public boolean isChanged() {
            return !prevValue.equals(curValue);
        }
        public void cache() {
            prevValue = curValue;
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success"),
    DIV_ZERO("Cannot divide by zero");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

final class Messages {
        public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private Messages() {

        }
}
