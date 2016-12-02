package ru.unn.agile.MultisystemCalculator.viewmodel.impl;

import ru.unn.agile.MultisystemCalculator.Model.MultisystemCalculator;
import ru.unn.agile.MultisystemCalculator.viewmodel.CalculatorInterface;

/**
 * Created by Дарья on 28.11.2016.
 */
public class MultisystemCalculatorWrapper implements CalculatorInterface<Integer, String, String> {

    @Override
    public Integer add(final String s, final String s2) {
        return new Integer(MultisystemCalculator.add(s, s2));
    }

    @Override
    public Integer subtract(final String s, final String s2) {
        return new Integer(MultisystemCalculator.subtract(s, s2));
    }

    @Override
    public Integer divide(final String s, final String s2) {
        return new Integer(MultisystemCalculator.divide(s, s2));
    }

    @Override
    public Integer multiply(final String s, final String s2) {
        return new Integer(MultisystemCalculator.multiply(s, s2));
    }
}
