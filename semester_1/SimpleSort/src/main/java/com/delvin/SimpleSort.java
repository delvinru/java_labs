package com.delvin;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * {@link #SimpleSort} - реализация базовых алгоритмов сортировки.
 * 
 * <p>
 * 1. BubbleSort ✅ {@link #BubbleSort}
 * <p>
 * 2. InsertionSort ✅ {@link #InsertionSort}
 * <p>
 * 3. SelectionSort ✅ {@link #SelectionSort}
 * <p>
 * a. Сортировка массива целых чисел ✅ {@link #BubbleSort(int)}
 * {@link #InsertionSort(int)} {@link #SelectionSort(int)}
 * <p>
 * b. Сортировка любой подпоследовательности массива ✅
 * {@link #BubbleSort(int, int, int)} {@link #InsertionSort(int, int, int)} {@link #SelectionSort(int, int, int)}
 * <p>
 * c. Сортировка массивов с любыми типами данных ✅
 * <p>
 * d. Сортировка контейнера по выбору ✅
 * {@link #BubbleSort(List)} {@link #InsertionSort(List)} {@link #SelectionSort(List)}
 */
public class SimpleSort {
    /**
     * Simple sort integer array
     * 
     * @param arr - integer array
     */
    public static void BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Simple sort integer subarray
     * 
     * @param arr   - integer array
     * @param begin - start index
     * @param end   - end index
     */
    public static void BubbleSort(int[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int i = begin; i < end; i++) {
            for (int j = begin; j < end - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Simple sort any object array
     * 
     * @param arr - Object array
     */
    public static <T extends Comparable<T>> void BubbleSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Simple sort object subarray
     * 
     * @param arr   - Object array
     * @param begin - start index
     * @param end   - end index
     */
    public static <T extends Comparable<T>> void BubbleSort(T[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int i = begin; i < end; i++) {
            for (int j = begin; j < end - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Simple sort collection any
     * 
     * @param arr - collection
     */
    public static <T extends Comparable<? super T>> void BubbleSort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
    }

    public static void InsertionSort(int[] arr) {
        for (int j = 1; j < arr.length; j++) {
            int key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public static void InsertionSort(int[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int j = begin + 1; j < end; j++) {
            int key = arr[j];
            int i = j - 1;
            while (i >= begin && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] arr) {
        for (int j = 1; j < arr.length; j++) {
            T key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i].compareTo(key) > 0) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int j = begin + 1; j < end; j++) {
            T key = arr[j];
            int i = j - 1;
            while (i >= begin && arr[i].compareTo(key) > 0) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public static <T extends Comparable<? super T>> void InsertionSort(List<T> list) {
        for (int j = 1; j < list.size(); j++) {
            T key = list.get(j);
            int i = j - 1;
            while (i >= 0 && list.get(i).compareTo(key) > 0) {
                list.set(i + 1, list.get(i));
                i--;
            }
            list.set(i + 1, key);
        }
    }

    public static void SelectionSort(int[] arr) {
        for (int min = 0; min < arr.length; min++) {
            int least = min;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[least])
                    least = j;
            }
            int tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }

    public static void SelectionSort(int[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int min = begin; min < end; min++) {
            int least = min;
            for (int j = min + 1; j < end; j++) {
                if (arr[j] < arr[least])
                    least = j;
            }
            int tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }

    public static <T extends Comparable<T>> void SelectionSort(T[] arr) {
        for (int min = 0; min < arr.length; min++) {
            int least = min;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[least]) < 0)
                    least = j;
            }
            T tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }

    public static <T extends Comparable<T>> void SelectionSort(T[] arr, int begin, int end)
            throws InvalidParameterException, IndexOutOfBoundsException {
        if (begin < 0 || begin > arr.length || end < 0 || end > arr.length)
            throw new IndexOutOfBoundsException("Invalid parametrs");
        if (begin > end)
            throw new InvalidParameterException("Begin should be less than end");

        for (int min = begin; min < end; min++) {
            int least = min;
            for (int j = min + 1; j < end; j++) {
                if (arr[j].compareTo(arr[least]) < 0)
                    least = j;
            }
            T tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }

    public static <T extends Comparable<? super T>> void SelectionSort(List<T> list) {
        for (int min = 0; min < list.size(); min++) {
            int least = min;
            for (int j = min + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(least)) < 0)
                    least = j;
            }
            T tmp = list.get(min);
            list.set(min, list.get(least));
            list.set(least, tmp);
        }
    }
}
