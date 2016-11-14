package ru.unn.agile.Huffman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WhenEncodeString {
    private HuffmanAlg huffmanAlg;
    @Before
    public void setUp() {
        huffmanAlg = new HuffmanAlg();
    }

    @Test
<<<<<<< HEAD
    public void emptyStringGivesEmpty() {
        assertAddReturns("", "");
    }

    @Test
    public void oneSymbolStringGivesZero() {
        assertAddReturns("1", "a");
    }

    @Test
    public void twoSymbolsStringGivesOneBitSymbolCode() {
        assertAddReturns("01101", "abbab");
    }

    @Test
    public void fiveSymbolsStringWithDifferentFrequenciesGivesCheckedByMyselfResult() {
        assertAddReturns("010011011000000101010101111111111", "abbcccddddeeeee");
=======
    public void emptyStrGivesEmpty() {
        assertEncodeOutput("", "");
    }

    @Test
    public void oneSymbolStrGivesZero() {
        assertEncodeOutput("1", "a");
    }

    @Test
    public void twoSymbolsStrGivesOneBitSymbolCode() {
        assertEncodeOutput("01101", "abbab");
    }

    @Test
    public void fiveCharsStrWithDifferentFreqsGivesCorrectResult() {
        assertEncodeOutput("010011011000000101010101111111111", "abbcccddddeeeee");
>>>>>>> Fixed mistakes for qwert182
    }

    @Test
    public void nullInputGivesException() {
        try {
            huffmanAlg.encodeString(null);
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("NULL input! "
                    + "Use encode/decode methods for string arguments."));
        }
    }

    private void assertEncodeOutput(final String expected, final String input) {
        assertEquals(expected, huffmanAlg.encodeString(input));
    }
}
