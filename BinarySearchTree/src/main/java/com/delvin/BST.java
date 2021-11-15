package com.delvin;

import java.util.NoSuchElementException;

/**
 * Вспомагательные структура {@link #Node}
 */
class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public Node(T d) {
        this.data = d;
        this.left = null;
        this.right = null;
    }
}

/**
 * {@code BST} - реализация BinarySearchTree
 * <p>
 * 1. Конструктор ✅ {@link #BST}
 * <p>
 * 2. Конструктор копирования ✅ {@link #cloneTree}
 * <p>
 * 3. Добавление элемента ✅ {@link #insert(T)}
 * <p>
 * 4. Поиск элемента ✅ {@link #search(T)}
 * <p>
 * 5. Удаление всех элементов ✅ {@link #clear}
 */
public class BST<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public BST(BST<T> bst) {
        this.root = cloneTree(bst.root);
    }

    private Node<T> cloneTree(Node<T> root) {
        if (root == null)
            return null;
        Node<T> node = new Node<T>(root.data);
        this.size++;
        node.left = cloneTree(root.left);
        node.right = cloneTree(root.right);
        return node;
    }

    public void insert(T data) {
        this.root = insertRecursive(this.root, data);
    }

    private Node<T> insertRecursive(Node<T> root, T data) {
        if (root == null) {
            root = new Node<T>(data);
            this.size++;
            return root;
        }
        if (data.compareTo(root.data) < 0)
            root.left = insertRecursive(root.left, data);
        else
            root.right = insertRecursive(root.right, data); // if values equals or less push them in right branch
        return root;
    }

    public T search(T data) {
        return this.searchRecursive(this.root, data).data;
    }

    private Node<T> searchRecursive(Node<T> root, T data) throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException("No element in BST");

        // try use tail recursion in java, it work only on newer version jvm
        return data.compareTo(root.data) == 0 ? root
                : data.compareTo(root.data) < 0 ? searchRecursive(root.left, data) : searchRecursive(root.right, data);
    }

    public void clear() {
        this.root = this.clearTree(this.root);
    }

    private Node<T> clearTree(Node<T> root) {
        if (root == null) {
            return null;
        }
        root.left = clearTree(root.left);
        root.right = clearTree(root.right);
        root = null;
        this.size = 0;
        return root;
    }

    public int size() {
        return this.size;
    }
}