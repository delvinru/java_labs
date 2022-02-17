package com.delvin;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AppTest {
    @Test
    public void BubbleSort_1() {
        int[] arr = new int[] { 10, 0, 1, -2, 4, 78 };
        SimpleSort.BubbleSort(arr);
        assertArrayEquals(new int[] { -2, 0, 1, 4, 10, 78 }, arr);
    }

    @Test
    public void BubbleSort_2() {
        int[] arr = new int[] { -1, -3, -20, 20, 10, 5 };
        SimpleSort.BubbleSort(arr, 1, 5);
        assertArrayEquals(new int[] { -1, -20, -3, 10, 20, 5 }, arr);
    }

    @Test
    public void BubbleSort_3() {
        String[] arr = { "abc", "whoami", "ps", "hello" };
        SimpleSort.BubbleSort(arr);
        assertArrayEquals(new String[] { "abc", "hello", "ps", "whoami" }, arr);
    }

    @Test
    public void BubbleSort_4() {
        String[] arr = { "g", "a", "z", "x", "m", "q" };
        String[] test = { "g", "a", "m", "q", "x", "z" };
        SimpleSort.BubbleSort(arr, 2, arr.length);
        assertArrayEquals(test, arr);
    }

    @Test
    public void BubbleSort_5() {
        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(10);
        list.add(-1);
        list.add(4);
        list.add(-2);
        SimpleSort.BubbleSort(list);
        assertArrayEquals(new Object[] { -2, -1, 4, 10, 20 }, list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void BubbleSort_6() {
        Character[] arr = { 'd', 'b', 'a', 'c' };
        SimpleSort.BubbleSort(arr, 10, -1);
    }

    @Test(expected = InvalidParameterException.class)
    public void BubbleSort_7() {
        Character[] arr = { 'd', 'b', 'a', 'c' };
        SimpleSort.BubbleSort(arr, 3, 1);
    }

    @Test
    public void InsertionSort_1() {
        int[] arr = new int[] { 0x20, 0xA, 0x7, 0x1C, 0x1B };
        SimpleSort.InsertionSort(arr);
        assertArrayEquals(new int[] { 0x7, 0xA, 0x1B, 0x1C, 0x20 }, arr);
    }

    @Test
    public void InsertionSort_2() {
        int[] arr = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        SimpleSort.InsertionSort(arr, 2, 8);
        assertArrayEquals(new int[] { 10, 9, 3, 4, 5, 6, 7, 8, 2, 1 }, arr);
    }

    @Test
    public void InsertionSort_3() {
        Character[] arr = { 'd', 'b', 'a', 'c' };
        SimpleSort.InsertionSort(arr);
        assertArrayEquals(new Character[] { 'a', 'b', 'c', 'd' }, arr);
    }

    @Test
    public void InsertionSort_4() {
        Character[] arr = { 'd', 'b', 'a', 'c', 'x', 'g', 'p' };
        SimpleSort.InsertionSort(arr, 2, 6);
        assertArrayEquals(new Character[] { 'd', 'b', 'a', 'c', 'g', 'x', 'p' }, arr);
    }

    @Test
    public void InsertionSort_5() {
        class Cat implements Comparable<Cat> {
            private int age = 0;

            public Cat(int age) {
                this.age = age;
            }

            @Override
            public int compareTo(Cat cat) {
                return this.age > cat.age ? 1 : this.age < cat.age ? -1 : 0;
            }

            @Override
            public boolean equals(Object cat) {
                if (this.age == ((Cat) cat).age)
                    return true;
                return false;
            }
        }

        List<Cat> list = new ArrayList<>();
        list.add(new Cat(5));
        list.add(new Cat(1));
        list.add(new Cat(4));
        list.add(new Cat(3));
        SimpleSort.InsertionSort(list);

        List<Cat> test = new ArrayList<>();
        test.add(new Cat(1));
        test.add(new Cat(3));
        test.add(new Cat(4));
        test.add(new Cat(5));

        assertArrayEquals(test.toArray(), list.toArray());
    }

    @Test
    public void SelectionSort_1() {
        int[] arr = new int[] { 8, 5, 2, 9, 7, 6, 3, 4, 1, 0 };
        SimpleSort.SelectionSort(arr);
        assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, arr);
    }

    @Test
    public void SelectionSort_2() {
        int[] arr = new int[] { 8, 5, 2, 9, 7, 6, 3, 4, 1, 0 };
        SimpleSort.SelectionSort(arr, 1, 6);
        assertArrayEquals(new int[] { 8, 2, 5, 6, 7, 9, 3, 4, 1, 0 }, arr);
    }

    @Test
    public void SelectionSort_3() {
        Character[] arr = { 'd', 'b', 'a', 'c' };
        SimpleSort.SelectionSort(arr);
        assertArrayEquals(new Character[] { 'a', 'b', 'c', 'd' }, arr);
    }

    @Test
    public void SelectionSort_4() {
        Character[] arr = { 'd', 'b', 'a', 'c', 'x', 'g', 'p' };
        SimpleSort.SelectionSort(arr, 2, 6);
        assertArrayEquals(new Character[] { 'd', 'b', 'a', 'c', 'g', 'x', 'p' }, arr);
    }

    @Test
    public void SelectionSort_5() {
        class Cat implements Comparable<Cat> {
            private int age = 0;

            public Cat(int age) {
                this.age = age;
            }

            @Override
            public int compareTo(Cat cat) {
                return this.age > cat.age ? 1 : this.age < cat.age ? -1 : 0;
            }

            @Override
            public boolean equals(Object cat) {
                if (this.age == ((Cat) cat).age)
                    return true;
                return false;
            }
        }

        List<Cat> list = new ArrayList<>();
        list.add(new Cat(5));
        list.add(new Cat(1));
        list.add(new Cat(4));
        list.add(new Cat(3));
        SimpleSort.InsertionSort(list);

        List<Cat> test = new ArrayList<>();
        test.add(new Cat(1));
        test.add(new Cat(3));
        test.add(new Cat(4));
        test.add(new Cat(5));

        assertArrayEquals(test.toArray(), list.toArray());
    }
}
