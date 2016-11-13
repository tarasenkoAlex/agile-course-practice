package ru.unn.agile.MatrixOperationMultyAndAdd.Model;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateMultyAndPlussMatrixTest {
    private Matrix first;
    private Matrix second;
    private final float[] testArray = new float[]{1, 1, 1, 1, 2, 2};
    @Test
    public void canFirstMatrixPlusSecondMatrix() {
        first = new Matrix(2, 2, new float[]{1, 1, 1, 1});
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(testM, CalcOperationMatrix.sumMatrix(first, second).toString());
    }
    @Test
    public void canSumTwoMatrixWithDifferentNumberOfRows() {
        first = new Matrix(3, 2, testArray);
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(null, CalcOperationMatrix.sumMatrix(first, second));
    }
    @Test
    public void canSumTwoMatrixWithDifferentNumberOfColumnsAndRows() {
        first = new Matrix(3, 3, new float[]{1, 1, 1, 1, 2, 2, 1, 1, 2});
        second = new Matrix(2, 2, new float[]{2, 2, 2, 2});
        String testM = "3.0,3.0,3.0,3.0,";
        assertEquals(null, CalcOperationMatrix.sumMatrix(first, second));
    }
    @Test
    public void canGetSizeArrayOfMatrix() {
        first = new Matrix(3, 2, testArray);
        second = new Matrix(2, 3, testArray);
        assertEquals(9, CalcOperationMatrix.getDimensinsForMulty(first, second));
    }
    @Test
    public void canMulty() {
        first = new Matrix(3, 2, testArray);
        second = new Matrix(2, 3, testArray);
        assertEquals(true, CalcOperationMatrix.checkDimensinsForMulty(first, second));
    }
    @Test
    public void canMultyTwoMatrix() {
        first = new Matrix(2, 2, new float[]{1, 3, 4, 2});
        second = new Matrix(2, 2, new float[]{1, 2, 1, 3});
        String result = "4.0,11.0,6.0,14.0,";
        assertEquals(result, CalcOperationMatrix.multiplyMatrix(first, second).toString());
    }



}
