package com.delvin;

/**
 * Класс, описывающие элемент, который хранит пару ключ-значение.
 */
public class Element<K extends Comparable<K>, V> implements Comparable<Element<? extends Comparable<?>, ?>> {
    public K key = null;
    public V value = null;

    public Element(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Element(Element<K, V> element) {
        this.key = element.key;
        this.value = element.value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * 1337 + ((key == null) ? 0 : key.hashCode());
    }

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
    public String toString() {
        return this.getClass().getName() + "{key=" + key + "; value=" + value + "}";
    }

    /**
     * Compare elements by key
     * 
     * @param other
     * @return int
     */
    @SuppressWarnings("unchecked")
    public int compareTo(Element<? extends Comparable<?>, ?> other) {
        if (other == null)
            throw new IllegalArgumentException("Value for compare can't be null");
        if (this.getClass() != other.getClass())
            throw new IllegalArgumentException("Illegal class compares");
        return this.key.compareTo((K)other.key);
    }
}
