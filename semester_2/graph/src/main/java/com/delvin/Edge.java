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

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object objEdge) {
        if (objEdge == this)
            return true;

        if (objEdge == null)
            return false;

        Edge<T> edge = (Edge<T>) objEdge;
        if (edge.src == this.src && edge.dest == this.dest)
            return true;

        return false;
    }
}
