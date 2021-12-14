package com.delvin;

import java.util.HashMap;
import java.util.HashSet;

public class Task<T extends Comparable<T>> extends EffectiveSort {
    public T max(HashMap<T, Integer> map) {
        int maxValue = -Integer.MAX_VALUE;
        T maxElement = null;
        for (T key : map.keySet()) {
            if (map.get(key) > maxValue) {
                maxValue = map.get(key);
                maxElement = key;
            }
        }
        return maxElement;
    }

    public T count(T[] arr) {
        this.QuickSort(arr);
        HashMap<T, Integer> map = new HashMap<>();
        for (T key : arr) {
            if (!map.containsKey(key))
                map.put(key, 0);
            map.put(key, map.get(key) + 1);
        }
        T max = this.max(map);
        return (new HashSet<Integer>(map.values())).size() == 1 ? null : max;
    }
}
