package ru.unn.agile.treesort.model;

public class TreeNode {
    private final Integer key;
    private TreeNode leftTreeNode;
    private TreeNode rightTreeNode;

    public TreeNode(final Integer key) {
        if (null == key) {
            throw new IllegalArgumentException("NULL not supported for TreeNode key");
        }
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public TreeNode getLeftNode() {
        return leftTreeNode;
    }

    public TreeNode getRightNode() {
        return rightTreeNode;
    }

    public void insertNode(final TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("NULL not supported for TreeNode node value");
        }

        int cmpResult = node.getKey().compareTo(key);
        if (cmpResult < 0) { // node.key < key
            if (leftTreeNode == null) {
                leftTreeNode = node;
            } else {
                leftTreeNode.insertNode(node);
            }
        } else {
            if (rightTreeNode == null) {
                rightTreeNode = node;
            } else {
                rightTreeNode.insertNode(node);
            }
        }
    }
}
