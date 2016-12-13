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
    public void byDefaultAField_InputAButtonIsDisabled() {
        assertTrue(viewModel.getInputAButtonDisabled());
    }

    @Test
    public void byDefaultAField_FieldAString() {
        assertEquals("00000000", viewModel.getBitFieldAString());
    }

    @Test
    public void whenEnterAField_InputAButtonIsEnabled() {
        viewModel.inputABitField("1");

        assertFalse(viewModel.getInputAButtonDisabled());
    }

    @Test
    public void whenClearAField_InputAButtonIsDisabled() {
        viewModel.inputABitField("1");
        viewModel.inputABitField("");

        assertTrue(viewModel.getInputAButtonDisabled());
    }

    @Test
    public void whenEnterAFieldWrongData_InputAButtonIsDisabled() {
        viewModel.inputABitField("2");

        assertTrue(viewModel.getInputAButtonDisabled());
    }

    @Test
    public void whenEnterAFieldWrongData_ErrorText() {
        viewModel.inputABitField("2");

        assertEquals("Only 0 and 1", viewModel.getTextErrorA());
    }

    @Test
    public void inputABitField_LengthEqualEight() {
        viewModel.setABitFieldString("01010101");

        assertEquals("01010101", viewModel.getBitFieldAString());
    }

    @Test
    public void inputABitField_LengthLessEight() {
        viewModel.setABitFieldString("1111");

        assertEquals("00001111", viewModel.getBitFieldAString());
    }

    @Test
    public void inputABitField_LengtMoreEight() {
        viewModel.setABitFieldString("111111111");

        assertEquals("Lenght of BitField must be less or equal 8", viewModel.getTextErrorA());
    }

    @Test
    public void setABitFieldBit() {
        viewModel.setABitFieldBit("5");

        assertEquals("00000100", viewModel.getBitFieldAString());
    }

    @Test
    public void setABitFieldBit_3Bits() {
        viewModel.setABitFieldBit("1");
        viewModel.setABitFieldBit("2");
        viewModel.setABitFieldBit("7");

        assertEquals("01100001", viewModel.getBitFieldAString());
    }

    @Test
    public void clearABitFieldBit() {
        viewModel.setABitFieldString("11111111");
        viewModel.clearABitFieldBit("5");

        assertEquals("11111011", viewModel.getBitFieldAString());
    }

    @Test
    public void clearABitFieldBit_3Bits() {
        viewModel.setABitFieldString("11111111");
        viewModel.clearABitFieldBit("1");
        viewModel.clearABitFieldBit("2");
        viewModel.clearABitFieldBit("7");

        assertEquals("10011110", viewModel.getBitFieldAString());
    }

    @Test
    public void getABitFieldBit_One() {
        viewModel.setABitFieldString("00001111");
        viewModel.getABitFieldBit("4");

        assertEquals("1", viewModel.getChooseBitA());
    }

    @Test
    public void getABitFieldBit_Zero() {
        viewModel.setABitFieldString("00001111");
        viewModel.getABitFieldBit("3");

        assertEquals("0", viewModel.getChooseBitA());
    }

    @Test
    public void notA() {
        viewModel.setABitFieldString("00010111");
        viewModel.logicNotA();

        assertEquals("11101000", viewModel.getBitFieldAString());
    }
}
