package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static ru.unn.agile.TemperatureConverter.Model.TemperatureScale.*;

@RunWith(Parameterized.class)
public class TemperatureConverterExceptionTest {
    private static final double DELTA = 0.000001;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {-273.15 - DELTA, CELSIUS, KELVIN},
                {0.0 - DELTA, KELVIN, CELSIUS},
                {90.14 - DELTA, NEWTON, KELVIN},
                {459.67 - DELTA, FAHRENHEIT, NEWTON},
        });
    }

    private final double source;
    private final TemperatureScale sourceScale;

    public TemperatureConverterExceptionTest(final double source,
                                    final TemperatureScale sourceScale,
                                    final TemperatureScale destinationScale) {
        this.source = source;
        this.sourceScale = sourceScale;
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryConvertingTemperatureBelowAbsoluteZero() {
        TemperatureConverter.convert(source, sourceScale, KELVIN);
    }
}
