package com.unn.agile.color;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FromRGBSpaceTest {
    @Test
    public void convert1FromRGBToHSV() {
        double[] hsv = FromRGBSpace.convertToHSV(new double[]{1, 255, 150});
        assertEquals(true, Arrays.equals(new double[]{155, 99.608, 100}, hsv));
    }

    @Test
    public void convert2FromRGBToHSV() {
        double[] hsv = FromRGBSpace.convertToHSV(new double[]{0, 255, 0});
        assertEquals(true, Arrays.equals(new double[]{120, 100, 100}, hsv));
    }

    @Test
    public void convert3FromRGBToHSV() {
        double[] hsv = FromRGBSpace.convertToHSV(new double[]{200, 1, 26});
        assertEquals(true, Arrays.equals(new double[]{352, 99.5, 78.431}, hsv));
    }
    @Test
    public void convert4FromRGBToHSV() {
        double[] hsv = FromRGBSpace.convertToHSV(new double[]{0, 0, 0});
        assertEquals(true, Arrays.equals(new double[]{0, 0, 0}, hsv));
    }

    @Test
    public void convert5FromRGBToHSV() {
        double[] hsv = FromRGBSpace.convertToHSV(new double[]{255, 255, 255});
        assertEquals(true, Arrays.equals(new double[]{0, 0, 100}, hsv));
    }

    @Test
    public void convert1FromRGBToLAB() {
        double[] hsv = FromRGBSpace.convertToLAB(new double[]{100, 100, 100});
        assertEquals(true, Arrays.equals(new double[]{42.375, 0.001, -0.005}, hsv));
    }

    @Test
    public void convert2FromRGBToLAB() {
        double[] hsv = FromRGBSpace.convertToLAB(new double[]{255, 0, 200});
        assertEquals(true, Arrays.equals(new double[]{57.492, 91.283, -34.317}, hsv));
    }
    @Test
    public void convert3FromRGBToLAB() {
        double[] hsv = FromRGBSpace.convertToLAB(new double[]{0, 0, 0});
        assertEquals(true, Arrays.equals(new double[]{0, 0, 0}, hsv));
    }
    @Test
    public void convert4FromRGBToLAB() {
        double[] hsv = FromRGBSpace.convertToLAB(new double[]{255, 255, 255});
        assertEquals(true, Arrays.equals(new double[]{100, 0.005, -0.01}, hsv));
    }
}
