package ru.unn.agile.color.viewmodel;


import com.unn.agile.color.ColorSpaces;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ViewModel {
    private final ObjectProperty<ColorSpaces> fromColorSpace = new SimpleObjectProperty<ColorSpaces>();
    private final ObjectProperty<ColorSpaces> toColorSpace = new SimpleObjectProperty<ColorSpaces>();
    private final ObjectProperty<ObservableList<ColorSpaces>> colorSpaces =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(ColorSpaces.values()));

    public ViewModel() {
        fromColorSpace.set(ColorSpaces.RGB);
        toColorSpace.set(ColorSpaces.HSV);
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

    public void convert() {

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
