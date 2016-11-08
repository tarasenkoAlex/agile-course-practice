package ru.unn.agile.SquareConverter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareConverterTest {

    private ISquareConverter converter;

    @Before
    public void setUp() {
        converter = new SquareConverter();
    }

    @Test
    public void isFromSqrMeterNotNull() {
        FromSqrMeter result = converter.convertFromSqrMeter();

        assertNotNull(result);
    }

}
