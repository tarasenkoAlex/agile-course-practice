package ru.unn.agile.MatrixOperationMultyAndAdd.Model;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Denis on 10.11.2016.
 */
public class MatrixMultyAndAddTest {
    private int rows = 2;
    private int columns = 3;
    private float[] testArray = new float[]{1, 2, 3, 4, 5, 6};
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
    public void compareMatrixAndTestArrayInTextImage() {
        matrix = new Matrix(rows, columns, new float[]{1, 2, 3, 4, 5, 6});
        assertEquals(arryeToString(new float[]{1, 2, 3, 4, 5, 6}), matrix.toString());
    }
    @Test
    public void canSetNumberOfRows() {
        matrix = new Matrix(5, columns, testArray);
        assertEquals(5, matrix.getRows());
    }
    @Test
    public void canSetNumberOfColumns() {
        matrix = new Matrix(rows, 7, testArray);
        assertEquals(7, matrix.getColumns());
    }
}
