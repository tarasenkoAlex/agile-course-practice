package ru.unn.agile.MultisystemCalculator.viewmodel;

import com.google.common.collect.ImmutableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.MultisystemCalculator.Model.Format;
import ru.unn.agile.MultisystemCalculator.Model.NumberConverter;
import ru.unn.agile.MultisystemCalculator.Model.NumeralSystemsData;
import ru.unn.agile.MultisystemCalculator.viewmodel.impl.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculatorViewModel {
    private HashMap<Operation, CalculatorInterface.BinaryOperation<Integer, String,
            String>> operationsMap;
    private HashMap<Format, Integer> formatBaseMapping;
    private HashMap<Format, ImmutableList<Character>> formatCharsMapping;
    private final StringProperty arg1 = new SimpleStringProperty();
    private final StringProperty arg2 = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> supportedOperations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> selectedOperation = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Format>> supportedFormats =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Format.values()));
    private final ObjectProperty<Format> selectedFormat = new SimpleObjectProperty<>();
    private final StringProperty logs = new SimpleStringProperty();
    private ILogger multySystemLogger;
    private List<ValueCachingChangeListener> valueChangedListeners;

    private final MultisystemCalculatorWrapper calculator = new MultisystemCalculatorWrapper();

    // FXML needs default c-tor for binding
    public CalculatorViewModel() {
        init();
    }

    public CalculatorViewModel(final ILogger logger) {
        setMultySystemLogger(logger);
        init();
    }

    public final void setMultySystemLogger(final ILogger multySystemLogger) {
        if (multySystemLogger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.multySystemLogger = multySystemLogger;
    }

    private void init() {
        arg1.set("");
        arg2.set("");
        result.set("Please provide input data");
        selectedOperation.set(Operation.ADDITION);
        operationsMap = new HashMap<>();
        operationsMap.put(Operation.ADDITION, new Addition(calculator));
        operationsMap.put(Operation.SUBTRACTION, new Subtraction(calculator));
        operationsMap.put(Operation.DIVISION, new Division(calculator));
        operationsMap.put(Operation.MULTIPLICATION, new Multiplication(calculator));

        selectedFormat.set(Format.BIN);
        formatBaseMapping = new HashMap<>();
        formatBaseMapping.put(Format.BIN, new Integer("2"));
        formatBaseMapping.put(Format.OCT, new Integer("8"));
        formatBaseMapping.put(Format.HEX, new Integer("16"));

        formatCharsMapping = new HashMap<>();
        formatCharsMapping.put(Format.BIN, NumeralSystemsData.BINARY_CHARACTERS);
        formatCharsMapping.put(Format.OCT, NumeralSystemsData.OCTAL_CHARACTERS);
        formatCharsMapping.put(Format.HEX, NumeralSystemsData.HEXADECIMAL_CHARACTERS);
        final List<StringProperty> vals = new ArrayList<StringProperty>() { {
            add(arg1);
            add(arg2);
        } };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty val : vals) {
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            val.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }


    public void calculate() {
        String output;
        try {
            Integer operationResult = (Integer) operationsMap.get(selectedOperation.getValue())
                    .perform(arg1.getValue(), arg2.getValue());
            output = NumberConverter.decimalToSystem(operationResult.intValue(),
                    formatBaseMapping.get(selectedFormat.getValue()),
                    formatCharsMapping.get(selectedFormat.getValue()));
            output = NumeralSystemsData.FORMAT_PREFIXES_MAPPING.get(selectedFormat.getValue())
                    + output;

            StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
            message.append("Arguments")
                    .append(": first argument = ").append(arg1.get())
                    .append("; second argument = ").append(arg2.get())
                    .append(" Operation: ").append(selectedOperation.get().toString()).append(".");
            multySystemLogger.log(message.toString());
            updateLogs();
        } catch (Exception e) {
            output = e.getMessage();
        }
        result.set(output);
    }

    public StringProperty firstArgProperty() {
        return arg1;
    }

    public StringProperty secondArgProperty() {
        return arg2;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public ObjectProperty<Operation> selectedOperationProperty() {
        return selectedOperation;
    }

    public ObjectProperty<ObservableList<Operation>> supportedOperationsProperty() {
        return supportedOperations;
    }

    public final ObservableList<Operation> getSupportedOperations() {
        return supportedOperations.get();
    }

    public ObjectProperty<Format> selectedFormatProperty() {
        return selectedFormat;
    }

    public ObjectProperty<ObservableList<Format>> supportedFormatsProperty() {
        return supportedFormats;
    }

    public final ObservableList<Format> getSupportedFormats() {
        return supportedFormats.get();
    }

    public String getLogs() {
        return logs.get();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final List<String> getLog() throws IOException {
        return multySystemLogger.getLog();
    }

    private void updateLogs() throws IOException {
        List<String> fullLog = multySystemLogger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    public void onOperationChanged(final Operation oldValue,
                                   final Operation newValue) throws IOException {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(LogMessages.OPERATION_WAS_CHANGED);
        message.append(newValue.toString());
        multySystemLogger.log(message.toString());
        updateLogs();
    }


    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) throws IOException {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener ValueCachingChangeListener : valueChangedListeners) {
            if (ValueCachingChangeListener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(arg1.get()).append("; ")
                        .append(arg2.get()).append("]");
                multySystemLogger.log(message.toString());
                updateLogs();

                ValueCachingChangeListener.cache();
                break;
            }
        }
    }

    private class ValueCachingChangeListener implements ChangeListener<String> {
        private String previousValueParam = new String();
        private String currentValueParam = new String();
        @Override
        public void changed(final ObservableValue<? extends String> observableValue,
                            final String oldValueParam, final String newValueParam) {
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

    final class LogMessages {
        public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMessages() {

        }

    }
}
