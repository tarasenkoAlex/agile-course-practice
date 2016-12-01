package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.text.DecimalFormat;
import ru.unn.agile.SolvingQuadraticEquation.model.SolvingQuadraticEquation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private static final int INFINITE_NUMBER_OF_SOLUTIONS = 3;

    private final StringProperty aProperty = new SimpleStringProperty();
    private final StringProperty bProperty = new SimpleStringProperty();
    private final StringProperty cProperty = new SimpleStringProperty();
    private final BooleanProperty solvingDisabled = new SimpleBooleanProperty();
    private final StringProperty resultProperty = new SimpleStringProperty();
    private final StringProperty statusProperty = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        aProperty.set("");
        bProperty.set("");
        cProperty.set("");
        resultProperty.set("");
        statusProperty.set(Status.WAITING.toString());
        BooleanBinding couldSolve = new BooleanBinding() {
            {
                super.bind(aProperty, bProperty, cProperty);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        solvingDisabled.bind(couldSolve.not());
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(aProperty);
            add(bProperty);
            add(cProperty);
        }};

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public Property<String> aCoefProperty() {
        return aProperty;
    }
    public Property<String> bCoefProperty() {
        return bProperty;
    }
    public Property<String> cCoefProperty() {
        return cProperty;
    }
    public BooleanProperty solvingDisabledProperty() {
        return solvingDisabled;
    }
    public final boolean getSolvingDisabled() {
        return solvingDisabled.get();
    }

    public StringProperty resultProperty() {
        return resultProperty;
    }
    public final String getResult() {
        return resultProperty.get();
    }
    public StringProperty statusProperty() {
        return statusProperty;
    }
    public final String getStatus() {
        return statusProperty.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (aProperty.get().isEmpty() || bProperty.get().isEmpty()
                || cProperty.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!aProperty.get().isEmpty()) {
                Double.parseDouble(aProperty.get());
            }
            if (!bProperty.get().isEmpty()) {
                Double.parseDouble(bProperty.get());
            }
            if (!cProperty.get().isEmpty()) {
                Double.parseDouble(cProperty.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    public void solve() {
        if (solvingDisabled.get()) {
            return;
        }
        double[] roots = SolvingQuadraticEquation
                .calc(aProperty.get(), bProperty.get(), cProperty.get());
        resultProperty.set(buildResultString(roots));
        statusProperty.set(Status.SUCCESS.toString());
    }

    private String buildResultString(final double[] roots) {
        switch (roots.length) {
            case 1:
                return "X = " + new DecimalFormat("#0.000").format(roots[0]);
            case 2:
                return "X(1) = " + new DecimalFormat("#0.000").format(roots[0]) + "; "
                        + "X(2) = " + new DecimalFormat("#0.000").format(roots[1]);
            case INFINITE_NUMBER_OF_SOLUTIONS:
                return "Infinite Set of Solutions";
            default:
                return "No Solutions";
        }
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusProperty.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please enter coefficients of Quadratic Equation"),
    READY("Press 'Solve' for solving Quadratic Equation"),
    BAD_FORMAT("Entered coefficients are incorrect!"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
