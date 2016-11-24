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
    public void canEnableButton() {
        viewModel.enableButton();
        assertEquals(true, viewModel.isButtonEnabled());
    }

    @Test
    public void canDisableButton() {
        viewModel.enableButton();
        viewModel.disableButton();
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
    public void canSetNormResultText() {
        viewModel.setNormResultText(validVectorString);
        assertEquals(validVectorString, viewModel.getNormResultText());
    }

    @Test
    public void canGetNormalizationResultText() {
        assertNotNull(viewModel.getNormalizationResultText());
    }

    @Test
    public void canSetNormalizationResultText() {
        viewModel.setNormalizationResultText(validVectorString);
        assertEquals(validVectorString, viewModel.getNormalizationResultText());
    }

    @Test
    public void canGetDotProductResultText() {
        assertNotNull(viewModel.getDotProductResultText());
    }

    @Test
    public void canSetDotProductResultText() {
        viewModel.setDotProductResultText(validVectorString);
        assertEquals(validVectorString, viewModel.getDotProductResultText());
    }

    @Test
    public void canGetCrossProductResultText() {
        assertNotNull(viewModel.getCrossProductResultText());
    }

    @Test
    public void canSetCrossProductResultText() {
        viewModel.setCrossProductResultText(validVectorString);
        assertEquals(validVectorString, viewModel.getCrossProductResultText());
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
    public void isVectorTextOnSettingValidationCorrectForValidText() {
        viewModel.setVectorText(validVectorString);
        assertTrue(viewModel.isVectorTextValid());
    }

    @Test
    public void isVectorTextOnSettingValidationCorrectForInvalidText() {
        viewModel.setVectorText(invalidVectorString);
        assertFalse(viewModel.isVectorTextValid());
    }

    @Test
    public void isDotProductOperandTextOnSettingValidationCorrectForValidText() {
        viewModel.setDotProductOperandText(validVectorString);
        assertTrue(viewModel.isDotProductOperandTextValid());
    }

    @Test
    public void isDotProductOperandTextOnSettingValidationCorrectForInvalidText() {
        viewModel.setDotProductOperandText(invalidVectorString);
        assertFalse(viewModel.isDotProductOperandTextValid());
    }

    @Test
    public void isCrossProductOperandTextOnSettingValidationCorrectForValidText() {
        viewModel.setCrossProductOperandText(validVectorString);
        assertTrue(viewModel.isCrossProductOperandTextValid());
    }

    @Test
    public void isCrossProductOperandTextOnSettingValidationCorrectForInvalidText() {
        viewModel.setCrossProductOperandText(invalidVectorString);
        assertFalse(viewModel.isCrossProductOperandTextValid());
    }
}
