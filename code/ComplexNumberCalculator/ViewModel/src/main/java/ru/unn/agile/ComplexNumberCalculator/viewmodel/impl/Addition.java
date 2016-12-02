package ru.unn.agile.ComplexNumberCalculator.viewmodel.impl;

import ru.unn.agile.ComplexNumberCalculator.Model.ComplexNum;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Alexander on 28.11.2016.
 */
public class Addition implements CalculatorInterface.BinaryOperation<ComplexNum, ComplexNum,
        ComplexNum> {
    private final ComplexNumberCalculatorWrapper calculator;
    private ComplexNum firstArg;
    private ComplexNum secondArg;

    public Addition(final ComplexNumberCalculatorWrapper calculator) {
        this.calculator = calculator;
    }

    @Override
    public ComplexNum perform() {
        return calculator.add(firstArg, secondArg);
    }

    @Override
    public Addition setFirstArgument(final ComplexNum complexNum) {
        firstArg = complexNum;
        return this;
    }

    @Override
    public Addition setSecondArgument(final ComplexNum complexNum) {
        secondArg = complexNum;
        return this;
    }
}
