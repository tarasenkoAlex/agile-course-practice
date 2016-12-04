package ru.unn.agile.NewtonRoots.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewtonRootAppViewModel  {
    private final StringProperty leftPoint;
    private final StringProperty rightPoint;
    private final StringProperty maxIterations;
    private final StringProperty accuracy;
    private final StringProperty function;
    private final StringProperty solverReport;
    private final BooleanProperty findRootButtonDisable;

    public NewtonRootAppViewModel()  {
        leftPoint = new SimpleStringProperty("");
        rightPoint = new SimpleStringProperty("");
        maxIterations = new SimpleStringProperty("");
        accuracy = new SimpleStringProperty("");
        function = new SimpleStringProperty("");
        findRootButtonDisable = new SimpleBooleanProperty(true);
        solverReport = new SimpleStringProperty("");
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
}