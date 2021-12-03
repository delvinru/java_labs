package com.delvin;

import java.util.ArrayList;
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

    // HeapSort
    // public void HeapSort(int[] arr) {
    // HeapSort(arr, 0, arr.length - 1);
    // }

    // public void HeapSort(int[] arr, int begin, int end) {
    // }

    // Merge Sort
    public void MergeSort(int[] arr) {
        MergeSort(arr, 0, arr.length - 1);
    }

    public void MergeSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            MergeSort(arr, begin, mid);
            MergeSort(arr, mid + 1, end);
            merge(arr, begin, mid, end);
        }
    }

    public void merge(int[] arr, int begin, int mid, int end) {
        int n1 = mid - begin + 1;
        int n2 = end - mid;

        int[] L = new int[n1];
        int[] M = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[begin + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[mid + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = begin;
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }

    // MergeSort for Object
    public <T extends Comparable<T>> void MergeSort(T[] arr) {
        MergeSort(arr, 0, arr.length - 1);
    }

    public <T extends Comparable<T>> void MergeSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            MergeSort(arr, begin, mid);
            MergeSort(arr, mid + 1, end);
            merge(arr, begin, mid, end);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void merge(T[] arr, int begin, int mid, int end) {
        int n1 = mid - begin + 1;
        int n2 = end - mid;

        Object[] L = new Object[n1];
        Object[] M = new Object[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[begin + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[mid + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = begin;
        while (i < n1 && j < n2) {
            if (((T) L[i]).compareTo((T) M[j]) <= 0) {
                arr[k] = (T) L[i];
                i++;
            } else {
                arr[k] = (T) M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = (T) L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = (T) M[j];
            j++;
            k++;
        }
    }

    // MergeSort for List
    public <T extends Comparable<? super T>> void MergeSort(List<T> list) {
        MergeSort(list, 0, list.size() - 1);
    }

    public <T extends Comparable<? super T>> void MergeSort(List<T> list, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            MergeSort(list, begin, mid);
            MergeSort(list, mid + 1, end);
            merge(list, begin, mid, end);
        }
    }

    public <T extends Comparable<? super T>> void merge(List<T> list, int begin, int mid, int end) {
        int n1 = mid - begin + 1;
        int n2 = end - mid;

        List<T> L = new ArrayList<>();
        List<T> M = new ArrayList<>();

        for (int i = 0; i < n1; i++)
            L.set(i, list.get(begin + i));
        for (int j = 0; j < n2; j++)
            M.set(j, list.get(mid + 1 + j));

        int i, j, k;
        i = 0;
        j = 0;
        k = begin;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(M.get(j)) <= 0) {
                list.set(k, L.get(i));
                i++;
            } else {
                list.set(k, M.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, M.get(j));
            j++;
            k++;
        }
    }
}
