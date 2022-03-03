package com.delvin;

import java.util.LinkedList;

/**
 * 1. Конструктор: {@link #HashMap} <br>
 * 2. Удаление всех элементов: {@link #clear} <br>
 * 3. Добавление пары ключ-значение: {@link #put} <br>
 * 4. Получение значения: {@link #get} <br>
 * 5. Удаление элемента по ключу: {@link #remove} <br>
 * 6. Получение числа элементов: {@link #size} <br>
 * 7. Получение коэффициента загруженности: {@link #getLoadFactor} <br>
 * 8. Изменение коэффициента загруженности: {@link #setLoadFactor} <br>
 * 9. Перехеширование всех элементов: {@link #resize} <br>
 * 10. Перехеширование с уменьшием числа списков: {@link #resizeWithDecrease}
 * <br>
 */
public class HashMap<K, V> implements Map<K, V> {
    private float loadFactor = 2.0f;
    private int capacity = 16;
    private int elements = 0;

    private LinkedList<Node<K, V>>[] table;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = (LinkedList<Node<K, V>>[]) new LinkedList[capacity];
    }

    public HashMap(float loadFactor) {
        this();
        this.loadFactor = loadFactor;
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        table = (LinkedList<Node<K, V>>[]) new LinkedList[capacity];
        this.capacity = capacity;
    }

    public HashMap(int capacity, float loadFactor) {
        this(loadFactor);
        this.capacity = capacity;
    }

    /**
     * Put value with increase capacity
     * 
     * @return old value if detect collision or null
     */
    @Override
    public V put(K key, V value) {
        return putVal(key, value, true);
    }

    /**
     * Put value with decrease capacity
     * 
     * @return old value if detect collision or null
     */
    public V putWithDecreaseCapacity(K key, V value) {
        return putVal(key, value, false);
    }

    private V putVal(K key, V value, boolean resizeMethod) {
        int hash = hash(key);
        int i = indexFor(hash, capacity);

        if (table[i] == null) {
            table[i] = new LinkedList<Node<K, V>>();
            table[i].add(new Node<K, V>(hash, key, value));
            elements++;
        } else {
            for (Node<K, V> node : table[i]) {
                if (node.hash == hash && (node.key == key || (key != null && node.key.equals(key)))) {
                    V retVal = node.value;
                    node.value = value;
                    return retVal;
                }
            }
            table[i].addLast(new Node<K, V>(hash, key, value));
            elements++;
        }

        if (getLoadFactor() >= loadFactor) {
            if (resizeMethod)
                resizeWithIncreaseCapacity();
            else
                resizeWithDecreaseCapacity();
        }

        return null;
    }

    /**
     * Get value by key
     * 
     * @return value
     */
    @Override
    public V get(K key) {
        int hash = hash(key);
        int i = indexFor(hash, capacity);

        LinkedList<Node<K, V>> tmpList;
        if ((tmpList = table[i]) != null && elements != 0) {
            for (Node<K, V> node : tmpList) {
                if (node.hash == hash && (node.key == key || (key != null && node.key.equals(key))))
                    return node.value;
            }
            return null;
        }

        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                table[i].clear();
                table[i] = null;
            }
        }
        this.elements = 0;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int i = indexFor(hash, capacity);

        LinkedList<Node<K, V>> tmpList;
        if ((tmpList = table[i]) != null && elements != 0) {
            for (Node<K, V> node : tmpList) {
                if (node.hash == hash && (node.key == key || (key != null && node.key.equals(key)))) {
                    V retVal = node.value;
                    tmpList.remove(node);
                    elements--;
                    return retVal;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Expand list for storing elements.
     */
    private void resizeWithIncreaseCapacity() {
        int newCapacity = 2 * capacity + 1;
        rehash(newCapacity);
    }

    private void resizeWithDecreaseCapacity() {
        int newCapacity = capacity;
        while (getLoadFactor() < loadFactor)
            newCapacity--;
        rehash(newCapacity);
    }

    @SuppressWarnings("unchecked")
    private void rehash(int capacity) {
        LinkedList<Node<K, V>>[] newTable = (LinkedList<Node<K, V>>[]) new LinkedList[capacity];

        for (LinkedList<Node<K, V>> list : table) {
            if (list != null) {
                for (Node<K, V> node : list) {
                    int i = indexFor(node.hash, capacity);
                    if (newTable[i] == null)
                        newTable[i] = new LinkedList<Node<K, V>>();
                    newTable[i].add(node);
                }
            }
        }
        this.table = newTable;
        this.capacity = capacity;

    }

    /**
     * Return of number elements in HashMap
     *
     * @return the number of elements in HashMap
     */
    public int size() {
        return elements;
    }

    /**
     * Return of capacity of HashMap
     *
     * @return capacity of HashMap
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Check if HashMap empty
     *
     * @return true if HashMap empty
     */
    public boolean isEmpty() {
        return elements == 0;
    }

    /**
     * Get the load factor
     * 
     * @return loadFactor of capacity
     */
    public float getLoadFactor() {
        return (float) elements / capacity;
    }

    /**
     * Set the load factor
     * 
     * @param loadFactor - new load factor
     */
    public void setLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
    }

    /**
     * According to the technical task, it is necessary to use this formula,
     * which makes it possible not to recalculate the indexes when expanding the
     * list.
     * 
     * @param hash
     * @param length
     * @return new index
     */
    private int indexFor(int hash, int length) {
        return hash % length;
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
