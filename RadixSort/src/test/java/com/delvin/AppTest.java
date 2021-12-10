package com.delvin;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void CountSortTest_1() {
        int[] arr = new int[] { 10, 9, 8, 7, 6, 1, 2, 3, 4, 5 };
        RadixSort sort = new RadixSort();
        sort.CountSort(arr);
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, arr);
    }

    @Test
    public void CountSortTest_2() {
        int[] arr = new int[] { 10, 9, 8, 7, 6, 1, 2, 3, 4, 5 };
        RadixSort sort = new RadixSort();
        sort.CountSort(arr, 0, 4);
        assertArrayEquals(new int[] { 6, 7, 8, 9, 10, 1, 2, 3, 4, 5 }, arr);
    }
}
