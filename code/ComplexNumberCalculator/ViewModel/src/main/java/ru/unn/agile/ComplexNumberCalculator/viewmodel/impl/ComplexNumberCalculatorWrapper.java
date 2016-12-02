package ru.unn.agile.ComplexNumberCalculator.viewmodel.impl;

import ru.unn.agile.ComplexNumberCalculator.Model.CalculatorComplexNumber;
import ru.unn.agile.ComplexNumberCalculator.Model.ComplexNum;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Alexander on 28.11.2016.
 */
public class ComplexNumberCalculatorWrapper implements CalculatorInterface<ComplexNum,
        ComplexNum, ComplexNum> {

    @Override
    public ComplexNum add(final ComplexNum complexNum, final ComplexNum complexNum2) {
        return CalculatorComplexNumber.add(complexNum, complexNum2);
    }

    @Override
    public ComplexNum subtract(final ComplexNum complexNum, final ComplexNum complexNum2) {
        return CalculatorComplexNumber.sub(complexNum, complexNum2);
    }

    @Override
    public ComplexNum divide(final ComplexNum complexNum, final ComplexNum complexNum2) {
        return CalculatorComplexNumber.div(complexNum, complexNum2);
    }

    @Override
    public ComplexNum multiply(final ComplexNum complexNum, final ComplexNum complexNum2) {
        return CalculatorComplexNumber.mult(complexNum, complexNum2);
    }

    public double argument(final ComplexNum complexNum) {
        return CalculatorComplexNumber.getArgument(complexNum);
    }

    public float abs(final ComplexNum complexNum) {
        return CalculatorComplexNumber.abs(complexNum);
    }
}
