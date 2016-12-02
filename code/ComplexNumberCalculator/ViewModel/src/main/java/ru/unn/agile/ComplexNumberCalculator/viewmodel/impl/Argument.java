package ru.unn.agile.ComplexNumberCalculator.viewmodel.impl;

import ru.unn.agile.ComplexNumberCalculator.Model.ComplexNum;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Alexander on 30.11.2016.
 */
public class Argument implements CalculatorInterface.UnaryOperation<Double, ComplexNum> {
    private final ComplexNumberCalculatorWrapper calculator;
    private ComplexNum arg;


    public Argument(final ComplexNumberCalculatorWrapper calculator) {
        this.calculator = calculator;
    }

    @Override
    public Double perform() {
        return new Double(calculator.argument(arg));
    }

    @Override
    public void setArgument(final ComplexNum complexNum) {
        arg = complexNum;
    }
}
