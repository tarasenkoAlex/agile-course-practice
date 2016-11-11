package ru.unn.agile.MatrixOperationMultyAndAdd.Model;

import java.util.Arrays;

/**
 * Created by Denis on 10.11.2016.
 */
public class Matrix {
    private final int rows;
    private final int columns;
    private float[] elementsOfMatrix;
    private final int lengthMatrix;
    public Matrix(final int rowsMatrix, final int columnsMatrix, final float[] matrixElements) {
        if (rowsMatrix > 0 && columnsMatrix > 0) {
            this.rows = rowsMatrix;
            this.columns = columnsMatrix;
            this.elementsOfMatrix = Arrays.copyOf(matrixElements, matrixElements.length);
            this.lengthMatrix = matrixElements.length;
        } else {
            this.rows = 1;
            this.columns = 1;
            this.elementsOfMatrix = null;
            this.lengthMatrix = 1;
        }
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public float[] getElementsOfMatrix() {
        return elementsOfMatrix;
    }
    public int getLengthMatrix() {
        return lengthMatrix;
    }
    public float getElementMatrix(final int numberOfElement) {
        return elementsOfMatrix[numberOfElement];
    }
    public void setElementsOfMatrix(final float[] matrixElements) {
        this.elementsOfMatrix = Arrays.copyOf(matrixElements, matrixElements.length);
    }
    @Override
    public String toString() {
        String arrayText = "";
        for (int elementNumber = 0; elementNumber < elementsOfMatrix.length; elementNumber++) {
            arrayText += Float.toString(elementsOfMatrix[elementNumber]) + ",";
        }
        return arrayText;
    }
}
