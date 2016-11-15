package ru.unn.agile.treesort.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    @Test
    public void testNodeCreate() {
        TreeNode node = new TreeNode(10);
        assertEquals(Integer.valueOf(10), node.getKey());
    }
}
