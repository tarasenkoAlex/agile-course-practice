package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {

    private ViewModel viewModel;

    private void fillFields(final String a, final String b, final String c) {
        viewModel.aCoefProperty().setValue(a);
        viewModel.bCoefProperty().setValue(b);
        viewModel.cCoefProperty().setValue(c);
    }

    private void fillFields() {
        fillFields("", "", "");
    }

    private void fillFields(final String a, final String b) {
        fillFields(a, b, "");
    }

    private void fillFields(final String a) {
        fillFields(a, "");
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.aCoefProperty().getValue());
        assertEquals("", viewModel.aCoefProperty().getValue());
        assertEquals("", viewModel.aCoefProperty().getValue());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }


    @Test
    public void canSetSuccessMessage() {
        fillFields("1", "1", "1");

        viewModel.solve();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenSolvingWithEmptyFields() {
        viewModel.solve();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenAllFieldsAreFilled() {
        fillFields("1", "1", "1");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        fillFields("bad input");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWithIncompleteInput() {
        fillFields("1", "1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void byDefaultSolveButtonIsDisable() {
        assertTrue(viewModel.solvingDisabledProperty().get());
    }

    @Test
    public void solveButtonIsDisabledWhenFormatIsBad() {
        fillFields("not a number");

        assertTrue(viewModel.solvingDisabledProperty().get());
    }

    @Test
    public void solveButtonIsDisabledWithIncompleteInput() {
        fillFields("1", "1");

        assertTrue(viewModel.solvingDisabledProperty().get());
    }

    @Test
    public void solveButtonIsEnabledWithThreeNumbersInput() {
        fillFields("1", "1", "1");

        assertFalse(viewModel.solvingDisabledProperty().get());
    }

    @Test
    public void solvingTwoRootsEquationHasTwoRootsOutput() {
        fillFields("1", "-5", "4");

        viewModel.solve();

        assertEquals("X(1) = 4,000; X(2) = 1,000", viewModel.resultProperty().get());
    }

    @Test
    public void solvingOneRootEquationGivesOneRootOutput() {
        fillFields("0", "2", "-4");

        viewModel.solve();

        assertEquals("X = 2,000", viewModel.resultProperty().get());
    }

    @Test
    public void solvingZerosCoefsEquationGivesInfiniteSetOfSolutionsMessage() {
        fillFields("0", "0", "0");

        viewModel.solve();

        assertEquals("Infinite Set of Solutions", viewModel.resultProperty().get());
    }

    @Test
    public void solvingInconsistentEquationGivesNoSolutionsMessage() {
        fillFields("0", "0", "1");

        viewModel.solve();

        assertEquals("No Solutions", viewModel.resultProperty().get());
    }
}
