package ru.unn.agile.Triangle.viewmodel;

import javafx.beans.binding.BooleanBinding;
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

    private final StringProperty area = new SimpleStringProperty();
    private final StringProperty perimeter = new SimpleStringProperty();
    private final StringProperty circumcircleRadius = new SimpleStringProperty();
    private final StringProperty circumcircleCenterX = new SimpleStringProperty();
    private final StringProperty circumcircleCenterY = new SimpleStringProperty();
    private final StringProperty incircleRadius = new SimpleStringProperty();
    private final StringProperty incircleCenterX = new SimpleStringProperty();
    private final StringProperty incircleCenterY = new SimpleStringProperty();

    private Status status;

    public ViewModel() {
        ax.set("");
        ay.set("");
        bx.set("");
        by.set("");
        cx.set("");
        cy.set("");

        calculationDisabled.setValue(true);
        status = Status.WAITING;
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(ax, ay, bx, by, cx, cy);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        area.set("");
        perimeter.set("");
        circumcircleRadius.set("");
        circumcircleCenterX.set("");
        circumcircleCenterY.set("");
        incircleRadius.set("");
        incircleCenterX.set("");
        incircleCenterY.set("");
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

    public BooleanProperty calculationDisabledProperty() { return calculationDisabled; }

    public Boolean getCalculationDisabled() { return calculationDisabled.get(); }

    public StringProperty areaProperty() {
        return area;
    }

    public String getArea() {
        return area.get();
    }

    public StringProperty perimeterProperty() { return perimeter; }

    public String getPerimeter() {
        return perimeter.get();
    }

    public StringProperty circumcircleRadiusProperty() { return circumcircleRadius; }

    public String getCircumcircleRadius() {
        return circumcircleRadius.get();
    }

    public StringProperty circumcircleCenterXProperty() {
        return circumcircleCenterX;
    }

    public String getCircumcircleCenterX() {
        return circumcircleCenterX.get();
    }

    public StringProperty circumcircleCenterYProperty() {
        return circumcircleCenterY;
    }

    public String getCircumcircleCenterY() {
        return circumcircleCenterY.get();
    }

    public StringProperty incircleRadiusProperty() { return incircleRadius; }

    public String getIncircleRadius() { return incircleRadius.get(); }

    public StringProperty incircleCenterXProperty() {
        return incircleCenterX;
    }

    public String getIncircleCenterX() {
        return incircleCenterX.get();
    }

    public StringProperty incircleCenterYProperty() {
        return incircleCenterY;
    }

    public String getIncircleCenterY() { return incircleCenterY.get(); }


    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (ax.get().isEmpty() || ay.get().isEmpty()
            || bx.get().isEmpty() || by.get().isEmpty()
            || cx.get().isEmpty() || cy.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }

        return inputStatus;
    }
}

enum Status {
    WAITING, READY
}