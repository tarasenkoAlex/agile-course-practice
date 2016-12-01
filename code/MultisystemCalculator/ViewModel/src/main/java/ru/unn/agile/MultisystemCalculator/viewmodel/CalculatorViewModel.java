package ru.unn.agile.MultisystemCalculator.viewmodel;

import com.google.common.collect.ImmutableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.MultisystemCalculator.Model.Format;
import ru.unn.agile.MultisystemCalculator.Model.NumberConverter;
import ru.unn.agile.MultisystemCalculator.Model.NumeralSystemsData;
import ru.unn.agile.MultisystemCalculator.viewmodel.impl.*;

import java.util.HashMap;

/**
 * Created by Дарья on 23.11.2016.
 */
public class CalculatorViewModel {
    private final HashMap<Operation, CalculatorInterface.BinaryOperation<Integer, String,
            String>> operationsMap;
    private final HashMap<Format, Integer> formatBaseMapping;
    private final HashMap<Format, ImmutableList<Character>> formatCharsMapping;
    private final StringProperty arg1 = new SimpleStringProperty();
    private final StringProperty arg2 = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> supportedOperations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> selectedOperation = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Format>> supportedFormats =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Format.values()));
    private final ObjectProperty<Format> selectedFormat = new SimpleObjectProperty<>();

    private final MultisystemCalculatorWrapper calculator = new MultisystemCalculatorWrapper();

    // FXML needs default c-tor for binding
    public CalculatorViewModel() {
        arg1.set("");
        arg2.set("");
        result.set("");
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
}
