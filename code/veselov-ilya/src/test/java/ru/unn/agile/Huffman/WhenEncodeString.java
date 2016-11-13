package ru.unn.agile.Huffman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenEncodeString {
    private HuffmanAlg huffmanAlg;
    @Before
    public void setUp() {
        huffmanAlg = new HuffmanAlg();
    }

    @Test
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
    }

    @Test
    public void nullInputGivesNull() {
        assertAddReturns(null, null);
    }



    private void assertAddReturns(final String expected, final String input) {
        assertEquals(expected, huffmanAlg.encodeString(input));
    }
}
