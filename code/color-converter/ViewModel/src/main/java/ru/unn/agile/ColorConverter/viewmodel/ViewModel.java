package ru.unn.agile.ColorConverter.viewmodel;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.ColorConverter.model.ColorSpaces;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ColorConverter.model.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.unn.agile.ColorConverter.model.CheckParameters.*;


public class ViewModel {

    private final StringProperty firstValue = new SimpleStringProperty();
    private final StringProperty secondValue = new SimpleStringProperty();
    private final StringProperty thirdValue = new SimpleStringProperty();
    private final StringProperty firstValueResult = new SimpleStringProperty();
    private final StringProperty secondValueResult = new SimpleStringProperty();
    private final StringProperty thirdValueResult = new SimpleStringProperty();
    private final StringProperty statusMessage = new SimpleStringProperty();
    private final BooleanProperty convertingDisabled = new SimpleBooleanProperty();
    private final ObjectProperty<ColorSpaces> fromColorSpace = new SimpleObjectProperty<>();
    private final ObjectProperty<ColorSpaces> toColorSpace = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<ColorSpaces>> colorSpaces =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(ColorSpaces.values()));
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();


    private final StringProperty logs = new SimpleStringProperty();
    private IColorConverterLogger colorConverterLogger;

    public ViewModel() {
        init();
    }


    public ViewModel(final IColorConverterLogger logger) {
        setLogger(logger);
        init();
    }

    public final void setLogger(final IColorConverterLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        colorConverterLogger = logger;
    }

    private void init() {
        firstValue.set("");
        secondValue.set("");
        thirdValue.set("");
        firstValueResult.set("");
        secondValueResult.set("");
        thirdValueResult.set("");
        fromColorSpace.set(ColorSpaces.LAB);
        toColorSpace.set(ColorSpaces.HSV);
        statusMessage.set(Status.WAITING.toString());
        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(firstValue, secondValue, thirdValue, fromColorSpace);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        convertingDisabled.bind(couldConvert.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(firstValue);
                add(secondValue);
                add(thirdValue);
            }
        };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

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

    public Property<String> getFirstValueProperty() {
        return firstValue;
    }

    public Property<String> getSecondValueProperty() {
        return secondValue;
    }

    public Property<String> getThirdValueProperty() {
        return thirdValue;
    }

    public boolean isConvertingDisabled() {
        return convertingDisabled.get();
    }

    public BooleanProperty convertingDisabledProperty() {
        return convertingDisabled;
    }


    public String getFirstValueResult() {
        return firstValueResult.get();
    }

    public StringProperty firstValueResultProperty() {
        return firstValueResult;
    }


    public String getSecondValueResult() {
        return secondValueResult.get();
    }

    public StringProperty secondValueResultProperty() {
        return secondValueResult;
    }

    public String getThirdValueResult() {
        return thirdValueResult.get();
    }

    public StringProperty thirdValueResultProperty() {
        return thirdValueResult;
    }

    public String getStatusMessage() {
        return statusMessage.get();
    }

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }

    public String getFirstValue() {
        return firstValue.get();
    }

    public StringProperty firstValueProperty() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue.get();
    }

    public StringProperty secondValueProperty() {
        return secondValue;
    }

    public String getThirdValue() {
        return thirdValue.get();
    }

    public StringProperty thirdValueProperty() {
        return thirdValue;
    }


    public void convert() throws IOException {
        if (convertingDisabled.get()) {
            return;
        }
        double[] params = {Double.parseDouble(firstValue.get()),
                Double.parseDouble(secondValue.get()),
                Double.parseDouble(thirdValue.get())};
        double[] roots =
                Converter.convert(fromColorSpace.get(), toColorSpace.get(), params);

        firstValueResult.set(String.valueOf(roots[0]));
        secondValueResult.set(String.valueOf(roots[1]));
        thirdValueResult.set(String.valueOf(roots[2]));
        statusMessage.set(Status.SUCCESS.toString());
        StringBuilder logMessage = new StringBuilder(LogMessages.CONVERT_WAS_PRESSED);
        logMessage.append("Input values are: ")
                .append(printVector(firstValue, secondValue, thirdValue))
                .append(" Output: ")
                .append(printVector(firstValueResult, secondValueResult, thirdValueResult));
        colorConverterLogger.log(logMessage.toString());
        updateLogs();

    }

    private String printVector(final StringProperty v1, final StringProperty v2,
                               final StringProperty v3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[")
                .append(v1.get()).append("; ")
                .append(v2.get()).append("; ")
                .append(v3.get()).append("]");
        return stringBuilder.toString();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) throws IOException {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueChangeListener valueCachingChangeListener : valueChangedListeners) {
            if (valueCachingChangeListener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input values are: [")
                        .append(firstValue.get()).append("; ")
                        .append(secondValue.get()).append("; ")
                        .append(thirdValue.get()).append("]")
                        .append(" Status: ").append(getInputStatus());
                colorConverterLogger.log(message.toString());
                updateLogs();

                valueCachingChangeListener.cache();
                break;
            }
        }
    }

    public void onColorSpaceChanged(final ColorSpaces oldValue, final ColorSpaces newValue,
                                    final Boolean source)
            throws IOException {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder((source ? "Source" : "Destination")
                + LogMessages.COLOR_SPACE_WAS_CHANGED);
        message.append("from ").append(oldValue.toString()).append(" to ")
                .append(newValue.toString());
        colorConverterLogger.log(message.toString());
        updateLogs();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        private String previousValueParam = new String();
        private String currentValueParam = new String();

        @Override
        public void changed(final ObservableValue<? extends String> observableValue,
                            final String oldValueParam, final String newValueParam) {
            statusMessage.set(getInputStatus().toString());
            if (oldValueParam.equals(newValueParam)) {
                return;
            }
            currentValueParam = newValueParam;
        }

        public boolean isChanged() {
            return !previousValueParam.equals(currentValueParam);
        }

        public void cache() {
            previousValueParam = currentValueParam;
        }
    }

    public String getLogs() {
        return logs.get();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final List<String> getLog() throws IOException {
        return colorConverterLogger.getLog();
    }

    private void updateLogs() throws IOException {
        List<String> fullLog = colorConverterLogger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    enum Status {
        WAITING("Please enter coefficients of color space"),
        READY("Press 'Convert' for converting"),
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

    final class LogMessages {
        public static final String CONVERT_WAS_PRESSED = "Convert button was pressed. ";
        public static final String COLOR_SPACE_WAS_CHANGED = " color space was changed ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMessages() {

        }
    }
}


