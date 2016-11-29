package ru.unn.agile.polynomial.viewmodel;

import ru.unn.agile.polynomial.model.Polynomial;

public class ViewModel {
    private String secondOperandLabelString;
    private String firstOperandString;
    private String secondOperandString;
    private String resultString;
    private Operation operation;

    public ViewModel() {
        secondOperandLabelString = "";
        firstOperandString = "";
        secondOperandString = "";
        resultString = "";
        operation = Operation.ADD;
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

    public void setOperation(final Operation newOperation) {
        operation = newOperation;
    }

    public Operation getOperation() {
        return operation;
    }

    public boolean isCalculationDisabled() {
        return getStatusString() != Status.READY.toString();
    }

    private Object getSecondOperand() {
        if (operation.equals(Operation.EXPONENTIATE)) {
            return Integer.parseUnsignedInt(secondOperandString);
        } else {
            return Polynomial.fromString(secondOperandString);
        }
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
                getSecondOperand();
            }
        } catch (Throwable e) {
            status = Status.BAD;
        }
        return status.toString();
    }

    public void calculate() {
        Polynomial firstOperand = Polynomial.fromString(firstOperandString);
        resultString = operation.apply(firstOperand, getSecondOperand()).toString();
    }

    public enum Operation {
        ADD("Add") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.add((Polynomial) op2);
            }
        },
        SUBTRACT("Subtract") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.subtract((Polynomial) op2);
            }
        },
        MULTIPLY("Multiply") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.multiply((Polynomial) op2);
            }
        },
        DIVIDE("Divide") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.divide((Polynomial) op2);
            }
        },
        EXPONENTIATE("Exponentiate") {
            public Polynomial apply(final Polynomial op1, final Object op2) {
                return op1.exponentiate((int) op2);
            }
        };

        private final String name;

        public abstract Polynomial apply(Polynomial op1, Object op2);

        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

enum Status {
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
