package ru.unn.agile.MultisystemCalculator.viewmodel;

/**
 * Created by Дарья on 28.11.2016.
 */
public interface CalculatorInterface<Result, Arg1, Arg2> {
    Result add(Arg1 arg1, Arg2 arg2);

    Result subtract(Arg1 arg1, Arg2 arg2);

    Result divide(Arg1 arg1, Arg2 arg2);

    Result multiply(Arg1 arg1, Arg2 arg2);

    interface BinaryOperation<Result, Arg1, Arg2> {
        Result perform(Arg1 arg1, Arg2 arg2);
    }
}
