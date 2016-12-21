package ru.unn.agile.vector3d.viewmodel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import ru.unn.agile.vector3d.model.Vector3D;
import ru.unn.agile.vector3d.viewmodel.ViewModel.OperationTab;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private final String validVectorString = "-5.6, 8, +0.003";
    private final String invalidVectorString = "-5.6, 8, 5dcfd";

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        TestLoggerImpl logger = new TestLoggerImpl();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canGetActiveTab() {
        assertNotNull(viewModel.getActiveTab());
    }

    @Test
    public void canSetActiveTab() {
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        assertEquals(OperationTab.DOTPRODUCT, viewModel.getActiveTab());
    }

    @Test
    public void isNormTabActiveOnStart() {
        assertEquals(OperationTab.NORM, viewModel.getActiveTab());
    }

    @Test
    public void canGetButtonStatus() {
        boolean enabled = viewModel.isButtonDisabled();
        assertNotNull(enabled);
    }

    @Test
    public void isButtonDisabledOnStart() {
        assertTrue(viewModel.isButtonDisabled());
    }

    @Test
    public void canGetVectorText() {
        String text = viewModel.getVectorText();
        assertNotNull(text);
    }

    @Test
    public void canSetVectorText() {
        viewModel.setVectorText(validVectorString);
        assertEquals(validVectorString, viewModel.getVectorText());
    }

    @Test
    public void canGetDotProductOperandText() {
        assertNotNull(viewModel.getDotProductOperandText());
    }

    @Test
    public void canSetDotProductOperandText() {
        viewModel.setDotProductOperandText(validVectorString);
        assertEquals(validVectorString, viewModel.getDotProductOperandText());
    }

    @Test
    public void canGetCrossProductOperandText() {
        assertNotNull(viewModel.getCrossProductOperandText());
    }

    @Test
    public void canSetCrossProductOperandText() {
        viewModel.setCrossProductOperandText(validVectorString);
        assertEquals(validVectorString, viewModel.getCrossProductOperandText());
    }

    @Test
    public void canGetNormResultText() {
        assertNotNull(viewModel.getNormResultText());
    }

    @Test
    public void canGetNormalizationResultText() {
        assertNotNull(viewModel.getNormalizationResultText());
    }

    @Test
    public void canGetDotProductResultText() {
        assertNotNull(viewModel.getDotProductResultText());
    }

    @Test
    public void canGetCrossProductResultText() {
        assertNotNull(viewModel.getCrossProductResultText());
    }

    @Test
    public void areAllTextsEmptyOnStart() {
        assertEquals("", viewModel.getVectorText());
        assertEquals("", viewModel.getDotProductOperandText());
        assertEquals("", viewModel.getCrossProductOperandText());
        assertEquals("", viewModel.getNormResultText());
        assertEquals("", viewModel.getNormalizationResultText());
        assertEquals("", viewModel.getDotProductResultText());
        assertEquals("", viewModel.getCrossProductResultText());
    }

    @Test
    public void isValidationCorrectForValidText() {
        assertTrue(viewModel.validate(validVectorString));
    }

    @Test
    public void isValidationCorrectForInvalidText() {
        assertFalse(viewModel.validate(invalidVectorString));
    }

    @Test
    public void isValidationCorrectForValidTextInParentheses() {
        assertTrue(viewModel.validate("(" + validVectorString + ")"));
    }

    @Test
    public void isValidationCorrectForValidTextWithOneOpenBracket() {
        assertFalse(viewModel.validate("(" + validVectorString));
    }

    @Test
    public void isValidationCorrectForValidTextWithOneCloseBracket() {
        assertFalse(viewModel.validate(validVectorString + ")"));
    }

    @Test
    public void canConvertValidStringToVector() {
        String text = "(5.6, 8, 9.000)";
        Vector3D vector = viewModel.vectorFromString(text);
        assertEquals(new Vector3D(5.6, 8, 9), vector);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantConvertInvalidStringToVector() {
        Vector3D vector = viewModel.vectorFromString(invalidVectorString);
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorTextAndNormOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        assertTrue(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorTextAndNormOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorTextAndNormalizationOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertTrue(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorTextAndNormalizationOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorAndOperandTextsAndDotProductOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        assertTrue(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorAndOperandTextsAndDotProductOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setDotProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorAndValidOperandTextsAndDotProductOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setDotProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorAndInvalidOperandTextsAndDotProductOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorAndOperandTextsAndCrossProductOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        assertTrue(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorAndOperandTextsAndCrossProductOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setCrossProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithInvalidVectorAndValidOperandTextsAndCrossProductOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setCrossProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isCanCalculateCheckCorrectWithValidVectorAndInvalidOperandTextsAndCrossProductOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        assertFalse(viewModel.canCalculate());
    }

    @Test
    public void isButtonEnabledWhenVectorTextValidAndNormOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        assertFalse(viewModel.isButtonDisabled());
    }

    @Test
    public void isButtonDisabledWhenVectorTextInvalidAndNormOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        assertTrue(viewModel.isButtonDisabled());
    }

    @Test
    public void isButtonEnabledWhenVectorTextValidAndNormalizationOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertFalse(viewModel.isButtonDisabled());
    }

    @Test
    public void isButtonDisabledWhenVectorTextInvalidAndNormalizationOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertTrue(viewModel.isButtonDisabled());
    }

    @Test
    public void canCalculateNormIfInputValid() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORM);

        viewModel.calculate();

        assertNotNull(viewModel.getNormResultText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCalculateNormIfInputInvalid() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        viewModel.calculate();
    }

    @Test
    public void canCalculateNormalizationIfInputValid() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);

        viewModel.calculate();

        assertNotNull(viewModel.getNormalizationResultText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCalculateNormalizationIfInputInvalid() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        viewModel.calculate();
    }

    @Test
    public void canCalculateDotProductIfInputValid() {
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);

        viewModel.calculate();

        assertNotNull(viewModel.getDotProductResultText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCalculateDotProductIfInputInvalid() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setDotProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        viewModel.calculate();
    }

    @Test
    public void canCalculateCrossProductIfInputValid() {
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);

        viewModel.calculate();

        assertNotNull(viewModel.getNormResultText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCalculateCrossProductIfInputInvalid() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setCrossProductOperandText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        viewModel.calculate();
    }

    @Test
    public void canGetStatusText() {
        assertNotNull(viewModel.getStatusText());
    }

    @Test
    public void isStatusWaitingOnStart() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatusText());
    }

    @Test
    public void isStatusBadIfNormOpAndInvalidVectorText() {
        viewModel.setActiveTab(OperationTab.NORM);
        viewModel.setVectorText(invalidVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.BAD.toString(), status);
    }

    @Test
    public void isStatusReadyIfNormOpAndValidVectorString() {
        viewModel.setActiveTab(OperationTab.NORM);
        viewModel.setVectorText(validVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.READY.toString(), status);
    }

    @Test
    public void isStatusBadIfNormalizationOpAndIvalidVectorText() {
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        viewModel.setVectorText(invalidVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.BAD.toString(), status);
    }

    @Test
    public void isStatusReadyIfNormalizationOpAndInvalidVectorText() {
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        viewModel.setVectorText(validVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.READY.toString(), status);
    }

    @Test
    public void isStatusWaitingIfDotProductOpAndEmptySecondOperand() {
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText("");

        String status = viewModel.getStatusText();

        assertEquals(Status.WAITING.toString(), status);
    }

    @Test
    public void isStatusBadIfDotProductOpAndInvalidSecondOperand() {
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(invalidVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.BAD.toString(), status);
    }

    @Test
    public void isStatusReadyIfDotProductOpAndInvalidSecondOperand() {
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(validVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.READY.toString(), status);
    }

    @Test
    public void isStatusWaitingIfCrossProductOpAndEmptySecondOperand() {
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText("");

        String status = viewModel.getStatusText();

        assertEquals(Status.WAITING.toString(), status);
    }

    @Test
    public void isStatusBadIfCrossProductOpAndInvalidSecondOperand() {
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(invalidVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.BAD.toString(), status);
    }

    @Test
    public void isStatusReadyIfCrossProductOpAndInvalidSecondOperand() {
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(validVectorString);

        String status = viewModel.getStatusText();

        assertEquals(Status.READY.toString(), status);
    }

    @Test
    public void canAccessActiveTabIndexProperty() {
        assertNotNull(viewModel.activeTabIndexProperty());
    }

    @Test
    public void canAccessVectorTextProperty() {
        assertNotNull(viewModel.vectorTextProperty());
    }

    @Test
    public void canAccessDotProductOperandTextProperty() {
        assertNotNull(viewModel.dotProductOperandTextProperty());
    }

    @Test
    public void canAccessCrossProductOperandTextProperty() {
        assertNotNull(viewModel.crossProductOperandTextProperty());
    }

    @Test
    public void canAccessNormResultTextProperty() {
        assertNotNull(viewModel.normResultTextProperty());
    }

    @Test
    public void canAccessNormalizationResultTextProperty() {
        assertNotNull(viewModel.normalizationResultTextProperty());
    }

    @Test
    public void canAccessDotProductResultTextProperty() {
        assertNotNull(viewModel.dotProductResultTextProperty());
    }

    @Test
    public void canAccessCrossProductResultTextProperty() {
        assertNotNull(viewModel.crossProductResultTextProperty());
    }

    @Test
    public void canAccessStatusTextProperty() {
        assertNotNull(viewModel.statusTextProperty());
    }

    @Test
    public void canAccessButtonDisabledProperty() {
        assertNotNull(viewModel.buttonDisabledProperty());
    }

    @Test
    public void testCreateWithFakeLogger() {
        try {
            ViewModel vm = new ViewModel();
        } catch (Exception e) {
            fail("Exception on empty ViewModel creration");
        }
    }

    @Test
    public void testNullIteratorWithFakeLogger() {
        ViewModel vm = new ViewModel();
        assertNull(vm.getLogger().iterator());
    }

    @Test
    public void testSetLogger() {
        ViewModel vm = new ViewModel();
        TestLoggerImpl logger = new TestLoggerImpl();
        vm.setLogger(logger);
        assertEquals(logger, vm.getLogger());
    }

    @Test
    public void testCreateViewModelWithLogger() {
        TestLoggerImpl logger = new TestLoggerImpl();
        ViewModel vm = new ViewModel(logger);

        assertNotNull(vm);
    }

    @Test
    public void testCreateViewModelWithNullLogger() {
        try {
            ViewModel vm = new ViewModel(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Logger can't be null", e.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void testLoggerAfterInit() {
        findRequiredText(viewModel.getLogger(), ViewModel.LogMessages.INIT_END);
    }

    @Test
    public void testLoggerAfterNormCalculate() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        viewModel.calculate();
        findRequiredText(viewModel.getLogger(), ViewModel.LogMessages.NORM_CALCULATE);
    }

    @Test
    public void testLoggerAfterNormalizationCalculate() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        viewModel.calculate();
        findRequiredText(viewModel.getLogger(), ViewModel.LogMessages.NORMALIZE_CALCULATE);
    }

    @Test
    public void testLoggerAfterDotProductCalculate() {
        viewModel.setVectorText(validVectorString);
        viewModel.setDotProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.DOTPRODUCT);
        viewModel.calculate();
        findRequiredText(viewModel.getLogger(), ViewModel.LogMessages.DOT_CALCULATE);
    }

    @Test
    public void testLoggerAfterCrossProductCalculate() {
        viewModel.setVectorText(validVectorString);
        viewModel.setCrossProductOperandText(validVectorString);
        viewModel.setActiveTab(OperationTab.CROSSPRODUCT);
        viewModel.calculate();
        findRequiredText(viewModel.getLogger(), ViewModel.LogMessages.CROSS_CALCULATE);
    }

    @Test
    public void testGetLogItems() {
        assertFalse(viewModel.logsItems().isEmpty());
    }

    @Test
    public void testOperationTabFromIndex() {
        assertNull(OperationTab.fromIndex(-1));
    }

    @Test
    public void testLogMessagesConstructor() {
        ViewModel.LogMessages lm = new ViewModel.LogMessages();
        assertNotNull(lm);
    }

    @Test
    public void testRemoveLogListener() {
        AbstractLogger.LoggerListener listener = new AbstractLogger.LoggerListener() {
            @Override
            public void onLogAdded(final String message) {
                // do nothing
            }
        };

        viewModel.getLogger().addListener(listener);
        int count = viewModel.getLogger().getListeners().size();
        viewModel.getLogger().removeListener(listener);
        assertEquals(count - 1, viewModel.getLogger().getListeners().size());
    }

    protected void findRequiredText(final AbstractLogger logger, final String requiredText) {
        boolean textFound = false;

        Iterator<String> it = logger.iterator();
        while (it.hasNext()) {
            if (requiredText.equals(it.next())) {
                textFound = true;
                break;
            }
        }

        assertTrue(textFound);
    }
}
