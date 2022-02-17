package com.delvin;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AppTest {
    @Test
    public void QuickSortTest_1() {
        int[] arr = new int[] { 10, 9, 3, 5, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.QuickSort(arr);
        assertArrayEquals(new int[] { 2, 3, 5, 7, 8, 9, 10 }, arr);
    }

    @Test
    public void QuickSortTest_2() {
        Integer[] arr = new Integer[] { 9, 3, 65, 2, 4, 6 };
        EffectiveSort sort = new EffectiveSort();
        sort.QuickSort(arr);
        assertArrayEquals(new Integer[] { 2, 3, 4, 6, 9, 65 }, arr);
    }

    @Test
    public void QuickSortTest_3() {
        String[] arr = new String[] { "hello", "world", "bad", "test" };
        EffectiveSort sort = new EffectiveSort();
        sort.QuickSort(arr);
        assertArrayEquals(new String[] { "bad", "hello", "test", "world" }, arr);
    }

    @Test
    public void QuickSortTest_4() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.QuickSort(arr, 2, 4);
        assertArrayEquals(new int[] { 10, 9, 2, 3, 5, 8, 7 }, arr);
    }

    @Test
    public void QuickSortTest_5() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        List<Integer> list = new ArrayList<>();

        for (int el : arr)
            list.add(el);

        List<Integer> test = new ArrayList<>();
        EffectiveSort sort = new EffectiveSort();

        sort.QuickSort(arr);
        for (int el : arr)
            test.add(el);

        sort.QuickSort(list);
        assertArrayEquals(test.toArray(), list.toArray());
    }

    @Test
    public void HeapSortTest_1() {
        int[] arr = new int[] { 10, 9, 3, 5, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.HeapSort(arr);
        assertArrayEquals(new int[] { 2, 3, 5, 7, 8, 9, 10 }, arr);
    }

    @Test
    public void HeapSortTest_2() {
        Integer[] arr = new Integer[] { 9, 3, 65, 2, 4, 6 };
        EffectiveSort sort = new EffectiveSort();
        sort.HeapSort(arr);
        assertArrayEquals(new Integer[] { 2, 3, 4, 6, 9, 65 }, arr);
    }

    @Test
    public void HeapSortTest_3() {
        String[] arr = new String[] { "hello", "world", "bad", "test" };
        EffectiveSort sort = new EffectiveSort();
        sort.HeapSort(arr);
        assertArrayEquals(new String[] { "bad", "hello", "test", "world" }, arr);
    }

    @Test
    public void HeapSortTest_4() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.HeapSort(arr, 2, 5);
        assertArrayEquals(new int[] { 10, 9, 2, 3, 5, 8, 7 }, arr);
    }

    @Test
    public void HeapSortTest_5() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        List<Integer> list = new ArrayList<>();

        for (int el : arr)
            list.add(el);

        List<Integer> test = new ArrayList<>();
        EffectiveSort sort = new EffectiveSort();

        sort.HeapSort(arr);
        for (int el : arr)
            test.add(el);

        sort.QuickSort(list);
        assertArrayEquals(test.toArray(), list.toArray());
    }

    @Test
    public void MergeSortTest_1() {
        int[] arr = new int[] { 10, 9, 3, 5, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.MergeSort(arr);
        assertArrayEquals(new int[] { 2, 3, 5, 7, 8, 9, 10 }, arr);
    }

    @Test
    public void MergeSortTest_2() {
        Integer[] arr = new Integer[] { 9, 3, 65, 2, 4, 6 };
        EffectiveSort sort = new EffectiveSort();
        sort.MergeSort(arr);
        assertArrayEquals(new Integer[] { 2, 3, 4, 6, 9, 65 }, arr);
    }

    @Test
    public void MergeSortTest_3() {
        String[] arr = new String[] { "hello", "world", "bad", "test" };
        EffectiveSort sort = new EffectiveSort();
        sort.MergeSort(arr);
        assertArrayEquals(new String[] { "bad", "hello", "test", "world" }, arr);
    }

    @Test
    public void MergeSortTest_4() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.MergeSort(arr, 2, 4);
        assertArrayEquals(new int[] { 10, 9, 2, 3, 5, 8, 7 }, arr);
    }

    @Test
    public void MergeSortTest_5() {
        int[] arr = new int[] { 10, 9, 5, 3, 2, 8, 7 };
        List<Integer> list = new ArrayList<>();

        for (int el : arr)
            list.add(el);

        List<Integer> test = new ArrayList<>();
        EffectiveSort sort = new EffectiveSort();

        sort.MergeSort(arr);
        for (int el : arr)
            test.add(el);

        sort.QuickSort(list);
        assertArrayEquals(test.toArray(), list.toArray());
    }
}
