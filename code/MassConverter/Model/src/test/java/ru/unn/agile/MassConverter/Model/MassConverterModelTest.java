package ru.unn.agile.MassConverter.Model;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.MassConverter.Model.MassConverter.*;

public class MassConverterModelTest {
    private static final double DELTA = 0.001;

    @Test
    public void convert0kgTo0Gram() {
        assertEquals(0, convertToSystem(Multipliers.GRAM_MULTIPLIER, 0), DELTA);
    }

    @Test
    public void convert1kgTo1000Gram() {
        assertEquals(1000, convertToSystem(Multipliers.GRAM_MULTIPLIER, 1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToGram() {
        convertToSystem(Multipliers.GRAM_MULTIPLIER, -1);
    }

    @Test
    public void convert0kgTo0Tonne() {
        assertEquals(0, convertToSystem(Multipliers.TONNE_MULTIPLIER, 0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToTonne() {
        convertToSystem(Multipliers.TONNE_MULTIPLIER, -1);
    }

    @Test
    public void convert1kgToTonne() {
        assertEquals(0.001, convertToSystem(Multipliers.TONNE_MULTIPLIER, 1), DELTA);
    }

    @Test
    public void checkNamesOfSystems() {
        assertEquals("Gram", ConversionSystem.GRAM.toString());
        assertEquals("Tonne", ConversionSystem.TONNE.toString());
        assertEquals("Pound", ConversionSystem.POUND.toString());
        assertEquals("Centner", ConversionSystem.CENTNER.toString());
        assertEquals("Milligram", ConversionSystem.MILLIGRAM.toString());
        assertEquals("Microgram", ConversionSystem.MICROGRAM.toString());
    }

    @Test
    public void convert1kgToGramThroughSystemToConvert() {
        assertEquals(1000, ConversionSystem.GRAM.convertTo(1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToGramThroughSystemToConvert() {
        ConversionSystem.GRAM.convertTo(-1);
    }

    @Test
    public void convert1KgToTonneThroughSystemToConvert() {
        assertEquals(0.001, ConversionSystem.TONNE.convertTo(1), DELTA);
    }

    @Test
    public void convert1kgToPoundThroughSystemToConvert() {
        assertEquals(2.679, ConversionSystem.POUND.convertTo(1), DELTA);
    }

    @Test
    public void convert1kgToCentnerThroughSystemToConvert() {
        assertEquals(0.01, ConversionSystem.CENTNER.convertTo(1), DELTA);
    }

    @Test
    public void convert1kgToMilligramThroughSystemToConvert() {
        assertEquals(1000000, ConversionSystem.MILLIGRAM.convertTo(1), DELTA);
    }

    @Test
    public void convert1kgToMicrogramThroughSystemToConvert() {
        assertEquals(1000000000, ConversionSystem.MICROGRAM.convertTo(1), DELTA);
    }

    @Test
    public void convert1TonneTo1000Kg() {
        assertEquals(1000, ConversionSystem.TONNE.convertFrom(1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeToKg() {
        ConversionSystem.TONNE.convertFrom(-1);
    }

    @Test
    public void convert1000GramTo1Kg() {
        assertEquals(1, ConversionSystem.GRAM.convertFrom(1000), DELTA);
    }

    @Test
    public void convert1000000mlgrTo1kg() {
        assertEquals(1, ConversionSystem.MILLIGRAM.convertFrom(1000000), DELTA);
    }

    @Test
    public void convert1000000000mcgrTo1kg() {
        assertEquals(1, ConversionSystem.MICROGRAM.convertFrom(1000000000), DELTA);
    }

    @Test
    public void convert2679PoundTo1Kg() {
        assertEquals(1, ConversionSystem.POUND.convertFrom(2.679), DELTA);
    }

    @Test
    public void convert1CentnerTo100Kg() {
        assertEquals(100, ConversionSystem.CENTNER.convertFrom(1), DELTA);
    }
}
