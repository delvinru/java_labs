package com.delvin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void testInsert() {
        BST<String> tree = new BST<>();
        tree.insert("data_1");
        tree.insert("data_2");
        tree.insert("data_3");
        tree.insert("data_4");
        assertEquals("data_1", tree.search("data_1"));
        assertEquals("data_2", tree.search("data_2"));
        assertEquals("data_3", tree.search("data_3"));
        assertEquals("data_4", tree.search("data_4"));
    }

    @Test
    public void testCountNodes_1() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 10, 7, 15, 13, 20, 9, 17, 4, 8, 1, 6, 5 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(4), tree.countNodes());
    }

    @Test
    public void testCountNodes_2() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 8, 3, 1, 6, 4, 7, 10, 14, 13 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(3), tree.countNodes());
    }

    @Test
    public void testCountNodes_3() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 5, 2, 1, 4, 3, 8 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(2), tree.countNodes());
    }

    @Test
    public void testCountNodes_4() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(7), tree.countNodes());
    }

    @Test
    public void testCountNodes_5() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 21, 7, 32, 5, 14, 27, 37, 4, 6, 12, 18, 25, 30, 34, 39, 2, 9, 24, 33 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(7), tree.countNodes());
    }

    @Test
    public void testCountNodes_6() {
        BST<Integer> tree = new BST<>();
        int[] list = new int[] { 50, 30, 55, 25, 35, 53, 60, 10, 30, 37, 62, 15 };
        for (int el : list)
            tree.insert(el);
        assertEquals(Integer.valueOf(4), tree.countNodes());
    }
}
