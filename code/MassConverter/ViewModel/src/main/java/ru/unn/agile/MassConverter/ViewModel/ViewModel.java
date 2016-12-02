package ru.unn.agile.MassConverter.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.MassConverter.Model.MassConverter.ConversionSystem;

public class ViewModel {

    private final StringProperty input = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<ConversionSystem> systemToConvert = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<ConversionSystem>> conversionSystems
            = new SimpleObjectProperty<>(FXCollections
            .observableArrayList(ConversionSystem.values()));
    private final ObjectProperty<ConversionSystem> systemFromConvert = new SimpleObjectProperty<>();

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
        input.set("");
        result.set("");
        status.set(Status.WAITING.toString());
        systemToConvert.set(ConversionSystem.GRAM);
        systemFromConvert.set(ConversionSystem.KILOGRAM);

        input.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                changedValue();
            }
        });

        systemToConvert.addListener(new ChangeListener<ConversionSystem>() {
            @Override
            public void changed(final ObservableValue<? extends ConversionSystem> observable,
                                final ConversionSystem oldValue,
                                final ConversionSystem newValue) {
                changedValue();
            }
        });

        systemFromConvert.addListener(new ChangeListener<ConversionSystem>() {
            @Override
            public void changed(final ObservableValue<? extends ConversionSystem> observable,
                                final ConversionSystem oldValue,
                                final ConversionSystem newValue) {
                changedValue();
            }
        });
    }

    private void changedValue() {
        status.set(Status.SUCCESS.toString());
        if (input.get().isEmpty()) {
            status.set(Status.WAITING.toString());
            result.set("");
        } else {
            try {
                result.set(String.valueOf(systemToConvert.get()
                        .convertTo(systemFromConvert.get()
                                .convertFrom(Double.parseDouble(input.get())))));
            } catch (NumberFormatException exception) {
                status.set(Status.WRONG_INPUT.toString());
                result.set("");
            } catch (IllegalArgumentException esception) {
                status.set(Status.WRONG_INPUT.toString());
                result.set("");
            }
        }
    }

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<ConversionSystem> systemToConvertProperty() {
        return systemToConvert;
    }

    public ObjectProperty<ConversionSystem> systemFromConvertProperty() {
        return systemFromConvert;
    }

    public final String getResult() {
        return result.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public final ObservableList<ConversionSystem> getConversionSystems() {
        return conversionSystems.get();
    }
}
