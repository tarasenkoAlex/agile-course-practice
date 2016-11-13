package ru.unn.agile.TreeSort.Model;

import java.util.ArrayList;
import java.util.Collection;

public class TreeSort {
    public Collection<Integer> sort(final Collection<Integer> inputCollection) {
        Tree sortingTree = null;
        for (Integer val : inputCollection) {
            if (sortingTree == null) {
                sortingTree = new Tree(val);
            } else {
                sortingTree.insert(val);
            }
        }
        if (sortingTree == null) {
            return new ArrayList<Integer>();
        } else {
            return sortingTree.extractValues();
        }
    }
}
