package ru.unn.agile.treesort.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeTest {
    @Test
    public void testTreeNodeCreate() {
        TreeNode node = new TreeNode(10);
        assertEquals(Integer.valueOf(10), node.getKey());
    }

    @Test
    public void testEmptyTreeLeftNode() {
        TreeNode node = new TreeNode(10);
        assertNull(node.getLeftNode());
    }

    @Test
    public void testEmptyTreeRightNode() {
        TreeNode node = new TreeNode(10);
        assertNull(node.getRightNode());
    }

    @Test
    public void testLeftNode() {
        TreeNode node = new TreeNode(20);
        TreeNode leftNode = new TreeNode(10);
        node.setLeftNode(leftNode);

        assertEquals(leftNode.getKey(), node.getLeftNode().getKey());
    }

    @Test
    public void testRightNode() {
        TreeNode node = new TreeNode(20);
        TreeNode rightNode = new TreeNode(30);
        node.setRightNode(rightNode);

        assertEquals(rightNode.getKey(), node.getRightNode().getKey());
    }

    @Test
    public void testCreateNullKey() {
        boolean exceptionTrowed = false;
        try {
            TreeNode rootNode = new TreeNode(null);
        } catch (IllegalArgumentException e) {
            exceptionTrowed = true;
        } finally {
            assertTrue(exceptionTrowed);
        }
    }
}
