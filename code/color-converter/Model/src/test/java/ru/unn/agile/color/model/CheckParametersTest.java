package ru.unn.agile.color.model;

import org.junit.Test;

import static ru.unn.agile.color.model.CheckParameters.*;
import static ru.unn.agile.color.model.ColorSpaces.*;
import static org.junit.Assert.assertTrue;

public class CheckParametersTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkNumberParametersWithoutParams() {
        checkNumberParameters(new double[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNumberParameters4Params() {
        checkNumberParameters(new double[]{1, 2, 3, 4});
    }

    @Test()
    public void testCheckNumberParameters() {
        assertTrue(checkNumberParameters(new double[]{1, 2, 3}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectNumberParametersRGB() {
        checkParameters(RGB, new double[]{-1, 100, 100, 100});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersRGB1() {
        checkParameters(RGB, new double[]{-1, 100, 100});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersRGB2() {
        checkParameters(RGB, new double[]{100, 100, 256});
    }

    @Test(expected = NumberFormatException.class)
    public void checkIncorrectParametersRGB3() {
        checkParameters(RGB, new double[]{255, 255, 244.1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectNumberParametersLAB() {
        checkParameters(LAB, new double[]{-1, 100, 100, 200});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersLAB1() {
        checkParameters(LAB, new double[]{-1, 100, 100});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersLAB2() {
        checkParameters(LAB, new double[]{99, 100, -256});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersLAB3() {
        checkParameters(LAB, new double[]{0, -128, 129});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersLAB4() {
        checkParameters(LAB, new double[]{0, -129, 128});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectNumberParametersHSV() {
        checkParameters(HSV, new double[]{-1, 100, 100, 100});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersHSV1() {
        checkParameters(HSV, new double[]{-1, 100, 100});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersHSV2() {
        checkParameters(HSV, new double[]{100, 100, 361});
    }

    @Test(expected = NumberFormatException.class)
    public void checkIncorrectParametersHSV3() {
        checkParameters(HSV, new double[]{1.2, 255, 1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectParametersHSV4() {
        checkParameters(HSV, new double[]{10, 255, 1});
    }
}