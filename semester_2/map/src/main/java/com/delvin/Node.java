package com.delvin;

public class Node<K extends Comparable<K>, V> {
    /**
     * Support structure for use names instead of numbers
     */
    static enum Color {
        BLACK,
        RED
    }

    Element<K, V> data = null;

    Color color = Color.BLACK;

    Node<K, V> parent = null;
    Node<K, V> leftChild = null;
    Node<K, V> rightChild = null;

    public Node() {
    }

    public Node(K key, V value) {
        this.data = new Element<>(key, value);
    }

    public Node(Element<K, V> data) {
        this.data = data;
    }

    public Node(Element<K, V> data, Color color) {
        this.data = data;
        this.color = color;
    }

    public void flipColor() {
        this.color = (this.color == Color.RED ? Color.BLACK : Color.RED);
    }

    @Override
    public String toString() {
        return "Node: " + data + " | Color=" + this.color.name();
    }
}
