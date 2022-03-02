package com.delvin;

import java.security.KeyException;

public interface MapInterface<K extends Comparable<K>, V> {
    public boolean empty();

    public void clear();

    public void put(K key, V value);

    public void put(Element<K, V> element);

    public V get(K key);

    public boolean search(K key);

    public void set(K key, V value) throws KeyException;
}
