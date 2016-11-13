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
    public void emptyStrEncodeGivesEmptyStrWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode("", "");
    }

    @Test
    public void nullInputForEncodeGivesExceptionWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.encodeStringByCurrentHTree(null);
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("NULL input! "
                    + "Use encode/decode methods for string arguments."));
        }
    }

    @Test
    public void symbolAGivesCorrectCodeWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode("010", "a");
    }

    @Test
    public void encodeStrAndEncodeStrByCurrentTreeGiveSameResults() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsEncode(huffmanAlg.encodeString(INIT_STR), INIT_STR);
    }

    @Test
    public void encodeStrByCurrentTreeWithNoTreeInObjectGivesException() {
        try {
            huffmanAlg.encodeStringByCurrentHTree(INIT_STR);
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("HTree hasn't been built."));
        }
    }

    @Test
    public void encodeStrByCurrentTreeWithMissingCharsInTreeGivesException() {
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
    public void encodeStrByCurrentTreeWithMissingAndExistingCharsInTree() {
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
    public void emptyStrDecodeGivesEmptyWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("", "");
    }

    @Test
    public void codeForBGivesBWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("b", "011");
    }

    @Test
    public void codeForAllCharsGivesCorrectCharsWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode("abcde", "010011001011");
    }

    @Test
    public void nullInputForDecodeGivesExceptionWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.encodeStringByCurrentHTree(null);
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("NULL input! "
                    + "Use encode/decode methods for string arguments."));
        }
    }

    @Test
    public void decodeCodeOfInitStrGivesInitStrWithCurrentTree() {
        huffmanAlg.encodeString(INIT_STR);
        assertAddReturnsDecode(INIT_STR, huffmanAlg.encodeString(INIT_STR));
    }

    @Test
    public void decodeBinSequenceWithNoTreeInObjectGivesException() {
        try {
            huffmanAlg.decodeBinarySequenceByCurrentHTree("1010110");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("HTree hasn't been built."));
        }
    }

    @Test
    public void decodeNonBinSequenceWithCurrentTreeInObjectGivesException() {
        huffmanAlg.encodeString(INIT_STR);
        try {
            huffmanAlg.decodeBinarySequenceByCurrentHTree("10001P");
        } catch (IllegalArgumentException caughtException) {
            assertThat(caughtException.getMessage(), is("Decoding input string must "
                    + "contain only '1' or '0' symbols!"));
        }
    }

    @Test
    public void decodeWrongBinSequenceForCurrentTreeInObjectGivesException() {
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

