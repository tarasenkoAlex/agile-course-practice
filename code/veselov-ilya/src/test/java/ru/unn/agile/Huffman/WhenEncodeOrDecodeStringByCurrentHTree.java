package ru.unn.agile.Huffman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WhenEncodeOrDecodeStringByCurrentHTree {
    private static final String INIT_STR = "abbcccddddeeeee";
    private HuffmanAlg huffmanAlg;
    @Before
    public void setUp() {
        huffmanAlg = new HuffmanAlg();
    }

    @Test
    public void emptyStringEncodeGivesEmptyWithCurrentHTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode("", "");
    }

    @Test
    public void symbolAGivesCorrectCodeWithCurrentHTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode("010", "a");
    }

    @Test
    public void encodingStringAndEncodingStringByCurrentTreeGivesSameResults() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode(huffmanAlg.encodeString(INIT_STR), INIT_STR);
    }

    @Test
    public void encodingStringByCurrentTreeWithNoTreeInObject() {
        try {
            huffmanAlg.encodeStringByCurrentHTree(INIT_STR);
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("HTree hasn't been built."));
        }
    }

    @Test
    public void encodingStringByCurrentTreeWithMissingCharactersInTree() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.encodeStringByCurrentHTree("Pro");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("Any characters "
                    + "in input string"
                    + " hasn't been used for HTree building!"));
        }
    }

    @Test
    public void encodingStringByCurrentTreeWithMissingAndExistingCharactersInTree() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.encodeStringByCurrentHTree("abcPde");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("Any characters "
                    + "in input string"
                    + " hasn't been used for HTree building!"));
        }
    }
    @Test
    public void emptyStringDecodeGivesEmptyWithCurrentHTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("", "");
    }

    @Test
    public void codeForBGivesBWithCurrentHTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("b", "011");
    }

    @Test
    public void codeForAllSymbolsGivesCorrectSymbolsWithCurrentHTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("abcde", "010011001011");
    }

    @Test
    public void nullInputGivesNullWithCurrentHTreeDecoding() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode(null, null);
    }

    @Test
    public void decodingCodeOfInitStringGivesInitString() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode(INIT_STR, huffmanAlg.encodeString(INIT_STR));
    }

    @Test
    public void decodingBinarySequenceWithNoTreeInObject() {
        try {
            huffmanAlg.decodeBinarySequenceByCurrentHTree("1010110");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("HTree hasn't been built."));
        }
    }

    @Test
    public void decodingNonBinarySequenceWithCurrentTreeInObject() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.decodeBinarySequenceByCurrentHTree("10001P");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("Decoding input string must "
                    + "contain only '1' or '0' symbols!"));
        }
    }

    @Test
    public void decodingWrongBinarySequenceForCurrentTreeInObject() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.decodeBinarySequenceByCurrentHTree("0100110010110");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("Incorrect binary"
                    + " sequence for current HTree!"));
        }
    }

    private void assertAddReturnsEncode(final String expected, final String input) {
        assertEquals(expected, huffmanAlg.encodeStringByCurrentHTree(input));
    }

    private void assertAddReturnsDecode(final String expected, final String input) {
        assertEquals(expected, huffmanAlg.decodeBinarySequenceByCurrentHTree(input));
    }
}

