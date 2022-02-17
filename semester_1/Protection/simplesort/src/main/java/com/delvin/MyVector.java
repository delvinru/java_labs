package com.delvin;

import java.util.NoSuchElementException;

public class MyVector<T extends Comparable<T>> extends SimpleSort {
    private Comparable[] list;
    private int size = 0;
    private int maxSize = 64;

    public boolean ASC = true;
    public boolean DESC = false;

    public MyVector() {
        this.list = new Comparable[this.maxSize];
    }

    public MyVector(MyVector<T> vec) {
        this.list = vec.list.clone();
        this.size = vec.size;
        this.maxSize = vec.maxSize;
    }

    public MyVector(int n) throws NegativeArraySizeException {
        if (n < 0)
            throw new NegativeArraySizeException("Negative size");

        this.maxSize = n * 2 + 10;
        this.size = 0;
        this.list = new Comparable[this.maxSize];
    }

    public void push(T data) {
        if (size == this.list.length)
            expand();
        this.list[this.size++] = data;
    }

    @SuppressWarnings("unchecked")
    public T pop() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException("Empty array");
        Object tmp = this.list[this.size - 1];
        this.list[this.size - 1] = null;
        this.size--;
        return (T) tmp;
    }

    public void insertAt(int idx, T el) throws IndexOutOfBoundsException {
        if (this.size == this.maxSize)
            expand();
        if (idx < 0 || idx > this.maxSize - 1)
            throw new IndexOutOfBoundsException("Incorrect index");
        this.list[idx] = el;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx > this.size - 1)
            throw new IndexOutOfBoundsException("Incorrect index");
        Object tmp = this.list[idx];
        this.list[idx] = null;
        this.size--;
        return (T) tmp;
    }

    @SuppressWarnings("unchecked")
    public T indexOf(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx > this.maxSize - 1)
            throw new IndexOutOfBoundsException("Incorrect index");
        return (T) this.list[idx];
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        System.out.println("Expand array:" + this.size);
        Comparable<T>[] tmp = new Comparable[2 * this.size + 10];
        for (int i = 0; i < this.size; i++)
            tmp[i] = this.list[i];
        this.list = tmp;
        this.maxSize = 2 * this.size + 10;
    }

    @SuppressWarnings("unchecked")
    public void bubbleSort(boolean order) {
        if (order)
            this.BubbleSortAsc(list);
        else
            this.BubbleSortDesc(list);
    }

    public void clear() {
        for (int i = 0; i < this.size; i++)
            this.list[i] = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public int max() {
        return this.maxSize;
    }
}