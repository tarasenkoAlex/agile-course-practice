package ru.unn.agile.Huffman;

public final class HNode extends HTree {
    private final HTree left;
    private final HTree right;

    public HNode(final HTree l, final HTree r) {
        super(l.getFrequency() + r.getFrequency(), l.getSymbolsInTree() + r.getSymbolsInTree());
        left = l;
        right = r;
    }

    public HTree getLeftTree() {
        return left;
    }

    public HTree getRightTree() {
        return right;
    }

    public boolean leftTreeContains(final char c) {
        return left.contains(c);
    }
}
