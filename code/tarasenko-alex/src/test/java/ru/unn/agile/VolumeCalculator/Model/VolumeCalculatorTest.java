package ru.unn.agile.VolumeCalculator.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by a.tarasenko on 09.11.2016.
 */
public class VolumeCalculatorTest {

    private static final double DELTA = 0.0000001;

    private VolumeCalculator calculator;

    @Before
    public void setUp() {
        calculator = new VolumeCalculator();
    }

    @Test
    public void calculateVolumeSphereWhenRadius0() {
        double result = calculator.getSphereValue(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeSphereWhenRadius2() {
        double result = calculator.getSphereValue(2);

        assertEquals(33.510321638291, result, DELTA);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void calculateVolumeSphereWhenRadiusNegative() {
        calculator.getSphereValue(-2);
    }

    @Test
    public void calculateVolumeCubeWhenEdge0() {
        double result = calculator.getCubeValue(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCubeWhenEdge3() {
        double result = calculator.getCubeValue(3);

        assertEquals(27, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCubeWhenEdgeNegative() {
        calculator.getCubeValue(-3);
    }

    @Test
    public void calculateVolumeConeWhenRadius0AndHeight2() {
        double result = calculator.getConeValue(0, 2);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius2AndHeight0() {
        double result = calculator.getConeValue(2, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius0AndHeight0() {
        double result = calculator.getConeValue(0, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius2AndHeight2() {
        double result = calculator.getConeValue(2, 2);

        assertEquals(8.3775804095728, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadiusNegativeAndHeight2() {
        calculator.getConeValue(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadius2AndHeightNegative() {
        calculator.getConeValue(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadiusNegativeAndHeightNegative() {
        calculator.getConeValue(-5, -2);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius0AndHeight5() {
        double result = calculator.getCylinderValue(0, 5);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius5AndHeight0() {
        double result = calculator.getCylinderValue(5, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius0AndHeight0() {
        double result = calculator.getCylinderValue(0, 0);
        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius2AndHeight5() {
        double result = calculator.getCylinderValue(2, 5);

        assertEquals(62.831853071796, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadiusNegativeAndHeight2() {
        calculator.getCylinderValue(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadius2AndHeightNegative() {
        calculator.getCylinderValue(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadiusNegativeAndHeightNegative() {
        calculator.getCylinderValue(-5, -2);
    }

    @Test
    public void calculateVolumePyramidWhenArea0AndHeight4() {
        double result = calculator.getPyramidValue(0, 4);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumePyramidWhenArea5AndHeight0() {
        double result = calculator.getPyramidValue(5, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumePyramidWhenArea2AndHeight8() {
        double result = calculator.getPyramidValue(2, 8);
        assertEquals(5.3333333333333, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenAreaNegativeAndHeight2() {
        calculator.getPyramidValue(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenArea2AndHeightNegative() {
        calculator.getPyramidValue(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenAreaNegativeAndHeightNegative() {
        calculator.getPyramidValue(-5, -2);
    }

    @Test
    public void calculateVolumeTetrahedronWhenEdge0() {
        double result = calculator.getTetrahedronValue(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeTetrahedronWhenEdge4() {
        double result = calculator.getTetrahedronValue(4);

        assertEquals(7.5424723326565, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeTetrahedronWhenEdgeNegative() {
        calculator.getTetrahedronValue(-4);
    }
}
