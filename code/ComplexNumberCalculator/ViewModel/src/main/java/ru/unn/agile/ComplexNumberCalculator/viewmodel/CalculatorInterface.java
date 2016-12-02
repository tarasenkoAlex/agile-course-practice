package ru.unn.agile.ComplexNumberCalculator.viewmodel;

/**
 * Created by Alexander on 28.11.2016.
 */
public interface CalculatorInterface<Result, Arg1, Arg2> {
    Result add(Arg1 arg1, Arg2 arg2);

    Result subtract(Arg1 arg1, Arg2 arg2);

    Result divide(Arg1 arg1, Arg2 arg2);

    Result multiply(Arg1 arg1, Arg2 arg2);

    interface Operation<Result> {
        Result perform();
    }

    interface BinaryOperation<Result, Arg1, Arg2> extends Operation {
        BinaryOperation setFirstArgument(Arg1 arg1);

        BinaryOperation setSecondArgument(Arg2 arg2);
    }

    interface UnaryOperation<Result, Arg1> extends Operation {

        void setArgument(Arg1 arg1);
    }
}
