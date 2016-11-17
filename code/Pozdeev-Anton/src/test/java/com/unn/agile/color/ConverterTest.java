package com.unn.agile.color;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @Test
    public void convert1FromHSVToLAB() {
        double[] hsv = Converter.hsvToLAB(new double[]{0, 0, 0});
        assertEquals(true, Arrays.equals(new double[]{0, 0, 0}, hsv));
    }

    @Test
    public void convert2FromHSVToLAB() {
        double[] hsv = Converter.hsvToLAB(new double[]{360, 100, 100});
        assertEquals(true, Arrays.equals(new double[]{53.233, 80.109, 67.22}, hsv));
    }

    @Test
    public void convert3FromHSVToLAB() {
        double[] hsv = Converter.hsvToLAB(new double[]{157, 14.8, 0.5});
        assertEquals(true, Arrays.equals(new double[]{0.271, 0.02, -0.005}, hsv));
    }

    @Test
    public void convert1FromLABToHSV() {
        double[] hsv = Converter.labToHSV(new double[]{0, -128, -128});
        assertEquals(true, Arrays.equals(new double[]{220, 100, 76.078}, hsv));
    }

    @Test
    public void convert2FromLABToHSV() {
        double[] hsv = Converter.labToHSV(new double[]{100, 128, 128});
        assertEquals(true, Arrays.equals(new double[]{15, 100, 100}, hsv));
    }

    @Test
    public void convert3FromLABToHSV() {
        double[] hsv = Converter.labToHSV(new double[]{17, 0, -22});
        assertEquals(true, Arrays.equals(new double[]{206, 90.411, 28.627}, hsv));
    }
}
