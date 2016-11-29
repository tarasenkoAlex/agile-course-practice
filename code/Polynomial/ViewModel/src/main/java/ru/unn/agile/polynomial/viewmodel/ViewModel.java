package ru.unn.agile.polynomial.viewmodel;

import java.util.Locale;
import ru.unn.agile.polynomial.model.Polynomial;

public class ViewModel {
    private String secondOperandLabelString;
    private String firstOperandString;
    private String secondOperandString;
    private String resultString;
    private String operationString;

    public ViewModel() {
        secondOperandLabelString = "";
        firstOperandString = "";
        secondOperandString = "";
        resultString = "";
        operationString = Operation.ADD.toString();
    }

    public String getSecondOperandLabelString() {
        return secondOperandLabelString;
    }

    public void setFirstOperandString(final String firstOperandString) {
        this.firstOperandString = firstOperandString;
    }

    public String getFirstOperandString() {
        return firstOperandString;
    }

    public void setSecondOperandString(final String newSecondOperandString) {
        secondOperandString = newSecondOperandString;
    }

    public String getSecondOperandString() {
        return secondOperandString;
    }

    public String getResultString() {
        return resultString;
    }

    public void setOperationString(final String newOperationString) {
        operationString = newOperationString;
    }

    public String getOperationString() {
        return operationString;
    }

    public boolean isCalculationDisabled() {
        try {
            Polynomial.fromString(firstOperandString);
            if (operationString.equals(Operation.EXPONENTIATE.toString())) {
                Integer.parseUnsignedInt(secondOperandString);
            } else {
                Polynomial.fromString(secondOperandString);
            }
        } catch (Throwable e) {
           return true;
        }
        return false;
    }

    public String getStatusString() {
        Status status;
        if ("".equals(firstOperandString) || "".equals(secondOperandString)) {
            status = Status.WAIT;
        } else {
            status = Status.READY;
        }
        try {
            if (!"".equals(firstOperandString)) {
                Polynomial.fromString(firstOperandString);
            }
            if (!"".equals(secondOperandString)) {
                if (operationString.equals(Operation.EXPONENTIATE.toString())) {
                    Integer.parseInt(secondOperandString);
                } else {
                    Polynomial.fromString(secondOperandString);
                }
            }
        } catch (Throwable e) {
            status = Status.BAD;
        }
        return status.toString();
    }

    public void calculate() {
        Polynomial result;
        Polynomial firstOperand = Polynomial.fromString(firstOperandString);
        if (operationString.equals(Operation.EXPONENTIATE.toString())) {
            int secondOperand = Integer.parseUnsignedInt(secondOperandString);
            result = firstOperand.exponentiate(secondOperand);
        } else {
            Polynomial secondOperand = Polynomial.fromString(secondOperandString);
            switch (Operation.fromString(operationString)) {
                case ADD:
                    result = firstOperand.add(secondOperand);
                    break;
                case SUBTRACT:
                    result = firstOperand.subtract(secondOperand);
                    break;
                case MULTIPLY:
                    result = firstOperand.multiply(secondOperand);
                    break;
                case DIVIDE:
                    result = firstOperand.divide(secondOperand);
                    break;
                default:
                    return;
            }
        }
        resultString = result.toString();
    }

    public enum Operation {
        ADD("Add"),
        SUBTRACT("Subtract"),
        MULTIPLY("Multiply"),
        DIVIDE("Divide"),
        EXPONENTIATE("Exponentiate");

        private final String name;

        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Operation fromString(final String string) {
            return valueOf(string.toUpperCase(Locale.getDefault()));
        }
    }

    public enum Status {
        WAIT("Please input operands"),
        BAD("Incorrect input"),
        READY("Can calculate");

        private final String name;

        Status(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
