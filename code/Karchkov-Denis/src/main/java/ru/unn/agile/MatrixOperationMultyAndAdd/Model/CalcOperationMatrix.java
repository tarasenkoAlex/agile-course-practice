package ru.unn.agile.MatrixOperationMultyAndAdd.Model;

/**
 * Created by Denis on 10.11.2016.
 */
public final class CalcOperationMatrix {

    private CalcOperationMatrix() { }

    public static boolean checkDimensionsForSum(final Matrix first, final Matrix second) {
        return (first.getColumns() == second.getColumns()) && (first.getRows() == second.getRows());
    }
    public static boolean checkDimensinsForMulty(final Matrix first, final Matrix second) {
        return (first.getColumns() == second.getRows());
    }
    public static Matrix sumTwoMatrix(final Matrix first, final Matrix second) {
        if (checkDimensionsForSum(first, second)) {
            float [] resultSum = new float[first.getLengthMatrix()];
            for (int numberElement = 0; numberElement < first.getLengthMatrix(); numberElement++) {
                resultSum[numberElement] = first.getElementMatrix(numberElement)
                        + second.getElementMatrix(numberElement);
            }
            return new Matrix(first.getRows(), first.getColumns(), resultSum);

        } else {
            return null;
        }
    }
    public static int getDimensinsForMulty(final Matrix first, final Matrix second) {
        return first.getRows() * second.getColumns();
    }

    public static Matrix multyTwoMutrix(final Matrix first, final Matrix second) {
        if (checkDimensinsForMulty(first, second)) {
            float [] multyArray = new float[getDimensinsForMulty(first, second)];
            multArrProc(first, second, multyArray);
            return new Matrix(first.getRows(), second.getColumns(), multyArray);
        } else {
            return null;
        }
    }

    public static String arryeToString(final float[] arrayOfElements) {
        String arrayText = "";
        for (int numberElement = 0; numberElement < arrayOfElements.length; numberElement++) {
            arrayText += Float.toString(arrayOfElements[numberElement]) + ",";
        }
        return arrayText;
    }

    public static void multArrProc(final Matrix first, final Matrix second, final float [] mulAr) {
        for (int numberRow = 0; numberRow < first.getRows(); numberRow++) {
            for (int numCol = 0; numCol < second.getColumns(); numCol++) {
                for (int k = 0; k < first.getColumns(); k++) {
                    mulAr[numberRow * second.getColumns() + numCol] +=
                            first.getElementMatrix(numberRow * first.getColumns() + k)
                                    * second.getElementMatrix(numCol + k * second.getColumns());
                }
            }
        }
    }

    public static String multyTwoArray(final Matrix first, final Matrix second) {
        float [] multyArray = new float[getDimensinsForMulty(first, second)];
        multArrProc(first, second, multyArray);
        return arryeToString(multyArray);
    }
}
