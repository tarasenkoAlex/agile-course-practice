package ru.unn.agile.Triangle.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty ax = new SimpleStringProperty();
    private final StringProperty ay = new SimpleStringProperty();
    private final StringProperty bx = new SimpleStringProperty();
    private final StringProperty by = new SimpleStringProperty();
    private final StringProperty cx = new SimpleStringProperty();
    private final StringProperty cy = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        ax.set("");
        ay.set("");
        bx.set("");
        by.set("");
        cx.set("");
        cy.set("");

        calculationDisabled.setValue(true);
    }

    public void calculate() {

    }

    public StringProperty axProperty() {
        return ax;
    }
    public StringProperty ayProperty() { return ay; }
    public StringProperty bxProperty() { return bx; }
    public StringProperty byProperty() {
        return by;
    }
    public StringProperty cxProperty() {
        return cx;
    }
    public StringProperty cyProperty() {
        return cy;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public Boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }
}