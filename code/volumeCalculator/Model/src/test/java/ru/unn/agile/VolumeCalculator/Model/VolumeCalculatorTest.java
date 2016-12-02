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
        double result = calculator.getSphereVolume(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeSphereWhenRadius2() {
        double result = calculator.getSphereVolume(2);

        assertEquals(33.510321638291, result, DELTA);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void calculateVolumeSphereWhenRadiusNegative() {
        calculator.getSphereVolume(-2);
    }

    @Test
    public void calculateVolumeCubeWhenEdge0() {
        double result = calculator.getCubeVolume(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCubeWhenEdge3() {
        double result = calculator.getCubeVolume(3);

        assertEquals(27, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCubeWhenEdgeNegative() {
        calculator.getCubeVolume(-3);
    }

    @Test
    public void calculateVolumeConeWhenRadius0AndHeight2() {
        double result = calculator.getConeVolume(0, 2);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius2AndHeight0() {
        double result = calculator.getConeVolume(2, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius0AndHeight0() {
        double result = calculator.getConeVolume(0, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeConeWhenRadius2AndHeight2() {
        double result = calculator.getConeVolume(2, 2);

        assertEquals(8.3775804095728, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadiusNegativeAndHeight2() {
        calculator.getConeVolume(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadius2AndHeightNegative() {
        calculator.getConeVolume(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeConeWhenRadiusNegativeAndHeightNegative() {
        calculator.getConeVolume(-5, -2);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius0AndHeight5() {
        double result = calculator.getCylinderVolume(0, 5);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius5AndHeight0() {
        double result = calculator.getCylinderVolume(5, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius0AndHeight0() {
        double result = calculator.getCylinderVolume(0, 0);
        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeCylinderWhenRadius2AndHeight5() {
        double result = calculator.getCylinderVolume(2, 5);

        assertEquals(62.831853071796, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadiusNegativeAndHeight2() {
        calculator.getCylinderVolume(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadius2AndHeightNegative() {
        calculator.getCylinderVolume(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeCylinderWhenRadiusNegativeAndHeightNegative() {
        calculator.getCylinderVolume(-5, -2);
    }

    @Test
    public void calculateVolumePyramidWhenArea0AndHeight4() {
        double result = calculator.getPyramidVolume(0, 4);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumePyramidWhenArea5AndHeight0() {
        double result = calculator.getPyramidVolume(5, 0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumePyramidWhenArea2AndHeight8() {
        double result = calculator.getPyramidVolume(2, 8);
        assertEquals(5.3333333333333, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenAreaNegativeAndHeight2() {
        calculator.getPyramidVolume(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenArea2AndHeightNegative() {
        calculator.getPyramidVolume(2, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumePyramidWhenAreaNegativeAndHeightNegative() {
        calculator.getPyramidVolume(-5, -2);
    }

    @Test
    public void calculateVolumeTetrahedronWhenEdge0() {
        double result = calculator.getTetrahedronVolume(0);

        assertEquals(0, result, DELTA);
    }

    @Test
    public void calculateVolumeTetrahedronWhenEdge4() {
        double result = calculator.getTetrahedronVolume(4);

        assertEquals(7.5424723326565, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeTetrahedronWhenEdgeNegative() {
        calculator.getTetrahedronVolume(-4);
    }
}
