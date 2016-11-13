package ru.unn.agile.TreeSort.Model;

/**
 * Created by Pavel on 13.11.16.
 */
public class Tree {
    private Integer keyValue;
    private Tree leftTree;
    private Tree rightTree;

    public Tree(Integer key) {
        keyValue = key;
    }

    public Integer getKey() {
        return keyValue;
    }

    public Tree getLeft() {
        return leftTree;
    }

    public Tree getRight() {
        return rightTree;
    }

    public void insert(Integer key) {
        int cmpResult = keyValue.compareTo(key);

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
}
