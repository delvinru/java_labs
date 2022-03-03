package com.delvin;

import java.util.*;

public class GraphAlgorithmsA {
    /**
     * Check if exist path from point A to point B with dfs
     * 
     * @param <T>
     * @param graph      - graph for search
     * @param fromVertex - from vertex
     * @param toVertex   - to vertex
     * @return {@code true} - if path exists
     * @throws GraphException
     */
    public static <T> boolean dfs(Graph<T> graph, T fromVertex, T toVertex) throws GraphException {
        return dfs(graph, fromVertex, toVertex, new HashSet<T>());
    }

    private static <T> boolean dfs(Graph<T> graph, T fromVertex, T toVertex, HashSet<T> visited) throws GraphException {
        if (!(graph.containVertex(fromVertex) || graph.containVertex(toVertex)))
            throw new GraphException("Vertex don't exist in graph");

        if (fromVertex.equals(toVertex))
            return true;

        if (visited.contains(toVertex))
            return true;

        visited.add(fromVertex);

        for (Vertex<T> vertex : graph.getConnections(fromVertex)) {
            if (!visited.contains(vertex.getValue())) {
                if (dfs(graph, vertex.getValue(), toVertex, visited))
                    return true;
            }
        }

        return false;
    }

    /**
     * Check if exist path from point A to point B with bfs
     * 
     * @param graph      - graph for search
     * @param fromVertex - from vertex
     * @param toVertex   - to vertex
     * @return {@code true} - if path exists
     * @throws GraphException
     */
    public static <T> boolean bfs(Graph<T> graph, T fromVertex, T toVertex) throws GraphException {
        return bfs(graph, fromVertex, toVertex, new LinkedList<T>());
    }

    private static <T> boolean bfs(Graph<T> graph, T fromVertex, T toVertex, LinkedList<T> queue)
            throws GraphException {
        if (!graph.containVertex(fromVertex))
            throw new GraphException("Vertex don't exist in graph");

        HashSet<T> visited = new HashSet<T>();

        queue.push(fromVertex);
        visited.add(fromVertex);

        while (queue.size() > 0) {
            T vertex = queue.pop();
            for (Vertex<T> neighbor : graph.getConnections(vertex))
                if (!visited.contains(neighbor.getValue())) {
                    queue.push(neighbor.getValue());
                    visited.add(neighbor.getValue());
                    if (neighbor.getValue() == toVertex)
                        return true;
                }
        }
        return false;
    }

    /**
     * Find shortest ways from vertex to all points with adjustmentMatrix
     * 
     * @param <T>
     * @param graph
     * @param fromVertex
     * @return
     */
    public static <T> int[] dijkstra(Graph<T> graph, int fromVertex) {
        final int INF = Integer.MAX_VALUE;
        int vertexNumber = graph.size();
        int[] dist = new int[vertexNumber];
        boolean[] used = new boolean[vertexNumber];

        int[][] matrix = graph.getAdjustmentMatrix();

        // Correct values for algorithm
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 0)
                    matrix[i][j] = INF;

        Arrays.fill(dist, INF);

        dist[fromVertex] = 0;
        for (;;) {
            int v = -1;
            for (int nv = 0; nv < vertexNumber; nv++)
                if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv]))
                    v = nv;
            if (v == -1)
                break;
            used[v] = true;
            for (int nv = 0; nv < vertexNumber; nv++)
                if (!used[nv] && matrix[v][nv] < INF)
                    dist[nv] = Math.min(dist[nv], dist[v] + matrix[v][nv]);
        }

        return dist;
    }

    public static <T> void kruskalAlgoritm() {
        return;
    }

    public static <T> void primAlgorithm() {
        return;
    }

    public static <T> void floydWarshallAlgorithm() {
        return;
    }

}
