package ru.unn.agile.matrixoperations.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelStatusTests {
    private final ViewModel.Status[] values = {ViewModel.Status.INVALID_LEFT_MATRIX_ROWS,
            ViewModel.Status.INVALID_LEFT_MATRIX_COLS,
            ViewModel.Status.INVALID_RIGHT_MATRIX_ROWS,
            ViewModel.Status.INVALID_RIGHT_MATRIX_COLS,
            ViewModel.Status.INVALID_MATRIX_SIZE,
            ViewModel.Status.READY,
            ViewModel.Status.SUCCESS,
    };

    private final Object[][] valuesPairs = {
            {ViewModel.Status.INVALID_LEFT_MATRIX_ROWS, "INVALID_LEFT_MATRIX_ROWS"},
            {ViewModel.Status.INVALID_LEFT_MATRIX_COLS, "INVALID_LEFT_MATRIX_COLS"},
            {ViewModel.Status.INVALID_RIGHT_MATRIX_ROWS, "INVALID_RIGHT_MATRIX_ROWS"},
            {ViewModel.Status.INVALID_RIGHT_MATRIX_COLS, "INVALID_RIGHT_MATRIX_COLS"},
            {ViewModel.Status.INVALID_MATRIX_SIZE, "INVALID_MATRIX_SIZE"},
            {ViewModel.Status.READY, "READY"},
            {ViewModel.Status.SUCCESS, "SUCCESS"},
    };

    private final Object[][] valuesPairsDesc = {
            {ViewModel.Status.INVALID_LEFT_MATRIX_ROWS, "Left matrix has invalid rows count."},
            {ViewModel.Status.INVALID_LEFT_MATRIX_COLS, "Left matrix has invalid columns count."},
            {ViewModel.Status.INVALID_RIGHT_MATRIX_ROWS, "Right matrix has invalid rows count."},
            {ViewModel.Status.INVALID_RIGHT_MATRIX_COLS,
                    "Right matrix has invalid columns count."},
            {ViewModel.Status.INVALID_MATRIX_SIZE,
                    "Right matrix has invalid size - it not agreed to left matrix"},
            {ViewModel.Status.READY, "Press 'Calculate'"},
            {ViewModel.Status.SUCCESS, "Success"},
    };

    @Test
    public void testStatusValueOf() {
        for (int i = 0; i < 7; i++) {
            assertEquals(valuesPairs[i][0],
                    ViewModel.Status.valueOf((String) (valuesPairs[i][1])));
        }
    }

    @Test
    public void testStatusValuesCount() {
        assertEquals(7, ViewModel.Status.values().length);
    }

    @Test
    public void testStatusValues() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], ViewModel.Status.values()[i]);
        }
    }

    @Test
    public void testStatusToString() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(valuesPairsDesc[i][1],
                    ViewModel.Status.values()[i].toString());
        }
    }
}
