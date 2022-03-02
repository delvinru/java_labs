package com.delvin;

public class BTree<T extends Element<? extends Comparable<?>, ?>> {
    private int t = 2; // default degree based on wikipedia
    private Node<T> root = null;

    public BTree() {
    }

    public BTree(int t) {
        this.t = t;
    }

    public void clear() {
        this.root = null;
        System.gc();
    }

    public boolean empty() {
        return this.root == null;
    }

    public T get(T element) {
        return this.getNode(root, element);
    }

    private T getNode(Node<T> node, T element) {
        if (node == null)
            return null;
        int i = 0;
        for (i = 0; i < node.keysCount; i++) {
            if (element.compareTo(node.keys[i]) < 0)
                break;
            if (element.compareTo(node.keys[i]) == 0)
                return node.keys[i];
        }
        if (node.isLeaf)
            return null;
        else
            return this.getNode(node.childs[i], element);
    }

    public void set(T element){
        setNode(root, element);
    }

    private void setNode(Node<T> node, T element){
        if (node == null)
            return;
        int i = 0;
        for (i = 0; i < node.keysCount; i++) {
            if (element.compareTo(node.keys[i]) < 0)
                break;
            if (element.compareTo(node.keys[i]) == 0)
                node.keys[i] = element;
        }
        if (node.isLeaf)
            return;
        else
            setNode(node.childs[i], element);
    }

    public void insert(T element) {
        if (root == null) {
            root = new Node<>(t);
            root.keys[0] = element;
            root.keysCount++;
        } else {
            Node<T> oldRoot = root;
            if (root.keysCount == (2 * t - 1)) {
                Node<T> newRoot = new Node<>(t, false);
                root = newRoot;
                newRoot.childs[0] = oldRoot;
                split(newRoot, 0, oldRoot);
                insert(newRoot, element);
            } else
                insert(root, element);
        }
    }

    private void split(Node<T> x, int i, Node<T> y) {
        Node<T> z = new Node<>(t, y.isLeaf);
        z.keysCount = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.isLeaf)
            for (int j = 0; j < t; j++)
                z.childs[j] = y.childs[j + t];
        y.keysCount = t - 1;

        for (int j = x.keysCount; j > i; j--) {
            x.childs[j + 1] = x.childs[j];
            x.keys[j + 1] = x.keys[j];
        }
        x.childs[i + 1] = z;

        x.keys[i] = y.keys[t - 1];
        x.keysCount++;

        y.keys[t - 1] = null;
        for (int j = 0; j < t - 1; j++)
            y.keys[j + t] = null;
    }

    private void insert(Node<T> node, T element) {
        if (node.isLeaf) {
            node.insertKey(element);
            node.keysCount++;
        } else {
            int i = node.searchInsertPosition(element);
            Node<T> tmp = node.childs[i];
            if (tmp.keysCount == 2 * t - 1) {
                split(node, i, tmp);
                if (element.compareTo(node.keys[i]) > 0)
                    i++;
            }
            insert(node.childs[i], element);
        }
    }
}
