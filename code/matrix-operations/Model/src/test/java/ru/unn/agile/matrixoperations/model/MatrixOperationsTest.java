package ru.unn.agile.matrixoperations.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixOperationsTest {
    private final double delta = 0.0001;

    private final int leftMatrixRows = 4;
    private final int leftMatrixCols = 3;
    private final int rightMatrixRows = 3;
    private final int rightMatrixCols = 2;
    private final int invalidSizeMatrixRows = 10;
    private final int invalidSizeMatrixCols = 20;

    private final int resultMultMatrixRows = 4;
    private final int resultMultMatrixCols = 2;

    private final float[] leftMatrixData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
    private final float[] leftAddMatrixData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
    private final float[] rightMatrixData = {4, 5, 6, 7, 8, 9};
    private final float[] resultMatrixData = {40, 46, 94, 109, 148, 172, 40, 46};

    private final float[] testData = {0.0f, 2.0f, 4.5f, 2.3f, 3.3f, 4.4f};
    private final float[] addData = {4.0f, 7.0f, 4.3f, 3.3f, 5.7f, 2.4f};

    @Test
    public void testToStringForDefaultMatrix() {
        Matrix m = new Matrix(1, 1);
        assertEquals("{0.0}", m.toString());
    }

    @Test
    public void testToStringForNonDefaultMatrix() {
        float[] data = {3.5f};
        Matrix m = new Matrix(1, 1, data);
        assertEquals("{3.5}", m.toString());
    }

    @Test
    public void testNegativeRowsCount() {
        doTestInvalidSizeMatrix(-1, 1);
    }

    @Test
    public void testZeroRowsCount() {
        doTestInvalidSizeMatrix(0, 1);
    }

    @Test
    public void testNegativeColumnsCount() {
        doTestInvalidSizeMatrix(1, -1);
    }

    @Test
    public void testZeroColumnsCount() {
        doTestInvalidSizeMatrix(1, 0);
    }

    @Test
    public void testValidRowsCount() {
        Matrix m = getLeftMatrix(true);
        assertEquals(leftMatrixRows, m.getRows());
    }

    @Test
    public void testValidColumnsCount() {
        Matrix m = getLeftMatrix(true);
        assertEquals(leftMatrixCols, m.getColumns());
    }

    @Test
    public void testMatrixSize() {
        Matrix m = getLeftMatrix(true);
        assertEquals(leftMatrixRows * leftMatrixCols, m.getSize());
    }

    @Test
    public void testRowsOnUpdateDimensions() {
        Matrix m = getLeftMatrix(true);
        m.updateDimensions(6, 6);
        assertEquals(6, m.getRows());
    }

    @Test
    public void testColsOnUpdateDimensions() {
        Matrix m = getLeftMatrix(true);
        m.updateDimensions(7, 8);
        assertEquals(8, m.getColumns());
    }

    @Test
    public void testUnfilledMatrix() {
        Matrix m = getLeftMatrix(true);
        for (int i = 0; i < m.getSize(); i++) {
            assertEquals(0.0, m.getElement(i), delta);
        }
    }

    @Test
    public void testFilledMatrix() {
        Matrix m = getLeftMatrix(false);
        for (int i = 0; i < m.getSize(); i++) {
            assertEquals(leftMatrixData[i], m.getElement(i), delta);
        }
    }

    @Test
    public void testMatrixCopy() {
        Matrix src = getLeftMatrix(false);
        Matrix copy = new Matrix(src);
        for (int i = 0; i < src.getSize(); i++) {
            assertEquals(src.getElement(i), copy.getElement(i), delta);
        }
    }

    @Test
    public void testGetElementByIndex() {
        Matrix m = getLeftMatrix(false);
        for (int r = 0; r < leftMatrixRows; r++) {
            for (int c = 0; c < leftMatrixCols; c++) {
                int idx = r * leftMatrixCols + c;
                assertEquals(leftMatrixData[idx], m.getElement(idx), delta);
            }
        }
    }

    @Test
    public void testGetElementByInvalidIndex() {
        Matrix m = getLeftMatrix(false);
        boolean catched = false;
        try {
            int idx = leftMatrixRows * leftMatrixCols + 10;
            m.getElement(idx);
        } catch (IndexOutOfBoundsException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testGetElementByInvalidRow() {
        Matrix m = getLeftMatrix(false);
        boolean catched = false;
        try {
            m.getElement(leftMatrixRows + 10, 1);
        } catch (IndexOutOfBoundsException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testGetElementByInvalidColumn() {
        Matrix m = getLeftMatrix(false);
        boolean catched = false;
        try {
            m.getElement(1, leftMatrixCols + 10);
        } catch (IndexOutOfBoundsException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testGetElementByRowColumn() {
        Matrix m = getLeftMatrix(false);
        for (int y = 0; y < leftMatrixRows; y++) {
            for (int c = 0; c < leftMatrixCols; c++) {
                int idx = y * leftMatrixCols + c;
                assertEquals(leftMatrixData[idx], m.getElement(y, c), delta);
            }
        }
    }

    @Test
    public void testGetElement() {
        Matrix m = getLeftMatrix(false);
        for (int r = 0; r < leftMatrixRows; r++) {
            for (int c = 0; c < leftMatrixCols; c++) {
                int idx = r * leftMatrixCols + c;
                assertEquals(m.getElement(idx), m.getElement(r, c), delta);
            }
        }
    }

    @Test
    public void testSetElementByIndex() {
        float testValue = 333.4f;
        Matrix m = getLeftMatrix(false);
        m.setElement(2, testValue);
        assertEquals(testValue, m.getElement(2), delta);
    }

    @Test
    public void testSetElementByInvalidIndex() {
        float testValue = 333.4f;
        Matrix m = getLeftMatrix(false);
        boolean catched = false;
        try {
            m.setElement(leftMatrixRows * leftMatrixCols + 10, testValue);
        } catch (IndexOutOfBoundsException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testSetElementByInvalidRow() {
        doTestSetByInvalidIndex(leftMatrixRows + 10, 1, 1);
    }

    @Test
    public void testSetElementByInvalidColumn() {
        doTestSetByInvalidIndex(1, leftMatrixCols + 10, 1);
    }

    @Test
    public void testSetElementByRowColumn() {
        float testValue = 333.4f;
        Matrix m = getLeftMatrix(false);
        m.setElement(2, 1, testValue);
        assertEquals(testValue, m.getElement(2, 1), delta);
    }

    @Test
    public void testAddInvalidSizeMatrix() {
        Matrix m = getLeftMatrix(false);
        Matrix a = getInvalidSizeMatrix();

        boolean catched = false;
        try {
            m.add(a);
        } catch (IllegalArgumentException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testAdd() {
        Matrix m = getLeftMatrix(false);
        Matrix a = getAddMatrix(false);

        m.add(a);
        for (int idx = 0; idx < m.getSize(); idx++) {
            assertEquals(leftMatrixData[idx] + leftAddMatrixData[idx], m.getElement(idx), delta);
        }
    }

    @Test
    public void testSumInvalidSizeMatrix() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getInvalidSizeMatrix();

        boolean catched = false;
        try {
            Matrix s = Matrix.sum(a, b);
        } catch (IllegalArgumentException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testSum() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getAddMatrix(false);
        Matrix s = Matrix.sum(a, b);
        for (int idx = 0; idx < s.getSize(); idx++) {
            assertEquals(leftMatrixData[idx] + leftAddMatrixData[idx], s.getElement(idx), delta);
        }
    }

    @Test
    public void testMultiplyInvalidSizeMatrix() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getInvalidSizeMatrix();
        boolean catched = false;
        try {
            Matrix m = Matrix.multiply(a, b);
        } catch (IllegalArgumentException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    @Test
    public void testRowsOfMultiplyMatrix() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getRightMatrix(false);
        Matrix m = Matrix.multiply(a, b);
        assertEquals(leftMatrixRows, m.getRows());
    }

    @Test
    public void testColumnsOfMultiplyMatrix() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getRightMatrix(false);
        Matrix m = Matrix.multiply(a, b);
        assertEquals(rightMatrixCols, m.getColumns());
    }

    @Test
    public void testMultiplyMatrix() {
        Matrix a = getLeftMatrix(false);
        Matrix b = getRightMatrix(false);
        Matrix r = getResultMatrix();

        Matrix m = Matrix.multiply(a, b);
        for (int i = 0; i < m.getSize(); i++) {
            assertEquals(resultMatrixData[i], m.getElement(i), delta);
        }
    }

    private void doTestInvalidSizeMatrix(final int rows, final int columns) {
        boolean catched = false;
        try {
            Matrix m = new Matrix(rows, columns);
        } catch (IllegalArgumentException ex) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    private void doTestSetByInvalidIndex(final int row, final int col, final float testValue) {
        Matrix m = getLeftMatrix(false);
        boolean catched = false;
        try {
            m.setElement(row, col, testValue);
        } catch (IndexOutOfBoundsException e) {
            catched = true;
        } finally {
            assertTrue(catched);
        }
    }

    private Matrix getLeftMatrix(final boolean isEmpty) {
        if (isEmpty) {
            return new Matrix(leftMatrixRows, leftMatrixCols);
        } else {
            return new Matrix(leftMatrixRows, leftMatrixCols, leftMatrixData);
        }
    }

    private Matrix getAddMatrix(final boolean isEmpty) {
        if (isEmpty) {
            return new Matrix(leftMatrixRows, leftMatrixCols);
        } else {
            return new Matrix(leftMatrixRows, leftMatrixCols, leftAddMatrixData);
        }
    }

    private Matrix getRightMatrix(final boolean isEmpty) {
        if (isEmpty) {
            return new Matrix(rightMatrixRows, rightMatrixCols);
        } else {
            return new Matrix(rightMatrixRows, rightMatrixCols, rightMatrixData);
        }
    }

    private Matrix getResultMatrix() {
        return new Matrix(resultMultMatrixRows, resultMultMatrixCols, resultMatrixData);
    }

    private Matrix getInvalidSizeMatrix() {
        return new Matrix(invalidSizeMatrixRows, invalidSizeMatrixCols);
    }

}
