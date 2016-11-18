package ru.unn.agile.treesort.model;

import java.util.Collection;

public class TreeSort {
    public Collection<Integer> sort(final Collection<Integer> inputCollection) {
        Tree sortingTree = new Tree();
        for (Integer val : inputCollection) {
            sortingTree.insert(val);
        }
        return sortingTree.extractValues();
    }
}
