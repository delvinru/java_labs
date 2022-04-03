package com.delvin;

/**
 * Helper class for store information about Vertex
 */
public class Vertex<T> {
    private T value;
    private Integer weight = null;

    /**
     * Init vertex without weights
     * 
     * @param value - value for vertex
     */
    public Vertex(T value) {
        this.value = value;
    }

    /**
     * Init graph with weights
     * 
     * @param value  - value for vertex
     * @param weight - weight for connection
     */
    public Vertex(T value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    public T getValue() {
        return value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "value=" + getValue() + ":weight=" + getWeight();
    }
}
