package com.delvin;

import java.util.*;

/**
 * Implemented algos: <br>
 * 1. dfs {@link #dfs(Graph, Object, Object)} <br>
 * 2. bfs {@link #bfs(Graph, Object, Object)} <br>
 * 3. dijkstra {@link #dijkstra(Graph, int)} <br>
 * 4. kruskal {@link #kruskal(Graph)} <br>
 * 5. prim {@link #prim(Graph)} <br>
 * 6. floyd-warshall {@link #floydWarshall(Graph)} <br>
 */
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
     * @return {@code int[] distances} - array with distances from current vertex to
     *         another
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
                if (matrix[i][j] == 0 && i != j)
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

    /**
     * Find minimum spanning tree wtih Kruskal algorithm
     * 
     * @param <T>   - type for graph
     * @param graph - graph
     * @return - new graph contains only MST
     * @throws GraphException
     */
    public static <T> Graph<T> kruskal(Graph<T> graph) throws GraphException {
        List<Edge<T>> edges = graph.getEdges();
        // Sort edgest by weight
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        // Create new MST graph
        Graph<T> MST = new Graph<>();

        int vertexsCount = graph.getVertexs().size();
        int[] parents = new int[vertexsCount + 1];
        int[] size = new int[vertexsCount + 1];
        for (int i = 0; i < vertexsCount; i++) {
            parents[i] = i;
            size[i] = 1;
        }

        // Enumerate all edges
        for (Edge<T> edge : edges) {
            // If adding an edge results in a cycle, skip this edge
            if (isCyclic((Integer) edge.src, (Integer) edge.dest, parents))
                continue;

            union(findParent((Integer) edge.src, parents), findParent((Integer) edge.dest, parents), parents, size);
            MST.addConnection(edge.src, edge.dest, edge.weight);
        }

        return MST;
    }

    /**
     * Helper function for Kruskal algorithm
     * 
     * @param <T>
     * @param src
     * @param dest
     * @param parents
     * @return
     */
    private static <T> boolean isCyclic(Integer src, Integer dest, int[] parents) {
        return findParent(src, parents) == findParent(dest, parents);
    }

    /**
     * Helper function for Kruskal algorithm
     * 
     * @param <T>
     * @param vertex
     * @param parents
     * @return
     */
    private static <T> Integer findParent(Integer vertex, int[] parents) {
        if (parents[vertex] == vertex)
            return vertex;
        else {
            parents[vertex] = findParent(parents[vertex], parents);
            return parents[vertex];
        }
    }

    /**
     * Helper function for Kruskal algorithm
     * 
     * @param src
     * @param dest
     * @param parents
     * @param size
     */
    private static void union(Integer src, Integer dest, int[] parents, int[] size) {
        src = findParent(src, parents);
        dest = findParent(dest, parents);
        if (size[src] > size[dest]) {
            parents[dest] = src;
            size[src] += size[dest];
        } else {
            parents[src] = dest;
            size[dest] += size[src];
        }
    }

    /**
     * Find minimum spanning tree wtih Prim's algorithm
     * P.S. simple algo with adjustment matrix
     * 
     * @param <T>   - type for graph
     * @param graph - graph
     * @return - new graph contains only MST
     * @throws GraphException
     */
    public static <T> Graph<Integer> prim(Graph<T> graph) throws GraphException {
        // Init new graph for store MST
        Graph<Integer> MST = new Graph<>();

        int numberOfVertex = graph.getVertexs().size();
        boolean[] selected = new boolean[numberOfVertex];
        Arrays.fill(selected, false);

        // By default start with first point
        selected[0] = true;

        // Generate adjustment matrix from graph
        int[][] matrix = graph.getAdjustmentMatrix();
        final int INF = Integer.MAX_VALUE / 2;

        // Correct values for algorithm
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 0)
                    matrix[i][j] = INF;

        for (int edgeCount = 0; edgeCount < numberOfVertex - 1; edgeCount++) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < numberOfVertex; i++) {
                for (int j = 0; j < numberOfVertex; j++) {
                    if (selected[i] && !selected[j] && matrix[i][j] < matrix[x][y]) {
                        x = i;
                        y = j;
                    }
                }
            }
            MST.addConnection(x + 1, y + 1, matrix[x][y]);
            selected[y] = true;
        }
        return MST;

    }

    /**
     * Find shortes way between all pairs
     * 
     * @param <T>
     * @param graph - graph for work
     * @return - {@code int[][]} -
     */
    public static <T> int[][] floydWarshall(Graph<T> graph) {
        final int INF = Integer.MAX_VALUE / 2;
        int vertexNumber = graph.size();
        int[][] matrix = graph.getAdjustmentMatrix();

        // Correct values for algorithm
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] == 0 && i != j)
                    matrix[i][j] = INF;

        int[][] dist = new int[vertexNumber][vertexNumber];
        for (int i = 0; i < vertexNumber; i++)
            System.arraycopy(matrix[i], 0, dist[i], 0, vertexNumber);

        for (int k = 0; k < vertexNumber; k++)
            for (int i = 0; i < vertexNumber; i++)
                for (int j = 0; j < vertexNumber; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        return dist;
    }
}