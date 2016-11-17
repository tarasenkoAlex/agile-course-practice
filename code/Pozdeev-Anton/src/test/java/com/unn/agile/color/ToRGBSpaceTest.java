package com.unn.agile.color;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ToRGBSpaceTest {
    @Test
    public void convert1FromHSVToRGB() {
        double[] hsv = ToRGBSpace.converterHSVtoRGB(new double[]{355, 1.9, 100});
        assertEquals(true, Arrays.equals(new double[]{255, 250, 251}, hsv));
    }

    @Test
    public void convert2FromHSVToRGB() {
        double[] hsv = ToRGBSpace.converterHSVtoRGB(new double[]{360, 100, 100});
        assertEquals(true, Arrays.equals(new double[]{255, 0, 0}, hsv));
    }

    @Test
    public void convert3FromHSVToRGB() {
        double[] hsv = ToRGBSpace.converterHSVtoRGB(new double[]{0, 0, 0});
        assertEquals(true, Arrays.equals(new double[]{0, 0, 0}, hsv));
    }

    @Test
    public void convert1FromLABToRGB() {
        double[] hsv = ToRGBSpace.converterLABtoRGB(new double[]{42, 123, 33});
        assertEquals(true, Arrays.equals(new double[]{255, 0, 56}, hsv));
    }

    @Test
    public void convert2FromLABToRGB() {
        double[] hsv = ToRGBSpace.converterLABtoRGB(new double[]{100, 128, -128});
        assertEquals(true, Arrays.equals(new double[]{255, 137, 255}, hsv));
    }

    @Test
    public void convert3FromLABToRGB() {
        double[] hsv = ToRGBSpace.converterLABtoRGB(new double[]{100, 12, 47});
        assertEquals(true, Arrays.equals(new double[]{255, 244, 163}, hsv));
    }
}
