package com.delvin;

public class MyVector<T> {
    private Object[] list;
    private int size = 0;
    private int maxSize = 64;

    public MyVector() {
        this.list = new Object[this.maxSize];
    }

    public void push(T data) {
        if (size == this.list.length)
            expand();
        this.list[this.size++] = data;
    }

    public void del(T data) {
        Object[] newList = new Object[this.maxSize];
        int newSize = 0;
        for (int i = 0; i < this.size; i++) {
            // Skip empty elements
            if (this.list[i] == null)
                continue;
            // Skip element for remove
            if (data.equals(this.list[i]))
                continue;
            newList[newSize++] = this.list[i];
        }
        this.list = newList;
        this.size = newSize;
    }

    private void expand() {
        Object[] tmp = new Object[2 * this.size + 10];
        for (int i = 0; i < this.size; i++)
            tmp[i] = this.list[i];
        this.list = tmp;
        this.maxSize = 2 * this.size + 10;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < this.size; i++)
            buf.append(this.list[i] + "\n");
        return buf.deleteCharAt(buf.length() - 1).toString();
    }
}