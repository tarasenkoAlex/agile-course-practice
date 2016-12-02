package ru.unn.agile.ComplexNumberCalculator.viewmodel.impl;

import ru.unn.agile.ComplexNumberCalculator.Model.ComplexNum;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Alexander on 28.11.2016.
 */
public class Multiplication implements CalculatorInterface.BinaryOperation<ComplexNum, ComplexNum,
        ComplexNum> {
    private final ComplexNumberCalculatorWrapper calculator;
    private ComplexNum firstArg;
    private ComplexNum secondArg;

    public Multiplication(final ComplexNumberCalculatorWrapper calculator) {
        this.calculator = calculator;
    }

    @Override
    public ComplexNum perform() {
        return calculator.multiply(firstArg, secondArg);
    }

    @Override
    public Multiplication setFirstArgument(final ComplexNum complexNum) {
        firstArg = complexNum;
        return this;
    }

    @Override
    public Multiplication setSecondArgument(final ComplexNum complexNum) {
        secondArg = complexNum;
        return this;
    }
}
