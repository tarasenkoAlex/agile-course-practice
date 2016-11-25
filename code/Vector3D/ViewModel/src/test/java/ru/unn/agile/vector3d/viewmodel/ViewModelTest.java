package ru.unn.agile.vector3d.viewmodel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import ru.unn.agile.vector3d.model.Vector3D;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private final String validVectorString = "-5.6, 8, +0.003";
    private final String invalidVectorString = "-5.6, 8, 5dcfd";

    @Before
    public void setUp() {
        viewModel = new ViewModel();
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
        boolean enabled = viewModel.isButtonEnabled();
        assertNotNull(enabled);
    }

    @Test
    public void isButtonDisabledOnStart() {
        assertEquals(false, viewModel.isButtonEnabled());
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
        assertTrue(viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenVectorTextInvalidAndNormOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORM);
        assertFalse(viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonEnabledWhenVectorTextValidAndNormalizationOp() {
        viewModel.setVectorText(validVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertTrue(viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenVectorTextInvalidAndNormalizationOp() {
        viewModel.setVectorText(invalidVectorString);
        viewModel.setActiveTab(OperationTab.NORMALIZATION);
        assertFalse(viewModel.isButtonEnabled());
    }
}
