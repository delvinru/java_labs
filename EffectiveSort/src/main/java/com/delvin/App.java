package com.delvin;

public class App {
    public static void main(String[] args) {
        int[] arr = new int[] { 10, 9, 3, 5, 2, 8, 7 };
        EffectiveSort sort = new EffectiveSort();
        sort.MergeSort(arr);
        System.out.println(arr);
    }
}
