package main.java.com.github.audice.matrixoperation.model;

import java.util.Arrays;
/**
 * Created by Denis on 10.11.2016.
 */
public class Matrix {
    private int rows;
    private int columns;
    private float[] elementsOfMatrix;

    public Matrix(final int rowsMatrix, final int columnsMatrix, final float[] matrixElements) {
        if (rowsMatrix > 0 && columnsMatrix > 0) {
            this.rows = rowsMatrix;
            this.columns = columnsMatrix;
            this.elementsOfMatrix = Arrays.copyOf(matrixElements, matrixElements.length);
        }
    }
    public int getNumR() {
        return this.rows;
    }
    public int getNumCol() {
        return this.columns;
    }
    public float getElementMatrix(final int numberOfElement) {
        return this.elementsOfMatrix[numberOfElement];
    }
    @Override
    public String toString() {
        String arrayText = "";
        if (this.rows > 0 && this.columns > 0) {
            for (int elementNumber = 0; elementNumber < elementsOfMatrix.length; elementNumber++) {
                arrayText += Float.toString(elementsOfMatrix[elementNumber]) + ",";
            }
            return arrayText;
        }
        return null;
    }

    public static Matrix sumMatrix(final Matrix first, final Matrix second) {
        if (first.getNumCol() == second.getNumCol()
                && first.getNumR() == second.getNumR()) {
            float[] resultSum = new float[first.elementsOfMatrix.length];
            for (int numEl = 0; numEl < first.elementsOfMatrix.length; numEl++) {
                resultSum[numEl] = first.getElementMatrix(numEl)
                        + second.getElementMatrix(numEl);
            }
            return new Matrix(first.getNumR(), first.getNumCol(), resultSum);
        }
        return null;
    }

    public static Matrix multiplyMatrix(final Matrix first, final Matrix second) {
        if (first.getNumCol() == second.getNumR()) {
            float[] multyArray = new float[first.getNumR() * second.getNumCol()];
            multArrProc(first, second, multyArray);
            return new Matrix(first.getNumR(), second.getNumCol(), multyArray);
        }
        return null;
    }

    public static void multArrProc(final Matrix first, final Matrix second, final float[] mulAr) {
        for (int numberRow = 0; numberRow < first.getNumR(); numberRow++) {
            for (int numCol = 0; numCol < second.getNumCol(); numCol++) {
                for (int k = 0; k < first.getNumCol(); k++) {
                    mulAr[numberRow * second.getNumCol() + numCol] +=
                            first.getElementMatrix(numberRow * first.getNumCol() + k)
                                    * second.getElementMatrix(numCol + k * second.getNumCol());
                }
            }
        }
    }
}
