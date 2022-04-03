package com.delvin;

/**
 * Helper class for some algorithm's
 */
public class Edge<T> {
    public T src, dest;
    public int weight;

    public Edge(T src, T dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge { src=" + this.src + ", dest=" + this.dest + ", weight=" + this.weight + " }";
    }
}
