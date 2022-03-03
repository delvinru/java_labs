package com.delvin;

public class Node<K, V> {
    final int hash;
    final K key;
    V value;

    public Node(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{hash=" + hash + "; key=" + key + "; value=" + value + "}";
    }
}
