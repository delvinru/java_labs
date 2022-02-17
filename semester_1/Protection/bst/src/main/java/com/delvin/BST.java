package com.delvin;

import java.util.NoSuchElementException;

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

public class BST<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
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

    /**
     * Данный метод работает исходя из следующего определения:
     * Полный узел дерева - узел, который имеет двое детей.
     */
    public Integer countNodes() {
        return this.countNodes(root) - 1;
    }

    private Integer countNodes(Node<T> root) {
        if (root == null)
            return 0;
        if (root.left != null && root.right != null) {
            return countNodes(root.left) + countNodes(root.right);
        }
        return 1;
    }
}
