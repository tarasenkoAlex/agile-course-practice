package ru.unn.agile.MatrixOperationMultyAndAdd.Model;

/**
 * Created by Denis on 10.11.2016.
 */
public final class CalcOperationMatrix {

    private CalcOperationMatrix() { }

    public static boolean checkDimensionsForSum(final Matrix first, final Matrix second) {
        return first.getNumCol() == second.getNumCol() && first.getNumR() == second.getNumR();
    }
    public static boolean checkDimensinsForMulty(final Matrix first, final Matrix second) {
        return first.getNumCol() == second.getNumR();
    }
    public static Matrix sumMatrix(final Matrix first, final Matrix second) {
        if (checkDimensionsForSum(first, second)) {
            float[] resultSum = new float[first.getLengthMatrix()];
            for (int numberElement = 0; numberElement < first.getLengthMatrix(); numberElement++) {
                resultSum[numberElement] = first.getElementMatrix(numberElement)
                        + second.getElementMatrix(numberElement);
            }
            return new Matrix(first.getNumR(), first.getNumCol(), resultSum);
        }
        return null;
    }
    public static int getDimensinsForMulty(final Matrix first, final Matrix second) {
        return first.getNumR() * second.getNumCol();
    }

    public static Matrix multiplyMatrix(final Matrix first, final Matrix second) {
        if (checkDimensinsForMulty(first, second)) {
            float[] multyArray = new float[getDimensinsForMulty(first, second)];
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
