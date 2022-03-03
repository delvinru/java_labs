package com.delvin;

import java.util.*;

public class Graph<T> {
    HashMap<T, Set<Vertex<T>>> graph;

    public Graph() {
        graph = new HashMap<T, Set<Vertex<T>>>();
    }

    public Graph(int[][] adjustmentMatrix) throws GraphException {
        this();
        convertFromAdjustmentMatrix(adjustmentMatrix);
    }

    /**
     * Add vertex to graph
     * 
     * @param vertex - vertex value
     * @throws GraphException
     */
    public void addVertex(T vertex) throws GraphException {
        if (vertex == null)
            throw new GraphException("Vertex can't be null");
        if (graph.containsKey(vertex))
            throw new GraphException("Vertex already present in graph");
        graph.put(vertex, new HashSet<Vertex<T>>());
    }

    /**
     * Add connection to graph without weights
     * 
     * @param fromVertex - from vertex connection
     * @param toVertex   - to vertex connection
     * @throws GraphException - if insert null values
     */
    public void addConnection(T fromVertex, T toVertex) throws GraphException {
        if (fromVertex == null || toVertex == null)
            throw new GraphException("Vertex can't be null");
        graph.get(fromVertex).add(new Vertex<T>(toVertex));
    }

    /**
     * Add connection to graph with weights
     * 
     * @param fromVertex - from vertex connection
     * @param toVertex   - to vertex connection
     * @param weight     - weight between vertex's
     * @throws GraphException - if vertex null
     */
    public void addConnection(T fromVertex, T toVertex, int weight) throws GraphException {
        if (fromVertex == null || toVertex == null)
            throw new GraphException("Vertex can't be null");
        graph.get(fromVertex).add(new Vertex<T>(toVertex, weight));
    }

    /**
     * Check if vertex in graph
     * 
     * @param vertex
     * @return {@code true} - if vertex in graph
     */
    public boolean containVertex(T vertex) {
        return graph.containsKey(vertex);
    }

    /**
     * Remove vertex from graph
     * 
     * @param vertex - vertex for remove
     */
    public void removeVertex(T vertex) {
        if (vertex == null)
            return;
        if (!graph.containsKey(vertex))
            return;

        graph.remove(vertex);

        for (Set<Vertex<T>> connections : graph.values())
            connections.removeIf(key -> key.getValue() == vertex);
    }

    public void removeConnection(T fromVertex, T toVertex) {
        if (fromVertex == null || toVertex == null)
            return;
        if (!graph.containsKey(fromVertex))
            return;

        graph.get(fromVertex).removeIf(key -> key.getValue() == toVertex);
    }

    @SuppressWarnings("unchecked")
    private void convertFromAdjustmentMatrix(int[][] adjustmentMatrix) throws GraphException {
        int rows = adjustmentMatrix.length;
        int columns = adjustmentMatrix[0].length;

        if (rows != columns)
            throw new GraphException("The adjacency matrix must be square");

        for (int i = 0; i < rows; i++) {
            graph.put((T) Integer.valueOf(i + 1), new HashSet<Vertex<T>>());
            for (int j = 0; j < columns; j++) {
                int weight = adjustmentMatrix[i][j];
                if (weight != 0)
                    addConnection((T) Integer.valueOf(i + 1), (T) Integer.valueOf(j + 1), Integer.valueOf(weight));
            }
        }
    }

    /**
     * Return adjustmentMatrix for graph
     * 
     * @return {@code int[][] matrix} - adjustment matrix for graph
     */
    public int[][] getAdjustmentMatrix() {
        if (graph.size() == 0)
            return null;

        int size = graph.size();
        int[][] adjustmentMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (Vertex<T> vertex : graph.get(i + 1))
                adjustmentMatrix[i][(Integer) vertex.getValue() - 1] = vertex.getWeight();
        }

        return adjustmentMatrix;
    }

    /**
     * Returns all vertexs connected to a given vertex
     * 
     * @param vertex
     * @return set of vertexs
     * @throws GraphException
     */
    public Set<Vertex<T>> getConnections(T vertex) throws GraphException {
        if (vertex == null)
            throw new GraphException("Vertex can't be null");
        if (!graph.containsKey(vertex))
            throw new GraphException("Vertex not in graph");

        return graph.get(vertex);
    }

    /**
     * Return count of vertex in graph
     * 
     * @return count of vertex
     */
    public int size() {
        return graph.size();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (T vertex : graph.keySet()) {
            string.append(vertex + " : { ");
            for (Vertex<T> connection : graph.get(vertex))
                string.append(connection + "; ");
            string.append("};\n");
        }
        return string.toString();
    }
}
