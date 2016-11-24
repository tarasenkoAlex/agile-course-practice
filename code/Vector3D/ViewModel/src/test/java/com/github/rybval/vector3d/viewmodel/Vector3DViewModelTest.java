package com.github.rybval.vector3d.viewmodel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import ru.unn.agile.vector3d.model.Vector3D;

import static org.junit.Assert.*;

public class Vector3DViewModelTest {
    private Vector3DViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new Vector3DViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
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
        Vector3DViewModel viewModel = new Vector3DViewModel();
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
        String text = "some text";
        viewModel.setVectorText(text);
        assertEquals(text, viewModel.getVectorText());
    }

    @Test
    public void canGetDotProductOperandText() {
        String text = viewModel.getDotProductOperandText();
        assertNotNull(text);
    }

    @Test
    public void canSetDotProductOperandText() {
        String text = "some text";
        viewModel.setDotProductOperandText(text);
        assertEquals(text, viewModel.getDotProductOperandText());
    }

    @Test
    public void canGetCrossProductOperandText() {
        String text = viewModel.getCrossProductOperandText();
        assertNotNull(text);
    }

    @Test
    public void canSetCrossProductOperandText() {
        String text = "some text";
        viewModel.setCrossProductOperandText(text);
        assertEquals(text, viewModel.getCrossProductOperandText());
    }

    @Test
    public void canGetNormResultText() {
        String text = viewModel.getNormResultText();
        assertNotNull(text);
    }

    @Test
    public void canSetNormResultText() {
        String text = "some text";
        viewModel.setNormResultText(text);
        assertEquals(text, viewModel.getNormResultText());
    }

    @Test
    public void canGetNormalizationResultText() {
        String text = viewModel.getNormalizationResultText();
        assertNotNull(text);
    }

    @Test
    public void canSetNormalizationResultText() {
        String text = "some text";
        viewModel.setNormalizationResultText(text);
        assertEquals(text, viewModel.getNormalizationResultText());
    }

    @Test
    public void canGetDotProductResultText() {
        String text = viewModel.getDotProductResultText();
        assertNotNull(text);
    }

    @Test
    public void canSetDotProductResultText() {
        String text = "some text";
        viewModel.setDotProductResultText(text);
        assertEquals(text, viewModel.getDotProductResultText());
    }

    @Test
    public void canGetCrossProductResultText() {
        String text = viewModel.getCrossProductResultText();
        assertNotNull(text);
    }

    @Test
    public void canSetCrossProductResultText() {
        String text = "some text";
        viewModel.setCrossProductResultText(text);
        assertEquals(text, viewModel.getCrossProductResultText());
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
        String text = "5.6, 8, 9.000";
        assertTrue(viewModel.validate(text));
    }

    @Test
    public void isValidationCorrectForInvalidText() {
        String text = "asdfv";
        assertFalse(viewModel.validate(text));
    }

    @Test
    public void isValidationCorrectForValidTextInParentheses() {
        String text = "(5.6, 8, 9.000)";
        assertTrue(viewModel.validate(text));
    }

    @Test
    public void isValidationCorrectForValidTextWithOneBracket() {
        String text = "(5.6, 8, 9.000";
        assertFalse(viewModel.validate(text));
    }

    @Test
    public void canConvertValidStringToVector() {
        String text = "(5.6, 8, 9.000)";
        Vector3D vector = viewModel.vectorFromString(text);
        assertEquals(new Vector3D(5.6, 8, 9), vector);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantConvertInvalidStringToVector() {
        String text = "(5.6, 8, 9.000";
        Vector3D vector = viewModel.vectorFromString(text);
    }
}
