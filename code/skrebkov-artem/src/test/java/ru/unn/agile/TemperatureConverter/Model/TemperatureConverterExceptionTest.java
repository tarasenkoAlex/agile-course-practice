package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static ru.unn.agile.TemperatureConverter.Model.TemperatureScale.*;

@RunWith(Parameterized.class)
public class TemperatureConverterExceptionTest {
    private static final double DELTA = 0.000001;

    @Parameterized.Parameters(name = "{index}: from {0} {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {AbsoluteZero.CELSIUS - DELTA, CELSIUS},
                {AbsoluteZero.KELVIN - DELTA, KELVIN},
                {AbsoluteZero.NEWTON - DELTA, NEWTON},
                {AbsoluteZero.FAHRENHEIT - DELTA, FAHRENHEIT},
        });
    }

    private final double source;
    private final TemperatureScale sourceScale;

    public TemperatureConverterExceptionTest(final double source,
                                    final TemperatureScale sourceScale) {
        this.source = source;
        this.sourceScale = sourceScale;
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryConvertingTemperatureLowerThanAbsoluteZero() {
        TemperatureConverter.convert(source, sourceScale, KELVIN);
    }
}
