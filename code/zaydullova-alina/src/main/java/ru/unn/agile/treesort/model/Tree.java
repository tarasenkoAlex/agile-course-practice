package ru.unn.agile.treesort.model;

import java.util.Collection;
import java.util.LinkedList;

public class Tree {
    private final Integer key;
    private Tree leftTree;
    private Tree rightTree;

    public Tree(final Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public Tree getLeft() {
        return leftTree;
    }

    public Tree getRight() {
        return rightTree;
    }

    public void insert(final Integer key) {
        int cmpResult = this.key.compareTo(key);

        if (cmpResult <= 0) {
            if (rightTree == null) {
                rightTree = new Tree(key);
            } else {
                rightTree.insert(key);
            }
        } else if (cmpResult > 0) {
            if (leftTree == null) {
                leftTree = new Tree(key);
            } else {
                leftTree.insert(key);
            }
        }
    }

    public Collection<Integer> extractValues() {
        final Collection<Integer> extractedValues = new LinkedList<Integer>();
        extractValues(extractedValues);
        return extractedValues;
    }

    private void extractValues(final Collection<Integer> values) {
        if (leftTree != null) {
            leftTree.extractValues(values);
        }
        values.add(key);

        if (rightTree != null) {
            rightTree.extractValues(values);
        }
    }
}
