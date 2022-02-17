package com.delvin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void Test1() {
        MyVector<Integer> vec = new MyVector<>();
        int[] arr = new int[] { 10, 8, 6, 4, 2, 1 };
        for (int el : arr)
            vec.push(el);

        vec.bubbleSort(vec.ASC);

        Integer[] test = new Integer[] { 1, 2, 4, 6, 8, 10 };
        for (int i = 0; i < vec.size(); i++)
            assertEquals(test[i], vec.indexOf(i));
    }

    @Test
    public void Test2() {
        MyVector<Integer> vec = new MyVector<>();
        int[] arr = new int[] { 1, 2, 4, 6, 8, 10 };
        for (int el : arr)
            vec.push(el);

        vec.bubbleSort(vec.DESC);

        Integer[] test = new Integer[] { 10, 8, 6, 4, 2, 1 };
        for (int i = 0; i < vec.size(); i++)
            assertEquals(test[i], vec.indexOf(i));
    }
}
