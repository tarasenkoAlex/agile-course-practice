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
    private final StringProperty derivativeStep;
    private final StringProperty accuracy;
    private final StringProperty function;
    private final StringProperty solverReport;
    private final BooleanProperty findRootButtonDisable;
    private final StringProperty inputStatus;
    private final StringProperty startPoint;

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public NewtonRootAppViewModel()  {
        leftPoint = new SimpleStringProperty("");
        rightPoint = new SimpleStringProperty("");
        derivativeStep = new SimpleStringProperty("");
        accuracy = new SimpleStringProperty("");
        function = new SimpleStringProperty("");
        findRootButtonDisable = new SimpleBooleanProperty(true);
        solverReport = new SimpleStringProperty("");
        startPoint = new SimpleStringProperty("");
        inputStatus = new SimpleStringProperty(InputStatus.WAITING.toString());

        BooleanBinding couldFindRoot = new BooleanBinding() {
            {
                super.bind(leftPoint, rightPoint, derivativeStep, accuracy, function);
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
            add(derivativeStep);
            add(accuracy);
            add(function);
            add(startPoint);
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

    public StringProperty derivativeStepProperty()  {
        return derivativeStep;
    }
    public String getDerivativeStep() {
        return derivativeStep.get();
    }
    public void setDerivativeStep(final String value) {
        derivativeStep.set(value);
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

    public StringProperty startPointProperty()  {
        return startPoint;
    }
    public String getStartPoint() {
        return startPoint.get();
    }
    public void setStartPoint(final String value) {
        startPoint.set(value);
    }

    private boolean checkInputFormat()  {
        try {
            Double.parseDouble(leftPoint.get());
            Double.parseDouble(rightPoint.get());
            Double.parseDouble(accuracy.get());
            Double.parseDouble(derivativeStep.get());
            Double.parseDouble(startPoint.get());
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            MathFunction testFunction = new MathFunction(function.get());
        } catch (Exception e)  {
            return false;
        }

        return true;
    }

    private boolean checkMonotonic()  {
        MathFunction testFunction = null;
        try {
             testFunction = new MathFunction(function.get());
        } catch (Exception e)  {
            return false;
        }

        if (!NewtonMethod.isMonotonicFunctionOnInterval(testFunction,
                Double.parseDouble(leftPoint.get()),
                Double.parseDouble(rightPoint.get())))  {
            return false;
        }

        return true;
    }

    private boolean checkMethodParameters()  {
        double leftPointValue = Double.parseDouble(leftPoint.get());
        double rightPointValue = Double.parseDouble(rightPoint.get());
        double startPointValue = Double.parseDouble(startPoint.get());

        if (leftPointValue >= rightPointValue || startPointValue < leftPointValue
                || startPointValue > rightPointValue) {
            return false;
        }

        double accuracyValue = Double.parseDouble(accuracy.get());
        double derivativeStepValue = Double.parseDouble(derivativeStep.get());

        if (accuracyValue <= 0 || accuracyValue > 0.1
                || derivativeStepValue <= 0 || derivativeStepValue > 0.1)  {
            return false;
        }

        return true;
    }

    private InputStatus checkInput()  {
        if (leftPoint.get().isEmpty() || rightPoint.get().isEmpty()
                || accuracy.get().isEmpty() || derivativeStep.get().isEmpty()
                || function.get().isEmpty() || startPoint.get().isEmpty()) {
            return InputStatus.WAITING;
        }

        if (!checkInputFormat()) {
            return InputStatus.BAD_FORMAT;
        }

        if (!checkMonotonic())  {
            return InputStatus.NON_MONOTONIC_FUNCTION;
        }

        if (!checkMethodParameters()) {
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
                    Double.parseDouble(derivativeStep.get()));
            MathFunction functionObj = new MathFunction(function.get());
            double left = Double.parseDouble(leftPoint.get());
            double right = Double.parseDouble(rightPoint.get());
            double root = method.findRoot(functionObj, Double.parseDouble(startPoint.get()), left, right);
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

