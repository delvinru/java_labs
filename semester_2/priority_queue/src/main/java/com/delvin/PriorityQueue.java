package com.delvin;

import java.util.List;
import java.util.ArrayList;

/**
 * 1. Проверка очереди на пустоту: {@link #isEmpty} <br>
 * 2. Получение числа элементов в очереди: {@link #getSize} <br>
 * 3. Добавление элементов в очередь: {@link #push} <br>
 * 4. Удаление элемента из очереди: {@link #pop} <br>
 * 5. Доступ к максимальному элементу очереди: {@link #getMax} <br>
 * 6. Очищение очереди: {@link #clear}
 */
public class PriorityQueue<T extends Comparable<T>> {
    private final List<T> queue;

    PriorityQueue() {
        queue = new ArrayList<>();
    }

    /**
     * Check queue is empty or not
     * 
     * @return true - if queue is empty, false in else cases
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Clear queue
     */
    public void clear() {
        queue.clear();
    }

    /**
     * @return the number of items in the queue
     */
    public int getSize() {
        return queue.size();
    }

    /**
     * swap two elements by indexes
     * 
     * @param i - first element
     * @param j - secodn element
     */
    private void swap(int i, int j) {
        T tmp = queue.get(i);
        queue.set(i, this.queue.get(j));
        queue.set(j, tmp);
    }

    /**
     * Add new element in queue
     * 
     * @param el
     * @throws NullPointerException
     */
    public void push(T el) throws NullPointerException {
        if (null == el)
            throw new NullPointerException("Can't insert null value in queue");
        queue.add(el);
        shiftUp(queue.size() - 1);
    }

    /**
     * @param i idx of children
     */
    private void shiftUp(int i) {
        while (queue.get(i).compareTo(queue.get((i - 1) / 2)) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     * Get next element from queue
     * 
     * @return null if queue is empty
     */
    public T pop() {
        if (queue.isEmpty())
            return null;

        T maxElement = queue.get(0);
        int queueSize = queue.size();
        if (queueSize > 1) {
            queue.set(0, queue.get(queueSize - 1));
            queue.remove(queueSize - 1);
            shiftDown(0);
        } else
            queue.remove(0);
        return maxElement;
    }

    private void shiftDown(int i) {
        while ((2 * i + 1) < queue.size()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < queue.size() && queue.get(right).compareTo(queue.get(left)) > 0)
                j = right;
            if (queue.get(i).compareTo(queue.get(j)) >= 0)
                break;
            swap(i, j);
            i = j;
        }
    }

    /**
     * @return maximum element from queue
     */
    public T getMax() {
        if (queue.isEmpty())
            return null;
        return queue.get(0);
    }
}
