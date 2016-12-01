package ru.unn.agile.MultisystemCalculator.viewmodel.impl;

import ru.unn.agile.MultisystemCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Дарья on 28.11.2016.
 */
public class Subtraction implements CalculatorInterface.BinaryOperation<Integer, String, String> {
    private final MultisystemCalculatorWrapper calculator;

    public Subtraction(final MultisystemCalculatorWrapper calculator) {
        this.calculator = calculator;
    }

    @Override
    public Integer perform(final String arg1, final String arg2) {
        return calculator.subtract(arg1, arg2);
    }
}
