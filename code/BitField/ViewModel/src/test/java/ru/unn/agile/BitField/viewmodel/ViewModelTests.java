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

    @Test
    public void byDefaultFieldA_FieldStringA() {
        assertEquals("00000000", viewModel.getBitFieldStringA());
    }

    @Test
    public void byDefaultFieldB_FieldStringB() {
        assertEquals("00000000", viewModel.getBitFieldStringB());
    }

    @Test
    public void whenEnterEmptyA_ErrorText() {
        viewModel.setBitFieldStringA("");

        assertEquals("Text Field is Empty", viewModel.getTextErrorA());
    }

    @Test
    public void whenEnterEmptyB_ErrorText() {
        viewModel.setBitFieldStringB("");

        assertEquals("Text Field is Empty", viewModel.getTextErrorB());
    }


    @Test
    public void whenEnterFieldWrongDataA_ErrorText() {
        viewModel.setBitFieldStringA("2");

        assertEquals("Only 0 and 1", viewModel.getTextErrorA());
    }

    @Test
    public void whenEnterFieldWrongDataB_ErrorText() {
        viewModel.setBitFieldStringB("a");

        assertEquals("Only 0 and 1", viewModel.getTextErrorB());
    }

    @Test
    public void inputBitFieldA_LengthMoreEight_ErrorText() {
        viewModel.setBitFieldStringA("111111111");

        assertEquals("Length of BitField must be less or equal 8", viewModel.getTextErrorA());
    }

    @Test
    public void inputBitFieldB_LengthMoreEight_ErrorText() {
        viewModel.setBitFieldStringB("000000000");

        assertEquals("Length of BitField must be less or equal 8", viewModel.getTextErrorB());
    }

    @Test
    public void setBitFieldA_LengthEqualEight() {
        viewModel.setBitFieldStringA("01010101");

        assertEquals("01010101", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldB_LengthEqualEight() {
        viewModel.setBitFieldStringB("10101010");

        assertEquals("10101010", viewModel.getBitFieldStringB());
    }

    @Test
    public void setBitFieldA_LengthLessEight() {
        viewModel.setBitFieldStringA("1111");

        assertEquals("00001111", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldB_LengthLessEight() {
        viewModel.setBitFieldStringB("111100");

        assertEquals("00111100", viewModel.getBitFieldStringB());
    }

    @Test
    public void setBitFieldBitA() {
        viewModel.setBitFieldBitA("5");

        assertEquals("00000100", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldBitB() {
        viewModel.setBitFieldBitB("3");

        assertEquals("00010000", viewModel.getBitFieldStringB());
    }

    @Test
    public void setBitFieldBitA_3Bits() {
        viewModel.setBitFieldBitA("1");
        viewModel.setBitFieldBitA("2");
        viewModel.setBitFieldBitA("7");

        assertEquals("01100001", viewModel.getBitFieldStringA());
    }

    @Test
    public void setBitFieldBitB_3Bits() {
        viewModel.setBitFieldBitB("0");
        viewModel.setBitFieldBitB("3");
        viewModel.setBitFieldBitB("6");

        assertEquals("10010010", viewModel.getBitFieldStringB());
    }

    @Test
    public void clearBitFieldBitA() {
        viewModel.setBitFieldStringA("11111111");
        viewModel.clearBitFieldBitA("5");

        assertEquals("11111011", viewModel.getBitFieldStringA());
    }

    @Test
    public void clearBitFieldBitB() {
        viewModel.setBitFieldStringB("11111111");
        viewModel.clearBitFieldBitB("3");

        assertEquals("11101111", viewModel.getBitFieldStringB());
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
    public void clearBitFieldBitB_3Bits() {
        viewModel.setBitFieldStringB("11111111");
        viewModel.clearBitFieldBitB("0");
        viewModel.clearBitFieldBitB("3");
        viewModel.clearBitFieldBitB("6");

        assertEquals("01101101", viewModel.getBitFieldStringB());
    }

    @Test
    public void getBitFieldBitA_One() {
        viewModel.setBitFieldStringA("00001111");
        viewModel.getBitFieldBitA("4");

        assertEquals("1", viewModel.getChooseBitA());
    }

    @Test
    public void getBitFieldBitB_One() {
        viewModel.setBitFieldStringB("00001111");
        viewModel.getBitFieldBitB("5");

        assertEquals("1", viewModel.getChooseBitB());
    }

    @Test
    public void getBitFieldBitA_Zero() {
        viewModel.setBitFieldStringA("00001111");
        viewModel.getBitFieldBitA("3");

        assertEquals("0", viewModel.getChooseBitA());
    }

    @Test
    public void getBitFieldBitB_Zero() {
        viewModel.setBitFieldStringB("00001111");
        viewModel.getBitFieldBitB("2");

        assertEquals("0", viewModel.getChooseBitB());
    }

    @Test
    public void notA() {
        viewModel.setBitFieldStringA("00010111");
        viewModel.logicNotA();

        assertEquals("11101000", viewModel.getBitFieldStringA());
    }

    @Test
    public void notB() {
        viewModel.setBitFieldStringB("10101010");
        viewModel.logicNotB();

        assertEquals("01010101", viewModel.getBitFieldStringB());
    }

    @Test
    public void resultAAndB() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("00001111");
        viewModel.logicAAndB();

        assertEquals("00001010", viewModel.getResultText());
    }

    @Test
    public void resultAAndB_2() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("01010101");
        viewModel.logicAAndB();

        assertEquals("00000000", viewModel.getResultText());
    }

    @Test
    public void resultAOrB() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("00001111");
        viewModel.logicAOrB();

        assertEquals("10101111", viewModel.getResultText());
    }

    @Test
    public void resultAOrB_2() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("01010101");
        viewModel.logicAOrB();

        assertEquals("11111111", viewModel.getResultText());
    }

    @Test
    public void resultAXorB() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("00001111");
        viewModel.logicAXorB();

        assertEquals("10100101", viewModel.getResultText());
    }

    @Test
    public void resultAXorB_2() {
        viewModel.setBitFieldStringA("10101010");
        viewModel.setBitFieldStringB("01010101");
        viewModel.logicAXorB();

        assertEquals("11111111", viewModel.getResultText());
    }
}
