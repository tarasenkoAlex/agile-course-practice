package ru.unn.agile.ComplexNumberCalculator.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ComplexNumberCalculator.Model.ComplexNum;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.impl.*;

import java.util.HashMap;

/**
 * Created by Alexander on 23.11.2016.
 */
public class ComplexNumberCalculatorViewModel {
    private final HashMap<Operations,
            CalculatorInterface.BinaryOperation<ComplexNum, ComplexNum, ComplexNum>>
            binaryOperationsMap;
    private final HashMap<Operations,
            CalculatorInterface.UnaryOperation<? extends Object, ComplexNum>> unaryOperationsMap;
    private final StringProperty firstArgReal = new SimpleStringProperty();
    private final StringProperty firstArgIm = new SimpleStringProperty();
    private final StringProperty secondArgReal = new SimpleStringProperty();
    private final StringProperty secondArgIm = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operations>> supportedOperations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operations.values()));
    private final ObjectProperty<Operations> selectedOperation = new SimpleObjectProperty<>();

    private final ComplexNumberCalculatorWrapper calculator = new ComplexNumberCalculatorWrapper();

    // FXML needs default c-tor for binding
    public ComplexNumberCalculatorViewModel() {
        firstArgReal.set("");
        firstArgIm.set("");
        secondArgReal.set("");
        secondArgIm.set("");
        result.set("");
        selectedOperation.set(Operations.ADDITION);
        binaryOperationsMap = new HashMap<>();
        binaryOperationsMap.put(Operations.ADDITION, new Addition(calculator));
        binaryOperationsMap.put(Operations.SUBTRACTION, new Subtraction(calculator));
        binaryOperationsMap.put(Operations.DIVISION, new Division(calculator));
        binaryOperationsMap.put(Operations.MULTIPLICATION, new Multiplication(calculator));
        unaryOperationsMap = new HashMap<>();
        unaryOperationsMap.put(Operations.ABS, new Abs(calculator));
        unaryOperationsMap.put(Operations.ARGUMENT, new Argument(calculator));

    }


    public void calculate() {
        result.set(performOperation());
    }

    private String performOperation() {
        ComplexNum firstArg;
        try {
            firstArg = new ComplexNum(Float.parseFloat(firstArgReal.getValue()),
                    Float.parseFloat(firstArgIm.getValue()));
        } catch (Exception e) {
            return "Incorrect input for the first argument";
        }
        if (binaryOperationsMap.get(selectedOperation.getValue()) == null) {
            unaryOperationsMap.get(selectedOperation.getValue()).setArgument(firstArg);
            return unaryOperationsMap.get(selectedOperation.getValue()).perform().toString();
        } else {
            try {
                ComplexNum secondArg = new ComplexNum(Float.parseFloat(secondArgReal.getValue()),
                        Float.parseFloat(secondArgIm.getValue()));
                binaryOperationsMap.get(selectedOperation.getValue()).setFirstArgument(firstArg);
                binaryOperationsMap.get(selectedOperation.getValue()).setSecondArgument(secondArg);
                return binaryOperationsMap.get(selectedOperation.getValue()).perform().toString();
            } catch (Exception e) {
                return "Incorrect input for the second argument";
            }
        }
    }

    public StringProperty firstArgRealProperty() {
        return firstArgReal;
    }

    public StringProperty firstArgImProperty() {
        return firstArgIm;
    }

    public StringProperty secondArgRealProperty() {
        return secondArgReal;
    }

    public StringProperty secondArgImProperty() {
        return secondArgIm;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public ObjectProperty<Operations> selectedOperationProperty() {
        return selectedOperation;
    }

    public ObjectProperty<ObservableList<Operations>> supportedOperationsProperty() {
        return supportedOperations;
    }

    public final ObservableList<Operations> getSupportedOperations() {
        return supportedOperations.get();
    }

}
