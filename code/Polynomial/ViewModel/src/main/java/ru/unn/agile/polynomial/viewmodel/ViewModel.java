package ru.unn.agile.polynomial.viewmodel;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polynomial.model.Polynomial;

public class ViewModel {
    private final StringProperty firstOperandString = new SimpleStringProperty();
    private final StringProperty secondOperandString = new SimpleStringProperty();
    private final StringProperty resultString = new SimpleStringProperty();
    private final StringProperty statusString = new SimpleStringProperty();
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<Operation>> operations =
        new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final ValueChangeListener firstOperandListener = new ValueChangeListener();
    private final ValueChangeListener secondOperandListener = new ValueChangeListener();
    private final OperationChangeListener operationChangeListener = new OperationChangeListener();

    public ViewModel() {
        firstOperandString.set("");
        secondOperandString.set("");
        resultString.set("");
        statusString.set(Status.WAIT.toString());
        operation.set(Operation.ADD);

        BooleanBinding cantCalculate = new BooleanBinding() {
            {
                super.bind(firstOperandString, secondOperandString, operation);
            }
            @Override
            protected boolean computeValue() {
                return getStatus() != Status.READY;
            }
        };
        calculationDisabled.bind(cantCalculate);

        firstOperandString.addListener(firstOperandListener);
        secondOperandString.addListener(secondOperandListener);
        operation.addListener(operationChangeListener);
    }

    public StringProperty firstOperandStringProperty() {
        return firstOperandString;
    }

    public StringProperty secondOperandStringProperty() {
        return secondOperandString;
    }

    public StringProperty resultStringProperty() {
        return resultString;
    }

    public StringProperty statusStringProperty() {
        return statusString;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public void setFirstOperandString(final String firstOperandString) {
        this.firstOperandString.set(firstOperandString);
    }

    public String getFirstOperandString() {
        return firstOperandString.get();
    }

    public void setSecondOperandString(final String newSecondOperandString) {
        secondOperandString.set(newSecondOperandString);
    }

    public String getSecondOperandString() {
        return secondOperandString.get();
    }

    public String getResultString() {
        return resultString.get();
    }

    public void setOperation(final Operation newOperation) {
        operation.set(newOperation);
    }

    public Operation getOperation() {
        return operation.get();
    }

    public boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    private Object getSecondOperand() {
        if (operation.get().equals(Operation.EXPONENTIATE)) {
            return Integer.parseUnsignedInt(secondOperandString.get());
        } else {
            return Polynomial.fromString(secondOperandString.get());
        }
    }

    private Status getStatus() {
        Status status;
        if ("".equals(firstOperandString.get()) || "".equals(secondOperandString.get())) {
            status = Status.WAIT;
        } else {
            status = Status.READY;
        }
        try {
            if (!"".equals(firstOperandString.get())) {
                Polynomial.fromString(firstOperandString.get());
            }
            if (!"".equals(secondOperandString.get())) {
                getSecondOperand();
            }
        } catch (Throwable e) {
            status = Status.BAD;
        }
        return status;
    }

    public String getStatusString() {
        return statusString.get();
    }

    public void calculate() {
        Polynomial firstOperand = Polynomial.fromString(firstOperandString.get());
        resultString.set(operation.get().apply(firstOperand, getSecondOperand()).toString());
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusString.set(getStatus().toString());
        }
    }

    private class OperationChangeListener implements ChangeListener<Operation> {
        @Override
        public void changed(final ObservableValue<? extends Operation> observable,
                            final Operation oldValue, final Operation newValue) {
            statusString.set(getStatus().toString());
        }
    }

    public enum Operation {
        ADD("Add") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.add((Polynomial) op2);
            }
        },
        SUBTRACT("Subtract") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.subtract((Polynomial) op2);
            }
        },
        MULTIPLY("Multiply") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.multiply((Polynomial) op2);
            }
        },
        DIVIDE("Divide") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.divide((Polynomial) op2);
            }
        },
        EXPONENTIATE("Exponentiate") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.exponentiate((int) op2);
            }
        };

        private final String name;

        public abstract Polynomial apply(Polynomial op1, Object op2);

        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

enum Status {
    WAIT("Please input operands"),
    BAD("Incorrect input"),
    READY("Can calculate");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
