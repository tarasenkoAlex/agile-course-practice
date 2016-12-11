package ru.unn.agile.color.viewmodel;


import ru.unn.agile.color.model.ColorSpaces;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.color.model.Converter;

import static ru.unn.agile.color.model.CheckParameters.*;


public class ViewModel {

    private final StringProperty firstValue = new SimpleStringProperty();
    private final StringProperty secondValue = new SimpleStringProperty();
    private final StringProperty thirdValue = new SimpleStringProperty();
    private final StringProperty firstValueResult = new SimpleStringProperty();
    private final StringProperty secondValueResult = new SimpleStringProperty();
    private final StringProperty thirdValueResult = new SimpleStringProperty();
    private final BooleanProperty convertingDisabled = new SimpleBooleanProperty();
    private final ObjectProperty<ColorSpaces> fromColorSpace = new SimpleObjectProperty<ColorSpaces>();
    private final ObjectProperty<ColorSpaces> toColorSpace = new SimpleObjectProperty<ColorSpaces>();
    private final ObjectProperty<ObservableList<ColorSpaces>> colorSpaces =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(ColorSpaces.values()));

    public ViewModel() {
        firstValue.set("");
        secondValue.set("");
        thirdValue.set("");
        fromColorSpace.set(ColorSpaces.RGB);
        toColorSpace.set(ColorSpaces.HSV);
        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(firstValue, secondValue, thirdValue);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        convertingDisabled.bind(couldConvert.not());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (firstValue.get().isEmpty() || secondValue.get().isEmpty()
                || thirdValue.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!firstValue.get().isEmpty() && !secondValue.get().isEmpty()
                    && !thirdValue.get().isEmpty()) {
                double[] params = {Double.parseDouble(firstValue.get()),
                    Double.parseDouble(secondValue.get()),
                    Double.parseDouble(thirdValue.get())};
                checkParameters(fromColorSpace.getValue(), params);
            }
        } catch (IllegalArgumentException ex) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    public ObjectProperty<ObservableList<ColorSpaces>> colorSpacesProperty() {
        return colorSpaces;
    }

    public final ObservableList<ColorSpaces> getColorSpaces() {
        return colorSpaces.get();
    }

    public ObjectProperty<ColorSpaces> getFromColorSpace() {
        return fromColorSpace;
    }

    public ObjectProperty<ColorSpaces> getToColorSpace() {
        return toColorSpace;
    }

    public BooleanProperty convertingDisabledProperty() {
        return convertingDisabled;
    }
    public final boolean getconvertingDisabled() {
        return convertingDisabled.get();
    }

    public Property<String> getFirstValueProperty() {
        return firstValue;
    }

    public Property<String> getSecondValueProperty() {
        return secondValue;
    }

    public Property<String> getThirdValueProperty() {
        return thirdValue;
    }

    public void convert() {
        if (getconvertingDisabled()) {
            return;
        }
        double[] params = {Double.parseDouble(firstValue.get()),
                Double.parseDouble(secondValue.get()),
                Double.parseDouble(thirdValue.get())};
        double[] roots =
                Converter.convert(fromColorSpace.get(), toColorSpace.get(), params);
        System.out.println(roots[0] + " | " + roots[1] + " | " + roots[2]);
        //resultProperty.set(buildResultString(roots));
        //statusProperty.set(Status.SUCCESS.toString());
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
}
