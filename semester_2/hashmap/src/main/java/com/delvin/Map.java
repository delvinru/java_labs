package com.delvin;

public interface Map<K, V> {
    public void clear();

    public V get(K key);

    public V put(K key, V value);

    public V remove(K key);
}
