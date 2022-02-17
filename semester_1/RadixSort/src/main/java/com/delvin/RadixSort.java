package com.delvin;

public class RadixSort {
    private int max(int[] arr, int begin, int end) {
        int max = -Integer.MAX_VALUE;
        for (int i = begin; i <= end; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    public void CountSort(int[] arr) {
        CountSort(arr, 0, arr.length - 1);
    }

    public void CountSort(int[] arr, int begin, int end) {
        int max = max(arr, begin, end);
        int[] countingArray = new int[max + 1];
        for (int i = begin; i <= end; i++)
            countingArray[arr[i]]++;
        for (int i = 1; i < max + 1; i++)
            countingArray[i] += countingArray[i - 1];

        int[] output = new int[end - begin + 1];
        for (int i = begin; i <= end; i++) {
            output[countingArray[arr[i]] - 1] = arr[i];
            countingArray[arr[i]]--;
        }
        System.arraycopy(output, 0, arr, begin, end - begin + 1);
    }
}