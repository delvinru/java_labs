package com.delvin;

import java.util.*;

/**
 * Implementation of graph based on HashMap: <br>
 * Key - vertex of graph <br>
 * Set - all connections from this vertex to other vertex <br>
 */
public class Graph<T> {
    HashMap<T, Set<Vertex<T>>> graph;

    /**
     * Initialize empty graph
     */
    public Graph() {
        graph = new HashMap<T, Set<Vertex<T>>>();
    }

    public Graph(Graph<T> graph) throws GraphException {
        for (T vertex : graph.getVertexs()) {
            addVertex(vertex);
            for (Vertex<T> vertex2 : graph.getConnections(vertex)) {
                addConnection(vertex, vertex2.getValue());
            }
        }
    }

    /**
     * Initialize graph with weights based on adjustmentMatrix
     * 
     * @param adjustmentMatrix
     * @throws GraphException
     */
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
        if (!graph.containsKey(fromVertex))
            this.addVertex(fromVertex);
        if (!graph.containsKey(toVertex))
            this.addVertex(toVertex);
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
        if (!graph.containsKey(fromVertex))
            this.addVertex(fromVertex);
        if (!graph.containsKey(toVertex))
            this.addVertex(toVertex);
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

    /**
     * Inititalize graph with adjustment matrix
     * 
     * @param adjustmentMatrix
     * @throws GraphException
     */
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
     * @return Return all vertexs in graph
     * @throws GraphException
     */
    public Set<T> getVertexs() throws GraphException {
        if (graph.isEmpty())
            throw new GraphException("Graph haven't vertexes!");
        return graph.keySet();
    }

    /**
     * Help function for kruskal algo
     * 
     * @return list of edges
     * @throws GraphException
     */
    public List<Edge<T>> getEdges() throws GraphException {
        if (graph == null || graph.isEmpty())
            throw new GraphException("In empty graph can't be edges");
        List<Edge<T>> edges = new ArrayList<>();
        for (T from : this.getVertexs())
            for (Vertex<T> to : this.getConnections(from))
                edges.add(new Edge<T>(from, to.getValue(), to.getWeight()));
        return edges;
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
            throw new GraphException("Vertex don't exists in graph");
        return graph.get(vertex);
    }

    /**
     * @return weight of graph
     * @throws GraphException
     */
    public int getGraphWeight() throws GraphException {
        if (graph == null || graph.isEmpty())
            throw new GraphException("Empty graph hasn't edges!");

        int result = 0;
        for (T vertex : this.getVertexs())
            for (Vertex<T> connection : this.getConnections(vertex)) {
                // System.out.println(vertex + ":" + connection.getValue() + " : " +
                // connection.getWeight());
                result += connection.getWeight();
            }
        return result;
    }

    /**
     * Return count of vertex in graph
     * 
     * @return count of vertex
     */
    public int size() {
        return graph.size();
    }

    /**
     * Nice print for graph
     */
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

    public List<T> getOutVertexs(T vertex) throws GraphException {
        if (!graph.containsKey(vertex))
            throw new GraphException("Vertex don't exists in graph");

        List<T> result = new ArrayList<>();
        for (Edge<T> edge : this.getEdges())
            if (edge.src.equals(vertex))
                result.add(edge.dest);
        return result;
    }

    public List<T> getInVertexs(T vertex) throws GraphException {
        if (!graph.containsKey(vertex))
            throw new GraphException("Vertex don't exists in graph");

        List<T> result = new ArrayList<>();
        for (Edge<T> edge : this.getEdges())
            if (edge.dest == vertex)
                result.add(edge.src);
        return result;
    }

    public Edge<T> getEdge(T src, T dest) throws GraphException {
        if (!(graph.containsKey(src) || graph.containsKey(dest)))
            throw new GraphException("Vertex not in graph");

        for (Edge<T> edge : this.getEdges())
            if (edge.src.equals(src) && edge.dest.equals(dest))
                return edge;

        return null;
    }

    public Graph<T> getTransposedGraph() throws GraphException {
        Graph<T> graph = new Graph<>();

        for (T vertex : this.getVertexs())
            graph.addVertex(vertex);

        for (Edge<T> edge : this.getEdges())
            graph.addConnection(edge.dest, edge.src, edge.weight);

        return graph;

    }
}
