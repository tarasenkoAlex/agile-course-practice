package ru.unn.agile.BitField.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    // Field A Tests

    @Test
    public void byDefaultFieldA_InputButtonIsDisabledA() {
        assertTrue(viewModel.getInputButtonDisabledA());
    }

    @Test
    public void byDefaultFieldA_FieldAString() {
        assertEquals("00000000", viewModel.getBitFieldStringA());
    }

    @Test
    public void whenEnterFieldA_InputAButtonIsEnabled() {
        viewModel.inputBitFieldA("1");

        assertFalse(viewModel.getInputButtonDisabledA());
    }

    @Test
    public void whenClearFieldA_InputAButtonIsDisabled() {
        viewModel.inputBitFieldA("1");
        viewModel.inputBitFieldA("");

        assertTrue(viewModel.getInputButtonDisabledA());
    }

    @Test
    public void whenEnterFieldWrongDataA_InputAButtonIsDisabled() {
        viewModel.inputBitFieldA("2");

        assertTrue(viewModel.getInputButtonDisabledA());
    }

    @Test
    public void whenEnterFieldWrongDataA_ErrorText() {
        viewModel.inputBitFieldA("2");

        assertEquals("Only 0 and 1", viewModel.getTextErrorA());
    }

    @Test
    public void inputBitFieldA_LengthMoreEight_ErrorText() {
        viewModel.inputBitFieldA("111111111");

        assertEquals("Length of BitField must be less or equal 8", viewModel.getTextErrorA());
    }

    @Test
    public void inputBitFieldA_LengthMoreEight_nputAButtonIsDisabled() {
        viewModel.inputBitFieldA("111111111");

        assertTrue(viewModel.getInputButtonDisabledA());
    }

    @Test
    public void setBitFieldA_LengthEqualEight() {
        viewModel.setBitFieldStringA("01010101");

        assertEquals("01010101", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldA_LengthLessEight() {
        viewModel.setBitFieldStringA("1111");

        assertEquals("00001111", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldBitA() {
        viewModel.setBitFieldBitA("5");

        assertEquals("00000100", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldBitA_3Bits() {
        viewModel.setBitFieldBitA("1");
        viewModel.setBitFieldBitA("2");
        viewModel.setBitFieldBitA("7");

        assertEquals("01100001", viewModel.getBitFieldStringA());
    }

    @Test
    public void clearBitFieldBitA() {
        viewModel.setBitFieldStringA("11111111");
        viewModel.clearBitFieldBitA("5");

        assertEquals("11111011", viewModel.getBitFieldStringA());
    }

    @Test
    public void clearBitFieldBitA_3Bits() {
        viewModel.setBitFieldStringA("11111111");
        viewModel.clearBitFieldBitA("1");
        viewModel.clearBitFieldBitA("2");
        viewModel.clearBitFieldBitA("7");

        assertEquals("10011110", viewModel.getBitFieldStringA());
    }

    @Test
    public void getBitFieldBitA_One() {
        viewModel.setBitFieldStringA("00001111");
        viewModel.getBitFieldBitA("4");

        assertEquals("1", viewModel.getChooseBitA());
    }

    @Test
    public void getBitFieldBitA_Zero() {
        viewModel.setBitFieldStringA("00001111");
        viewModel.getBitFieldBitA("3");

        assertEquals("0", viewModel.getChooseBitA());
    }

    @Test
    public void notA() {
        viewModel.setBitFieldStringA("00010111");
        viewModel.logicNotA();

        assertEquals("11101000", viewModel.getBitFieldStringA());
    }
}
