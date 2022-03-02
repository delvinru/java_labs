package com.delvin;

import java.util.Arrays;

/**
 * 1. Для поиска ключа в структуре использовать двоичный поиск:
 * {@link #searchInsertPosition}
 * <br>
 */
public final class Node<T extends Element<? extends Comparable<?>, ?>> {
    public T[] keys = null;
    public int keysCount = 0;
    public Node<T>[] childs = null;
    public boolean isLeaf = true;

    /**
     * Initialize new leaf
     * 
     * @param t - tree's degree
     */
    @SuppressWarnings("unchecked")
    public Node(int t) {
        keys = (T[]) new Element[2 * t - 1];
        childs = new Node[2 * t];
    }

    @SuppressWarnings("unchecked")
    public Node(int t, boolean isLeaf) {
        keys = (T[]) new Element[2 * t - 1];
        childs = new Node[2 * t];
        this.isLeaf = isLeaf;
    }

    public int searchInsertPosition(T element) {
        int low = 0;
        int high = keysCount - 1;
        int mid = (high + low) / 2;
        while (low <= high) {
            mid = (high + low) / 2;
            if (keys[mid].compareTo(element) < 0)
                low = mid + 1;
            else if (keys[mid].compareTo(element) > 0)
                high = mid - 1;
            else
                break;
        }
        if (element.compareTo(keys[mid]) <= 0)
            return mid;
        else
            return mid + 1;
    }

    public void insertKey(T element) {
        int i = searchInsertPosition(element);
        for (int j = keysCount - 1; j >= i; j--)
            keys[j + 1] = keys[j];
        keys[i] = element;
    }
}
