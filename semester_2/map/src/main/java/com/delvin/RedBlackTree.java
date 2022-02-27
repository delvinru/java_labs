package com.delvin;

import java.security.KeyException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

public class RedBlackTree<K extends Comparable<K>, V> implements Iterable<Element<K, V>> {
    private Node<K, V> root = null;
    private Comparator<K> comparator = Comparable::compareTo;

    public RedBlackTree() {
    }

    public RedBlackTree(K key, V value) {
        root = new Node<>(key, value);
    }

    public RedBlackTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public RedBlackTree(K key, V value, Comparator<K> comparator) {
        this.root = new Node<>(key, value);
        this.comparator = comparator;
    }

    public RedBlackTree(RedBlackTree<K, V> tree) {
        this.comparator = tree.comparator;
        if (tree.root == null) {
            root = null;
        } else {
            root = new Node<K, V>(tree.root.data);
            if (tree.root.leftChild != null)
                root.leftChild = new Node<K, V>(tree.root.leftChild.data, tree.root.leftChild.color);

            if (tree.root.rightChild != null)
                root.rightChild = new Node<K, V>(tree.root.rightChild.data, tree.root.rightChild.color);
        }
    }

    public RedBlackTree(RedBlackTree<K, V> tree, Comparator<K> comparator) {
        this.comparator = comparator;
        if (tree.root == null) {
            root = null;
        } else {
            root = new Node<K, V>(tree.root.data);
            if (tree.root.leftChild != null)
                root.leftChild = new Node<K, V>(tree.root.leftChild.data, tree.root.leftChild.color);

            if (tree.root.rightChild != null)
                root.rightChild = new Node<K, V>(tree.root.rightChild.data, tree.root.rightChild.color);
        }
    }

    public void insertNode(Node<K, V> insertNode) {
        if (root == null) {
            root = insertNode;
            return;
        }

        Node<K, V> node = root;
        Node<K, V> parent = null;

        while (node != null) {
            parent = node;
            if (comparator.compare(insertNode.data.key, node.data.key) < 0)
                node = node.leftChild;
            else if (comparator.compare(insertNode.data.key, node.data.key) > 0) {
                node = node.rightChild;
            } else {
                throw new IllegalArgumentException("RedBlackTree already contains a node" + insertNode);
            }
        }

        Node<K, V> newNode = new Node<K, V>(insertNode.data, Node.Color.RED);
        if (parent == null)
            root = newNode;
        else if (comparator.compare(insertNode.data.key, parent.data.key) < 0)
            parent.leftChild = newNode;
        else
            parent.rightChild = newNode;

        newNode.parent = parent;

        balance(newNode);
    }

    /**
     * Check if key exist in tree and return node if it, else return null
     * 
     * @param key - key for search
     * @return Node or null
     */
    public Node<K, V> get(K key) {
        Node<K, V> node = root;
        while (node != null) {
            if (comparator.compare(key, node.data.key) == 0)
                return node;
            else if (comparator.compare(key, node.data.key) < 0)
                node = node.leftChild;
            else
                node = node.rightChild;
        }

        return null;
    }

    /**
     * Update value in tree by key
     * @param key
     * @param value
     * @throws KeyException
     */
    public void set(K key, V value) throws KeyException{
        Node<K, V> node = root;
        while (node != null) {
            if (comparator.compare(key, node.data.key) == 0) {
                node.data.value = value;
                break;
            } else if (comparator.compare(key, node.data.key) < 0)
                node = node.leftChild;
            else
                node = node.rightChild;
        }
        throw new KeyException("Key not found in tree");
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        Node<K, V> leftChild = node.leftChild;

        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null)
            leftChild.rightChild.parent = node;

        leftChild.rightChild = node;
        node.parent = leftChild;
        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        Node<K, V> rightChild = node.rightChild;

        node.rightChild = rightChild.leftChild;
        if (rightChild.leftChild != null)
            rightChild.leftChild.parent = node;

        rightChild.leftChild = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(Node<K, V> parent, Node<K, V> oldChild, Node<K, V> newChild) {
        if (parent == null)
            root = newChild;
        else if (parent.leftChild == oldChild)
            parent.leftChild = newChild;
        else if (parent.rightChild == oldChild)
            parent.rightChild = newChild;
        else
            throw new IllegalStateException("Internal error, node isn't a child of its parent");

        if (newChild != null)
            newChild.parent = parent;
    }

    private Node<K, V> getGrandparanet(Node<K, V> node) {
        return node.parent.parent;
    }

    private Node<K, V> getUncle(Node<K, V> node) {
        Node<K, V> grandparent = getGrandparanet(node);
        if (grandparent.leftChild == node.parent)
            return grandparent.rightChild;
        else if (grandparent.rightChild == node.parent)
            return grandparent.leftChild;
        else
            throw new IllegalStateException("Internal error, parent isn't a child of its grandparent");
    }

    private void balance(Node<K, V> node) {
        if (node == root)
            return;
        // Если родитель черный, то балансировка не требуется
        Node<K, V> parent = node.parent;
        if (node.color == Node.Color.BLACK)
            return;

        // Если нет дедушки, то значит родитель - корень. Следовательно, родитель -
        // черный
        Node<K, V> grandparent = getGrandparanet(node);
        if (grandparent == null) {
            parent.color = Node.Color.BLACK;
            return;
        }

        // Если дядя красный, то перекрашиваем родителя, дедушку и дяду
        Node<K, V> uncle = getUncle(node);
        if (uncle != null && uncle.color == Node.Color.RED) {
            parent.color = Node.Color.BLACK;
            grandparent.color = Node.Color.RED;
            uncle.color = Node.Color.BLACK;

            balance(grandparent);
        } else if (parent == grandparent.leftChild) { // Родитель левый потомок дедушки
            // Дядя черный и новый элемент левый->правый потомок дедушки
            if (node == parent.rightChild) {
                rotateLeft(parent);
                parent = node;
            }

            // Дядя черный и новый элемент левый->левый потоком дедушки
            rotateRight(grandparent);

            // Перекрашиваем родителя и дедушку
            parent.color = Node.Color.BLACK;
            grandparent.color = Node.Color.RED;
        } else { // Родитель правый потомок дедушки
            // Дядя черный и новый элемент правый->левый потомок дедушки
            if (node == parent.leftChild) {
                rotateRight(parent);
                parent = node;
            }

            // Дядя черный и новый элемент правый->правый потоком дедушки
            rotateLeft(grandparent);

            // Перекрашиваем родителя и дедушку
            parent.color = Node.Color.BLACK;
            grandparent.color = Node.Color.RED;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        System.gc();
    }

    @Override
    public Iterator<Element<K, V>> iterator() {
        return new RedBlackTreeIterator(root);
    }

    private class RedBlackTreeIterator implements Iterator<Element<K, V>> {
        Stack<Node<K, V>> stack;

        RedBlackTreeIterator(Node<K, V> root) {
            stack = new Stack<Node<K, V>>();
            explore(root);
        }

        private void explore(Node<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public Element<K, V> next() {
            Node<K, V> node = stack.pop();
            if (node.rightChild != null) {
                explore(node.rightChild);
            }
            return node.data;
        }

    }
}