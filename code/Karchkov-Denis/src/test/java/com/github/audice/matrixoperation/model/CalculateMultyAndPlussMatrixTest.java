package com.github.audice.matrixoperation.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateMultyAndPlussMatrixTest {
    private int rows = 2;
    private int columns = 3;
    private float[] testArray = new float[]{1, 2, 3, 4, 5, 6};
    private Matrix first;
    private Matrix second;
    private double delta = 0.0001;
    private Matrix matrix;

    public String arryeToString(final float[] arrayOfElements) {
        String arrayText = "";
        for (int i = 0; i < arrayOfElements.length; i++) {
            arrayText += Float.toString(arrayOfElements[i]) + ",";
        }
        return arrayText;
    }

    @Test
    public void canCreateMutrixMultyAndAddWithInitialValues() {
        matrix = new Matrix(rows, columns, testArray);
        assertNotNull(matrix);
    }

    @Test
    public void cantCreateMutrix() {
        assertNull(matrix);
    }

    @Test
    public void compareMatrixAndTestArrayInTextImage() {
        matrix = new Matrix(rows, columns, new float[]{1, 2, 3, 4, 5, 6});
        assertEquals(arryeToString(new float[]{1, 2, 3, 4, 5, 6}), matrix.toString());
    }

    @Test
    public void compareMatrixAndTestArrayInTextImageOnNotEquals() {
        matrix = new Matrix(rows, columns, new float[]{1, 2, 3, 4, 5, 7});
        assertNotEquals(arryeToString(new float[]{1, 2, 3, 4, 5, 6}), matrix.toString());
    }

    @Test
    public void arrayInTextImageOnNotEmpty() {
        matrix = new Matrix(rows, columns, new float[]{1, 2, 3, 4, 5, 7});
        assertFalse(matrix.toString().isEmpty());
    }

    @Test
    public void arrayInTextImageOnNull() {
        matrix = new Matrix(0, 0, null);
        assertNull(matrix.toString());
    }

    @Test
    public void canGetNumberOfRows() {
        matrix = new Matrix(5, columns, testArray);
        assertEquals(5, matrix.getNumR());
    }

    @Test
    public void canGetNumberOfColumns() {
        matrix = new Matrix(rows, 7, testArray);
        assertEquals(7, matrix.getNumC());
    }

    @Test
    public void getElementOfIndex() {
        matrix = new Matrix(rows, columns, testArray);
        assertEquals(testArray[3], matrix.getElementMatrix(3), delta);
    }

    @Test
    public void getIsNotElementOfIndex() {
        matrix = new Matrix(rows, columns, testArray);
        assertNotEquals(100f, matrix.getElementMatrix(3), delta);
    }

    @Test
    public void canFirstMatrixPlusSecondMatrix() {
        first = new Matrix(2, 2, new float[]{1, 1, 1, 1});
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(testM, Matrix.sum(first, second).toString());
    }

    @Test
    public void canSumTwoMatrixWithDifferentNumberOfRows() {
        first = new Matrix(3, 2, testArray);
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(null, Matrix.sum(first, second));
    }

    @Test
    public void canSumTwoMatrixWithDifferentNumberOfColumnsAndRows() {
        first = new Matrix(3, 3, new float[]{1, 1, 1, 1, 2, 2, 1, 1, 2});
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(null, Matrix.sum(first, second));
    }

    @Test
    public void canMultyTwoMatrix() {
        first = new Matrix(2, 2, new float[]{1, 3, 4, 2});
        second = new Matrix(2, 2, new float[]{1, 2, 1, 3});
        String result = "4.0,11.0,6.0,14.0,";
        assertEquals(result, Matrix.multiply(first, second).toString());
    }

    @Test
    public void canMultyTwoMatrixWithDifferentNumbersOfColumnsAndRows() {
        first = new Matrix(2, 3, new float[]{1, 3, 4, 2, 3, 4});
        second = new Matrix(2, 2, new float[]{1, 2, 1, 3});
        assertNull(Matrix.multiply(first, second));
    }
}
