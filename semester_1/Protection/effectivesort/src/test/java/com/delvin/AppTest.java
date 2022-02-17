package com.delvin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void Test1() {
        Task<Integer> task = new Task<Integer>();
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 6, 7, 8, 9 };
        assertEquals(Integer.valueOf(6), task.count(arr));
    }

    @Test
    public void Test2() {
        Task<Integer> task = new Task<Integer>();
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertEquals(null, task.count(arr));
    }

    @Test
    public void Test3() {
        Task<Integer> task = new Task<Integer>();
        Integer[] arr = new Integer[] { 10, 10, 20, 20, 30, 40, 50, 50, 80, 80, 3241, 1234, 9999, 9999, 9999 };
        assertEquals(Integer.valueOf(9999), task.count(arr));
    }

    @Test
    public void Test4() {
        Task<String> task = new Task<String>();
        String[] arr = { "hello", "world", "whoami", "ps", "aux", "root", "sudo", "doas", "doas" };
        assertEquals("doas", task.count(arr));
    }
}
