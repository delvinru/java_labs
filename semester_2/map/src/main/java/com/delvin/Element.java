package com.delvin;

public class Element<K extends Comparable<K>, V> implements Comparable<Element<K, V>> {
    public K key;
    public V value;

    Element(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return (key != null) ? key.hashCode() : 1337;
    }

    /**
     * Object equals if key's matches
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Element<?, ?> other = (Element<?, ?>) obj;
        return this.key == other.key;
    }

    @Override
    public int compareTo(Element<K, V> element) {
        return this.key.compareTo(element.key);
    }

    @Override
    public String toString() {
        return "Element{key=" + this.key + ", value=" + this.value + "}";
    }
}
