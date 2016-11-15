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
    public void testInsertLessValueAndNotNullLeftNode() {
        TreeNode rootNode = new TreeNode(10);
        TreeNode lessNode = new TreeNode(5);

        rootNode.insertNode(lessNode);
        assertNotNull(rootNode.getLeftNode());
    }

    @Test
    public void testInsertLessValue() {
        TreeNode rootNode = new TreeNode(10);
        TreeNode lessNode = new TreeNode(5);

        rootNode.insertNode(lessNode);
        assertEquals(lessNode.getKey(), rootNode.getLeftNode().getKey());
    }

    @Test
    public void testInsertGreateValueAndNotNullRightNode() {
        TreeNode rootNode = new TreeNode(10);
        TreeNode greateNode = new TreeNode(20);

        rootNode.insertNode(greateNode);
        assertNotNull(rootNode.getRightNode());
    }

    @Test
    public void testInsertGreateValue() {
        TreeNode rootNode = new TreeNode(10);
        TreeNode greateNode = new TreeNode(20);

        rootNode.insertNode(greateNode);
        assertEquals(greateNode.getKey(), rootNode.getRightNode().getKey());
    }

    @Test
    public void testInsertEqualValue() {
        TreeNode rootNode = new TreeNode(10);
        TreeNode equalNode = new TreeNode(10);

        rootNode.insertNode(equalNode);
        assertEquals(equalNode.getKey(), rootNode.getRightNode().getKey());
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

    @Test
    public void testInsertNullNode() {
        TreeNode rootNode = new TreeNode(10);

        boolean exceptionTrowed = false;
        try {
            rootNode.insertNode(null);

        } catch (IllegalArgumentException e) {
            exceptionTrowed = true;
        } finally {
            assertTrue(exceptionTrowed);
        }
    }
}
