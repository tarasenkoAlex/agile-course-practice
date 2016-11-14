package ru.unn.agile.Huffman;

public class HTree implements Comparable<HTree> {
    private final String symbolsInTree;
    private final int frequency;

    protected String getSymbolsInTree() {
        return symbolsInTree;
    }

    protected int getFrequency() {
        return frequency;
    }

    public HTree(final int frequency, final String symbolsInTree) {
        this.frequency = frequency;
        this.symbolsInTree = symbolsInTree;
    }

    public int compareTo(final HTree comparableTree) {
        return frequency - comparableTree.frequency;
    }
}
