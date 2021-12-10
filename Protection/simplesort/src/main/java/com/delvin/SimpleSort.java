package com.delvin;

public class SimpleSort {
    public <T extends Comparable<T>> void BubbleSortAsc(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] == null || arr[j + 1] == null)
                    continue;
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public <T extends Comparable<T>> void BubbleSortDesc(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] == null || arr[j + 1] == null)
                    continue;
                if (arr[j].compareTo(arr[j + 1]) < 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
