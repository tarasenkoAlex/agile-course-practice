package ru.unn.agile.NewtonRoots.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

import ru.unn.agile.NewtonRoots.Model.NewtonMethod;
import ru.unn.agile.NewtonRoots.Model.MathFunction;

public class NewtonRootAppViewModel  {
    private final StringProperty leftPoint;
    private final StringProperty rightPoint;
    private final StringProperty maxIterations;
    private final StringProperty accuracy;
    private final StringProperty function;
    private final StringProperty solverReport;
    private final BooleanProperty findRootButtonDisable;
    private final StringProperty inputStatus;

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public NewtonRootAppViewModel()  {
        leftPoint = new SimpleStringProperty("");
        rightPoint = new SimpleStringProperty("");
        maxIterations = new SimpleStringProperty("");
        accuracy = new SimpleStringProperty("");
        function = new SimpleStringProperty("");
        findRootButtonDisable = new SimpleBooleanProperty(true);
        solverReport = new SimpleStringProperty("");
        inputStatus = new SimpleStringProperty(InputStatus.WAITING.toString());

        BooleanBinding couldFindRoot = new BooleanBinding() {
            {
                super.bind(leftPoint, rightPoint, maxIterations, accuracy, function);
            }
            @Override
            protected boolean computeValue() {
                return checkInput() == InputStatus.READY;
            }
        };
        findRootButtonDisable.bind(couldFindRoot.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(leftPoint);
            add(rightPoint);
            add(maxIterations);
            add(accuracy);
            add(function);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty leftPointProperty()  {
        return leftPoint;
    }
    public String getLeftPoint() {
        return leftPoint.get();
    }
    public void setLeftPoint(final String value) {
        leftPoint.set(value);
    }

    public StringProperty rightPointProperty()  {
        return rightPoint;
    }
    public String getRightPoint() {
        return rightPoint.get();
    }
    public void setRightPoint(final String value) {
        rightPoint.set(value);
    }

    public StringProperty maxIterationsProperty()  {
        return maxIterations;
    }
    public String getMaxIterations() {
        return maxIterations.get();
    }
    public void setMaxIterations(final String value) {
        maxIterations.set(value);
    }

    public StringProperty accuracyProperty()  {
        return accuracy;
    }
    public String getAccuracy() {
        return accuracy.get();
    }
    public void setAccuracy(final String value) {
        accuracy.set(value);
    }

    public StringProperty functionProperty()  {
        return function;
    }
    public String getFunction() {
        return function.get();
    }
    public void setFunction(final String value) {
        function.set(value);
    }

    public BooleanProperty findRootButtonDisableProperty()  {
        return findRootButtonDisable;
    }
    public boolean getFindRootButtonDisable() {
        return findRootButtonDisable.get();
    }
    public void setFindRootButtonDisable(final boolean value) {
        findRootButtonDisable.set(value);
    }

    public StringProperty solverReportProperty()  {
        return solverReport;
    }
    public String getSolverReport() {
        return solverReport.get();
    }
    public void setSolverReport(final String value) {
        solverReport.set(value);
    }

    public StringProperty inputStatusProperty()  {
        return inputStatus;
    }
    public String getInputStatus() {
        return inputStatus.get();
    }
    public void setInputStatus(final String value) {
        inputStatus.set(value);
    }

    InputStatus checkInput()  {
        if (leftPoint.get().isEmpty() || rightPoint.get().isEmpty()
                || accuracy.get().isEmpty() || maxIterations.get().isEmpty()
                || function.get().isEmpty()) {
            return InputStatus.WAITING;
        }

        MathFunction testFunction = null;
        try {
            testFunction = new MathFunction(function.get());
        } catch (Exception e)  {
            return InputStatus.BAD_FORMAT;
        }

        try {
            Double.parseDouble(leftPoint.get());
            Double.parseDouble(rightPoint.get());
            Double.parseDouble(accuracy.get());
            Integer.parseUnsignedInt(maxIterations.get());
        } catch (NumberFormatException nfe) {
            return InputStatus.BAD_FORMAT;
        }

        if (!NewtonMethod.isMonotonicFunctionOnInterval(testFunction,
                Double.parseDouble(leftPoint.get()),
                Double.parseDouble(rightPoint.get())))  {
            return InputStatus.NON_MONOTONIC_FUNCTION;
        }

        if (Double.parseDouble(leftPoint.get()) >= Double.parseDouble(rightPoint.get())
                || Double.parseDouble(accuracy.get()) <= 0) {
            return InputStatus.BAD_PARAMETERS;
        }

        return InputStatus.READY;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            inputStatus.set(checkInput().toString());
        }
    }

    public void findRoot() {
        if (findRootButtonDisable.get()) {
            return;
        }

        try {
            NewtonMethod method = new NewtonMethod(Double.parseDouble(accuracy.get()),
                    Double.parseDouble(accuracy.get()) / 2);
            MathFunction functionObj = new MathFunction(function.get());
            double left = Double.parseDouble(leftPoint.get());
            double right = Double.parseDouble(rightPoint.get());
            double root = method.findRoot(functionObj, (left + right) / 2, left, right);
            if (Double.isNaN(root))  {
                throw new Exception();
            } else  {
                setSolverReport("Root: " + Double.toString(root));
                inputStatus.set(InputStatus.SUCCESS.toString());
            }
        } catch (Exception e)  {
            inputStatus.set(InputStatus.FAILED.toString());
        }
    }
}

enum InputStatus {
    WAITING("Please provide input data"),
    READY("Press 'Find root'"),
    NON_MONOTONIC_FUNCTION("The function is not monotonic"),
    BAD_FORMAT("Bad format"),
    BAD_PARAMETERS("Wrong method parameters"),
    SUCCESS("Root found"),
    FAILED("Root not found");

    private final String name;
    InputStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

