package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {

    private ViewModel viewModel;

    public void setExteriorlViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

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
        FakeQuadraticEquationSolverLogger logger = new FakeQuadraticEquationSolverLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
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
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus().toString());
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
        assertTrue(viewModel.getSolvingDisabled());
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
    public void solvingTwoRootsEquationGivesTwoRootsOutput() {
        fillFields("1", "-5", "4");

        viewModel.solve();

        assertEquals("X(1) = 4.0; X(2) = 1.0", viewModel.resultProperty().get());
    }

    @Test
    public void solvingOneRootEquationGivesOneRootOutput() {
        fillFields("0", "2", "-4");

        viewModel.solve();

        assertEquals("X = 2.0", viewModel.resultProperty().get());
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
        assertEquals("No Solutions", viewModel.getResult().toString());
    }

    @Test
    public void createLoggerOnViewModel() {
        FakeQuadraticEquationSolverLogger logger = new FakeQuadraticEquationSolverLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void logIsEmptyOnStart() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsMessageAfterSolve() {
        fillFields("0", "0", "1");
        viewModel.solve();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + ViewModel.LogsMessages.SOLVE_WAS_PRESSED + ".*"));
    }

    @Test
    public void checkLogMessageFormatWhenPressSolve() {
        fillFields("0", "2", "1");

        viewModel.solve();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + "Solved. Arguments: a = 0; b = 2; c = 1."));
    }

    @Test
    public void canPutSeveralLogMessages() {
        fillFields("0", "2", "1");

        viewModel.solve();
        viewModel.solve();

        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void checkErrorMessage() {
        fillFields("a");
        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INCORRECT_INPUT));
    }

    @Test
    public void solveIsNotCalledWhenButtonIsDisabled() {
        viewModel.solve();

        assertTrue(viewModel.getLog().isEmpty());
        assertEquals("StringProperty [value: null]", viewModel.logsProperty().toString());
    }

    @Test
    public void checkLogMessageFormatWhenInputArguments() {
        fillFields("0", "2", "1");

        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INPUT_IN_FIELD_FINISHED
                + "Input coefficients are: \\[0; 2; 1\\]"));
        message = viewModel.getLogs();
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INPUT_IN_FIELD_FINISHED
                + "Input coefficients are: \\[0; 2; 1\\]" +"\n"));
    }

    @Test
    public void checkLogMessageFormatWhenManyActions() {
        fillFields("0");
        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);
        fillFields("0", "2");
        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);
        fillFields("0", "2", "1");
        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INPUT_IN_FIELD_FINISHED
                + "Input coefficients are: \\[0; ; \\]"));
        message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INPUT_IN_FIELD_FINISHED
                + "Input coefficients are: \\[0; 2; \\]"));
        message = viewModel.getLog().get(2);
        assertTrue(message.matches(".*" + ViewModel.LogsMessages.INPUT_IN_FIELD_FINISHED
                + "Input coefficients are: \\[0; 2; 1\\]"));
    }

    @Test
    public void createViewModelWithNullLogger() {
        try {
            new ViewModel(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter must be not null", ex.getMessage());
        }
    }

    @Test
    public void inputWithoutChangeField() {
        fillFields("a");
        viewModel.onFocusFieldChanged(Boolean.FALSE, Boolean.TRUE);
        fillFields("a2");
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void createViewModelWihtoutLogger() {
        ViewModel viewModel = new ViewModel();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus().toString());
    }

    @Test
    public void repeatInputInFieldSameValue() {
        fillFields("1");
        viewModel.onFocusFieldChanged(Boolean.TRUE, Boolean.FALSE);
        fillFields("1");
        assertEquals(1, viewModel.getLog().size());
    }
}
