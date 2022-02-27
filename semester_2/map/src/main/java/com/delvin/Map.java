package com.delvin;

import java.security.KeyException;
import java.util.Iterator;

/**
 * 1. Конструктор: {@link #Map} <br>
 * 2. Конструктор копирования: {@link #Map(Map)} <br>
 * 3. Копирующий оператор присваивания: don't exist overloads in Java <br>
 * 4. Проверка на пустоту: {@link #isEmpty} <br>
 * 5. Удаление всех элементов: {@link #clear} <br>
 * 6. Добавление пары ключ-значение: {@link #push} <br>
 * 7. Получение значения по ключу: {@link #getValueByKey} <br>
 * 8. Поиск по ключу: {@link #get} <br>
 * 9. Создать итерируемую коллекцию: {@link #iterator} <br>
 */
public class Map<K extends Comparable<K>, V> implements Iterable<Element<K, V>> {
    private RedBlackTree<K, V> tree;

    public Map() {
        tree = new RedBlackTree<>();
    }

    public Map(Map<K, V> map) {
        tree = new RedBlackTree<>(map.tree);
    }

    public void push(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null object");
        tree.insertNode(new Node<K, V>(key, value));
    }

    public void push(Element<K, V> element) {
        if (element == null)
            throw new IllegalArgumentException("Element can't be null object");

        tree.insertNode(new Node<>(element));
    }

    public Element<K, V> get(K key) {
        Node<K, V> node = tree.get(key);
        return node.data;
    }

    public void set(K key, V value) throws KeyException {
        tree.set(key, value);
    }

    public V getValueByKey(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null object");
        Element<K, V> res = get(key);
        if (res == null)
            return null;
        return res.value;
    }

    public void clear() {
        tree.clear();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public Iterator<Element<K, V>> iterator() {
        return tree.iterator();
    }
}
