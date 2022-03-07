package com.delvin;

import java.security.KeyException;
import java.util.Iterator;

/**
 * 1. Конструктор: {@link #BTreeMap} <br>
 * 2. Конструктор копирования: {@link #BTreeMap(BTreeMap<K, V> map)}<br>
 * 3. Проверка на пустоту: {@link #empty} <br>
 * 4. Удаление всех элементов: {@link #clear} <br>
 * 5. Добавление пары ключ-значение: {@link #put} <br>
 * 6. Получить значение по ключу: {@link #get} <br>
 * 7. Поиск по ключу: {@link #search} <br>
 */
public class BTreeMap<K extends Comparable<K>, V> implements MapInterface<K, V>, Iterable<Element<K, V>> {
    private BTree<Element<K, V>> tree = null;

    /**
     * Default constructor
     * The degree of the tree will be determined by default value
     */
    public BTreeMap() {
        this.tree = new BTree<>();
    }

    /**
     * @param t - BTree degree
     */
    public BTreeMap(int t) {
        this.tree = new BTree<>(t);
    }

    public BTreeMap(BTreeMap<K, V> map) {
        this.tree = new BTree<>(map.tree);
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean empty() {
        return tree.empty();
    }

    @Override
    public V get(K key) {
        return tree.get(new Element<K, V>(key, null)).value;
    }

    @Override
    public void put(K key, V value) {
        tree.insert(new Element<K, V>(key, value));
    }

    @Override
    public void put(Element<K, V> element) {
        tree.insert(element);
    }

    @Override
    public boolean search(K key) {
        return tree.get(new Element<K, V>(key, null)) != null;
    }

    @Override
    public void set(K key, V value) throws KeyException {
        if (tree.get(new Element<K, V>(key, value)) == null)
            throw new KeyException("Key not found in tree");
        tree.set(new Element<K, V>(key, value));
    }

    @Override
    public Iterator<Element<K, V>> iterator() {
        return tree.iterator();
    }
}