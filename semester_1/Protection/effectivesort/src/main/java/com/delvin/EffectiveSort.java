package com.delvin;

import java.util.List;

public class EffectiveSort {
    // QuickSort with simple int array
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }

    public void QuickSort(int[] arr) {
        QuickSort(arr, 0, arr.length - 1);
    }

    public void QuickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIdx = partition(arr, begin, end);

            QuickSort(arr, begin, partitionIdx - 1);
            QuickSort(arr, partitionIdx + 1, end);
        }
    }

    // Quick sort with Object type
    private <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private <T extends Comparable<T>> int partition(T[] arr, int begin, int end) {
        T pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }

    public <T extends Comparable<T>> void QuickSort(T[] arr) {
        QuickSort(arr, 0, arr.length - 1);
    }

    public <T extends Comparable<T>> void QuickSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIdx = partition(arr, begin, end);

            QuickSort(arr, begin, partitionIdx - 1);
            QuickSort(arr, partitionIdx + 1, end);
        }
    }

    // Quick sort with Template value
    private <T extends Comparable<? super T>> void swap(List<T> list, int i, int j) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    private <T extends Comparable<? super T>> int partition(List<T> list, int begin, int end) {
        T pivot = list.get(end);
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, end);
        return i + 1;
    }

    public <T extends Comparable<? super T>> void QuickSort(List<T> list) {
        QuickSort(list, 0, list.size() - 1);
    }

    public <T extends Comparable<? super T>> void QuickSort(List<T> list, int begin, int end) {
        if (begin < end) {
            int partitionIdx = partition(list, begin, end);

            QuickSort(list, begin, partitionIdx - 1);
            QuickSort(list, partitionIdx + 1, end);
        }
    }
}
