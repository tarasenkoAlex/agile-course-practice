package ru.unn.agile.MassConverter.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.MassConverter.Model.MassConverter.SystemToConvert;

public class ViewModel {

    private final StringProperty kilogram = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<SystemToConvert> systemToConvert = new SimpleObjectProperty<>();

    enum Status {
        WAITING("Waiting for data input"),
        WRONG_INPUT("Wrong input"),
        SUCCESS("Success");

        private String message;

        Status(final String message) {
            this.message = message;
        }

        public String toString() {
            return message;
        }
    }

    public ViewModel() {
        kilogram.set("");
        result.set("");
        status.set(Status.WAITING.toString());
        systemToConvert.set(SystemToConvert.GRAM);

        kilogram.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                changedValue();
            }
        });

        systemToConvert.addListener(new ChangeListener<SystemToConvert>() {
            @Override
            public void changed(final ObservableValue<? extends SystemToConvert> observable,
                                final SystemToConvert oldValue,
                                final SystemToConvert newValue) {
                changedValue();
            }
        });
    }

    private void changedValue() {
        status.set(Status.SUCCESS.toString());
        if (kilogram.get().isEmpty()) {
            status.set(Status.WAITING.toString());
        } else {
            try {
                result.set(String.valueOf(systemToConvert.get()
                        .convert(Double.parseDouble(kilogram.get()))));
            } catch (NumberFormatException exception) {
                status.set(Status.WRONG_INPUT.toString());
            }
        }
    }

    public StringProperty kilogramProperty() {
        return kilogram;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<SystemToConvert> systemToConvertProperty() {
        return systemToConvert;
    }
}
