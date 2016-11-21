package com.github.audice.matrixoperation.model;

import java.util.Arrays;

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

    public int getNumC() {
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

    public static Matrix sum(final Matrix first, final Matrix second) {
        if (first.getNumC() == second.getNumC()
                && first.getNumR() == second.getNumR()) {
            float[] resultSum = new float[first.elementsOfMatrix.length];
            for (int numEl = 0; numEl < first.elementsOfMatrix.length; numEl++) {
                resultSum[numEl] = first.getElementMatrix(numEl)
                        + second.getElementMatrix(numEl);
            }
            return new Matrix(first.getNumR(), first.getNumC(), resultSum);
        }
        return null;
    }

    public static Matrix multiply(final Matrix first, final Matrix second) {
        if (first.getNumC() == second.getNumR()) {
            float[] multyArray = new float[first.getNumR() * second.getNumC()];
            multArrProc(first, second, multyArray);
            return new Matrix(first.getNumR(), second.getNumC(), multyArray);
        }
        return null;
    }

    private static void multArrProc(final Matrix first, final Matrix second, final float[] mulAr) {
        for (int numberRow = 0; numberRow < first.getNumR(); numberRow++) {
            for (int numCol = 0; numCol < second.getNumC(); numCol++) {
                for (int k = 0; k < first.getNumC(); k++) {
                    mulAr[numberRow * second.getNumC() + numCol] +=
                            first.getElementMatrix(numberRow * first.getNumC() + k)
                                    * second.getElementMatrix(numCol + k * second.getNumC());
                }
            }
        }
    }
}
