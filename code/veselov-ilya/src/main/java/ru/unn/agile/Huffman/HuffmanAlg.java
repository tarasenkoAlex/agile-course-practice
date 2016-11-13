package ru.unn.agile.Huffman;

import java.util.PriorityQueue;

public class HuffmanAlg {
    private HTree hTree = null;
    private static final int ASCII_SIZE = 256;

    private class HTree implements Comparable<HTree> {
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

        public String getNodeSymbols() {
            return symbolsInTree;
        }

        public int compareTo(final HTree comparableTree) {
            return frequency - comparableTree.frequency;
        }
    }

    private class HNode extends HTree {
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
    }

    private class HLeaf extends HTree {
        public HLeaf(final int frequency, final char c) {
            super(frequency, String.valueOf(c));
        }
    }

    private void buildTree(final String stringForBuilding) {
        PriorityQueue<HTree> nodesMaker = new PriorityQueue<HTree>();
        int[] frequenciesOfSymbols = countingFrequencies(stringForBuilding.toCharArray());

        for (int i = 0; i < frequenciesOfSymbols.length; ++i) {
            if (frequenciesOfSymbols[i] != 0) {
                nodesMaker.offer(new HLeaf(frequenciesOfSymbols[i], (char) i));
            }
        }

        while (nodesMaker.size() > 1) {
            HTree l = nodesMaker.poll();
            HTree r = nodesMaker.poll();
            nodesMaker.offer(new HNode(l, r));
        }

        hTree = nodesMaker.poll();
    }

    private int[] countingFrequencies(final char[] charArray) {
        int[] frequenciesOfSymbols = new int[ASCII_SIZE];

        for (char c : charArray) {
            frequenciesOfSymbols[c]++;
        }
        return frequenciesOfSymbols;
    }

    private void builtHTreeCheck() {
        if (hTree == null) {
            throw new IllegalArgumentException("HTree hasn't been built.");
        }
    }

    public String encodeStringByCurrentHTree(final String stringForEncoding) {
        if ("".equals(stringForEncoding)) {
            return "";
        }
        builtHTreeCheck();
        String codedString = "";
        char currentCharacterInInputString;

        for (int i = 0; i < stringForEncoding.length(); ++i) {
            currentCharacterInInputString = stringForEncoding.charAt(i);
            HTree currentNode = hTree;

            if (!currentNode.getNodeSymbols()
                    .contains(String.valueOf(currentCharacterInInputString))) {
                throw new IllegalArgumentException("Any characters "
                        + "in input string"
                        + " hasn't been used for HTree building!");
            }

            if (hTree instanceof HLeaf) {
                codedString += '1';
            }

            while (currentNode instanceof HNode) {
                if (((HNode) currentNode)
                        .getLeftTree()
                        .getNodeSymbols()
                        .contains(String.valueOf(currentCharacterInInputString))) {
                    currentNode = ((HNode) currentNode).getLeftTree();
                    codedString += '0';
                } else {
                    currentNode = ((HNode) currentNode).getRightTree();
                    codedString += '1';
                }
            }
        }
        return codedString;
    }

    public String encodeString(final String stringForEncoding) {
        if (stringForEncoding == null) {
            return null;
        }
        buildTree(stringForEncoding);
        return encodeStringByCurrentHTree(stringForEncoding);
    }

    public String decodeBinarySequenceByCurrentHTree(final String stringForDecoding) {
        if (stringForDecoding == null) {
            return null;
        }
        if ("".equals(stringForDecoding)) {
            return "";
        }
        builtHTreeCheck();
        String decodedString = "";
        HTree currentNode = hTree;
        char currentCharacterInInputString;

        for (int i = 0; i < stringForDecoding.length(); ++i) {
            currentCharacterInInputString = stringForDecoding.charAt(i);

            if (currentNode instanceof  HTree) {

                if (currentCharacterInInputString == '0') {
                    currentNode = ((HNode) currentNode).getLeftTree();
                }

                if (currentCharacterInInputString == '1') {
                    currentNode = ((HNode) currentNode).getRightTree();
                }

                if (currentCharacterInInputString != '0' && currentCharacterInInputString != '1') {
                    throw new IllegalArgumentException("Decoding input string must contain"
                            + " only '1' or '0' symbols!");
                }
            }

            if (currentNode instanceof  HLeaf) {
                decodedString += currentNode.getNodeSymbols();
                currentNode = hTree;
            }
        }

        if (!currentNode.equals(hTree)) {
            throw new IllegalArgumentException("Incorrect binary sequence for current HTree!");
        }

        return decodedString;
    }
}
