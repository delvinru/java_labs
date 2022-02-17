package com.delvin;

import java.util.NoSuchElementException;

class Node<T> {
    public T data;
    public Node<T> next = null;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }
}

public class MyList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    public MyList() {
    }

    public void pushFront(T data) {
        Node<T> node = new Node<T>(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void del(T data) throws IndexOutOfBoundsException, NoSuchElementException {
        if (head == null)
            throw new IndexOutOfBoundsException("empty list");

        if (head == tail) {
            if (!head.data.equals(data))
                throw new NoSuchElementException();
            head = null;
            tail = null;
            return;
        }

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node<T> node = head;
        while (node.next != null) {
            if (node.next.data.equals(data)) {
                if (tail == node.next)
                    tail = node;
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
        throw new NoSuchElementException("element not found");
    }
}
