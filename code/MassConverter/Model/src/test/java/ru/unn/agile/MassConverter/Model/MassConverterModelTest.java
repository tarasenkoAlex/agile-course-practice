package ru.unn.agile.MassConverter.Model;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.MassConverter.Model.MassConverter.*;

public class MassConverterModelTest {
    private static final double DELTA = 0.001;

    @Test
    public void convert0kgTo0Gram() {
        assertEquals(0, convertToSystem(Multipliers.TO_GRAM_MULTIPLIER, 0), DELTA);
    }

    @Test
    public void convert1kgTo1000Gram() {
        assertEquals(1000, convertToSystem(Multipliers.TO_GRAM_MULTIPLIER, 1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToGram() {
        convertToSystem(Multipliers.TO_GRAM_MULTIPLIER, -1);
    }

    @Test
    public void convert0kgTo0Tonne() {
        assertEquals(0, convertToSystem(Multipliers.TO_TONNE_MULTIPLIER, 0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToTonne() {
        convertToSystem(Multipliers.TO_TONNE_MULTIPLIER, -1);
    }

    @Test
    public void convert1kgToTonne() {
        assertEquals(0.001, convertToSystem(Multipliers.TO_TONNE_MULTIPLIER, 1), DELTA);
    }

    @Test
    public void checkNamesOfSystems() {
        assertEquals("Gram", SystemToConvert.GRAM.toString());
        assertEquals("Tonne", SystemToConvert.TONNE.toString());
        assertEquals("Pound", SystemToConvert.POUND.toString());
        assertEquals("Centner", SystemToConvert.CENTNER.toString());
        assertEquals("Milligram", SystemToConvert.MILLIGRAM.toString());
        assertEquals("Microgram", SystemToConvert.MICROGRAM.toString());
    }

    @Test
    public void convert1kgToGramThroughSystemToConvert() {
        assertEquals(1000, SystemToConvert.GRAM.convert(1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToGramThroughSystemToConvert() {
        SystemToConvert.GRAM.convert(-1);
    }

    @Test
    public void convert1kgToPoundThroughSystemToConvert() {
        assertEquals(2.679, SystemToConvert.POUND.convert(1), DELTA);
    }

    @Test
    public void convert1kgToCentnerThroughSystemToConvert() {
        assertEquals(0.01, SystemToConvert.CENTNER.convert(1), DELTA);
    }

    @Test
    public void convert1kgToMilligramThroughSystemToConvert() {
        assertEquals(1000000, SystemToConvert.MILLIGRAM.convert(1), DELTA);
    }

    @Test
    public void convert1kgToMicrogramThroughSystemToConvert() {
        assertEquals(1000000000, SystemToConvert.MICROGRAM.convert(1), DELTA);
    }
}
