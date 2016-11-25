package ru.unn.agile.Fraction.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.Fraction.model.Fraction;
import ru.unn.agile.Fraction.model.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty frac1 = new SimpleStringProperty();
    private final StringProperty frac2 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
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
                return getInputStatus() == Status.READY;
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

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
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

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
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
