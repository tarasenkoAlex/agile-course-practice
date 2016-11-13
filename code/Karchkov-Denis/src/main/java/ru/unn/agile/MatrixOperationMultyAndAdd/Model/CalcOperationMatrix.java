package ru.unn.agile.MatrixOperationMultyAndAdd.Model;

/**
 * Created by Denis on 10.11.2016.
 */
public final class CalcOperationMatrix {

    private CalcOperationMatrix() { }

    public static boolean checkDimensionsForSum(final Matrix first, final Matrix second) {
        return first.getNumberColumns() == second.getNumberColumns() && first.getNumberRows() == second.getNumberRows();
    }
    public static boolean checkDimensinsForMulty(final Matrix first, final Matrix second) {
        return first.getNumberRows() == second.getNumberRows();
    }
    public static Matrix sumTwoMatrix(final Matrix first, final Matrix second) {
        if (checkDimensionsForSum(first, second)) {
            float[] resultSum = new float[first.getLengthMatrix()];
            for (int numberElement = 0; numberElement < first.getLengthMatrix(); numberElement++) {
                resultSum[numberElement] = first.getElementMatrix(numberElement)
                        + second.getElementMatrix(numberElement);
            }
            return new Matrix(first.getNumberRows(), first.getNumberColumns(), resultSum);
        }
        return null;
    }
    public static int getDimensinsForMulty(final Matrix first, final Matrix second) {
        return first.getNumberRows() * second.getNumberColumns();
    }

    public static Matrix multiplyMutrix(final Matrix first, final Matrix second) {
        if (checkDimensinsForMulty(first, second)) {
            float[] multyArray = new float[getDimensinsForMulty(first, second)];
            multArrProc(first, second, multyArray);
            return new Matrix(first.getNumberRows(), second.getNumberColumns(), multyArray);
        }
        return null;
    }

    public static void multArrProc(final Matrix first, final Matrix second, final float[] mulAr) {
        for (int numberRow = 0; numberRow < first.getNumberRows(); numberRow++) {
            for (int numCol = 0; numCol < second.getNumberColumns(); numCol++) {
                for (int k = 0; k < first.getNumberColumns(); k++) {
                    mulAr[numberRow * second.getNumberColumns() + numCol] +=
                            first.getElementMatrix(numberRow * first.getNumberColumns() + k)
                                    * second.getElementMatrix(numCol + k * second.getNumberColumns());
                }
            }
        }
    }
}
