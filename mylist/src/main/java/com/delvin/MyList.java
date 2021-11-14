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

/**
 * <P>
 * 1. Конструктор
 * <P>
 * 2. Конструктор копирования
 * <P>
 * 3. Добавление элемента в начало списка {@link #pushFront}
 * <p>
 * 4. Добавление элемента в конец списка {@link #pushBack}
 * <p>
 * 5. Удаление элемента из начала списка {@link #popFront}
 * <p>
 * 6. Удаление элемента из конца списка {@link #popBack}
 * <p>
 * 7. Получение числа элементов в списке {@link #getSize}
 * <p>
 * 8. Очистка списка {@link #clear}
 * <p>
 * 9. При невозможности извлечения или удаления элемента должны генерироваться
 * исключения
 */
public class MyList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public MyList() {
    }

    public MyList(int n) {
        Node<T> node = head;
        for (int i = 0; i < n; i++) {
            node.next = new Node<T>();
            node = node.next;
        }
    }

    public MyList(MyList<T> list) {
        this.size = list.size;
        Node<T> listNode = list.head;
        while (listNode != null) {
            this.pushBack(listNode.data);
            listNode = listNode.next;
        }
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
        size++;
    }

    public void pushBack(T data) {
        Node<T> node = new Node<T>(data);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T popFront() throws IndexOutOfBoundsException {
        Node<T> node = head;
        if (head == null)
            throw new IndexOutOfBoundsException("empty list");
        if (head == tail) {
            head = tail = null;
            size--;
            return node.data;
        } else {
            head = null;
            head = node.next;
            size--;
            return node.data;
        }
    }

    public T popBack() throws IndexOutOfBoundsException {
        Node<T> node = tail;
        if (tail == null)
            throw new IndexOutOfBoundsException("empty list");
        if (head == tail) {
            head = tail = null;
            size--;
            return node.data;
        } else {
            Node<T> pred = this.getLast(node);
            tail = pred;
            size--;
            return node.data;
        }
    }

    private Node<T> getLast(Node<T> last) {
        Node<T> node = head;
        while (node != null && node.next != last)
            node = node.next;
        return node;
    }

    public void del(T data) throws IndexOutOfBoundsException, NoSuchElementException {
        if (head == null)
            throw new IndexOutOfBoundsException("empty list");

        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        if (head.data == data) {
            head = head.next;
            size--;
            return;
        }

        Node<T> node = head;
        while (node.next != null) {
            if (node.next.data.equals(data)) {
                if (tail == node.next)
                    tail = node;
                node.next = node.next.next;
                size--;
                return;
            }
            node = node.next;
        }
        throw new NoSuchElementException("element not found");
    }

    public void printList() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public int getSize() {
        return this.size;
    }

    public void clear() {
        size = 0;
        head = null;
        tail = null;
        System.gc();
    }
}