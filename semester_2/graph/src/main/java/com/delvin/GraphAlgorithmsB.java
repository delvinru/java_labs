package com.delvin;

import java.util.*;

/**
 * Implemented algorithms: <br>
 * 1. tarjan algorithm for topological sort: {@link #tarjan(Graph)} <br>
 * 2. fleury: {@link #fleury(Graph)} <br>
 * 3. eularian circle: {@link #findEulerCycle(Graph)} <br>
 * 4. kosarayu: {@link #kosaraju(Graph)} <br>
 */
public class GraphAlgorithmsB {
    /**
     * Tarjan algorithm for topological sort in graph
     * 
     * @param <T>   - data type, must be same as graph
     * @param graph - original graph
     * @return - list in topological order
     * @throws GraphException
     */
    public static <T> List<T> tarjan(Graph<T> graph) throws GraphException {
        if (graph == null)
            throw new GraphException("graph can't be null");

        Set<T> grayVertex = new HashSet<>();
        List<T> blackVertex = new ArrayList<>();

        for (T vertex : graph.getVertexs())
            tarjanPrivate(graph, vertex, blackVertex, grayVertex);

        Collections.reverse(blackVertex);
        return blackVertex;
    }

    /**
     * Private function for iterate over each vertex in graph
     * 
     * @param <T>
     * @param graph       - original graph
     * @param vertex      - search vertex
     * @param blackVertex - list of black vertexs
     * @param grayVertex  - list of gray vertexs
     * @throws GraphException
     */
    private static <T> void tarjanPrivate(Graph<T> graph, T vertex, List<T> blackVertex, Set<T> grayVertex)
            throws GraphException {
        if (blackVertex.contains(vertex))
            return;

        if (grayVertex.contains(vertex))
            throw new GraphException("Detected loop. Algorithm can't be used on this graph. Try choose another graph");

        grayVertex.add(vertex);
        Set<Vertex<T>> neighbors = graph.getConnections(vertex);
        for (Vertex<T> neighbor : neighbors)
            tarjanPrivate(graph, neighbor.getValue(), blackVertex, grayVertex);

        grayVertex.remove(vertex);
        blackVertex.add(vertex);
    }

    public static <T> List<Edge<T>> fleury(Graph<T> graph) throws GraphException {
        if (graph == null)
            throw new GraphException("graph can't be null");

        T startVertex = checkGraphForEulerism(graph, false);

        List<Edge<T>> result = new ArrayList<>();
        fleuryPrivate(startVertex, startVertex, graph, result);
        return result;
    }

    private static <T> void fleuryPrivate(T vertex, T startVertex, Graph<T> graph, List<Edge<T>> output)
            throws GraphException {
        for (T neighbor : graph.getVertexs()) {
            if (!graph.getConnections(vertex).contains(neighbor))
                continue;

            if (neighbor.equals(startVertex) && graph.getOutVertexs(vertex).size() > 1)
                continue;

            if (canBeRemoved(vertex, neighbor, graph)) {
                output.add(graph.getEdge(vertex, neighbor));
                graph.removeConnection(vertex, neighbor);
                fleuryPrivate(neighbor, startVertex, graph, output);
                break;
            }
        }
    }

    private static <T> boolean canBeRemoved(T src, T dest, Graph<T> graph) throws GraphException {
        if (graph.getOutVertexs(src).size() == 1)
            return true;

        int allEdgesCount = graph.getEdges().size();
        int newEdgesCount = 0;

        Deque<Edge<T>> stack = new ArrayDeque<>();
        Set<Edge<T>> visited = new HashSet<>();
        Edge<T> tempEdge = graph.getEdge(src, dest);
        graph.removeConnection(src, dest);
        stack.add(tempEdge);

        while (!stack.isEmpty()) {
            Edge<T> edge = stack.pop();
            visited.add(edge);

            for (Edge<T> e : graph.getEdges())
                if (e.dest == edge.dest)
                    if (!visited.contains(e) && !stack.contains(e))
                        stack.add(e);

            newEdgesCount++;
        }
        graph.addConnection(tempEdge.src, tempEdge.dest, tempEdge.weight);
        return allEdgesCount == newEdgesCount;
    }

    private static <T> T checkGraphForEulerism(Graph<T> graph, boolean isCycle) throws GraphException {
        // based on:
        // https://ru.wikipedia.org/wiki/%D0%AD%D0%B9%D0%BB%D0%B5%D1%80%D0%BE%D0%B2_%D1%86%D0%B8%D0%BA%D0%BB
        int degreeP1 = 0;
        int degreeM1 = 0;

        T startVertex = null;
        for (T vertex : graph.getVertexs()) {
            if (graph.getInVertexs(vertex).size() == graph.getOutVertexs(vertex).size())
                continue;

            if (degreeM1 == 0 && graph.getInVertexs(vertex).size() == graph.getOutVertexs(vertex).size() - 1) {
                degreeM1++;
                startVertex = vertex;
                continue;
            }

            if (degreeP1 == 0 && graph.getInVertexs(vertex).size() == graph.getOutVertexs(vertex).size() + 1) {
                degreeP1++;
                continue;
            }

            throw new GraphException("Euler path doesn't exist. Choose another graph");
        }

        if (degreeP1 != degreeM1)
            throw new GraphException("Euler path doesn't exist. Choose another graph");

        if (!isCycle && degreeM1 == 0)
            throw new GraphException("Euler circiut doesn't exists. Choose another graph");

        if (startVertex == null)
            startVertex = graph.getVertexs().iterator().next();

        if (degreeM1 == 0) {
            for (T vertex : graph.getVertexs()) {
                int[] distances = GraphAlgorithmsA.dijkstra(graph, (Integer) vertex);
                int existedPaths = 0;
                for (int distance : distances)
                    if (distance != Integer.MAX_VALUE)
                        existedPaths++;
                if (existedPaths != graph.getVertexs().size())
                    throw new GraphException("Euler circuit doesn't exist. Choose another graph");
            }
        }

        return startVertex;
    }

    public static <T> List<Edge<T>> findEulerCycle(Graph<T> graph) throws GraphException {
        if (graph == null)
            throw new GraphException("graph can't be null");

        T startVertex = checkGraphForEulerism(graph, true);

        List<T> result = new ArrayList<>();
        Deque<T> stack = new ArrayDeque<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T vertex = stack.peek();
            if (graph.getOutVertexs(vertex).isEmpty()) {
                result.add(vertex);
                stack.pop();
            } else {
                Edge<T> edge = null;
                for (Edge<T> tmp : graph.getEdges()) {
                    if (tmp.src.equals(vertex)) {
                        edge = tmp;
                        break;
                    }
                }

                if (edge == null)
                    throw new GraphException("Edge not found");

                graph.removeConnection(edge.src, edge.dest);
                stack.push(edge.dest);
            }
        }

        Collections.reverse(result);

        List<Edge<T>> edges = new ArrayList<>();
        for (int i = 0; i < result.size() - 1; i++)
            edges.add(graph.getEdge(result.get(i), result.get(i + 1)));
        return edges;
    }

    /**
     * 
     * @param <T>
     * @param graph
     * @return list of unions, where every unions is list of vertices of a single
     *         connectivity component
     * @throws GraphException
     */
    public static <T> List<List<T>> kosaraju(Graph<T> graph) throws GraphException {
        if (graph == null)
            throw new GraphException("Graph can't be null");

        List<List<T>> result = new ArrayList<>();

        Stack<T> dfsStack = new Stack<>();
        Stack<T> resultStack = new Stack<>();
        Deque<T> unvisited = new ArrayDeque<>(graph.getVertexs());
        dfsStack.add(graph.getVertexs().iterator().next());
        while (!dfsStack.empty() || !unvisited.isEmpty()) {
            T vertex;
            if (dfsStack.isEmpty()) {
                vertex = unvisited.peek();
                dfsStack.add(vertex);
            } else
                vertex = dfsStack.peek();

            unvisited.remove(vertex);

            boolean existUnvisitedNeighborFlag = false;
            for (Vertex<T> neighbor : graph.getConnections(vertex)) {
                if (unvisited.contains(neighbor.getValue())) {
                    dfsStack.add(neighbor.getValue());
                    existUnvisitedNeighborFlag = true;
                    break;
                }
            }

            if (existUnvisitedNeighborFlag)
                continue;

            dfsStack.pop();
            resultStack.add(vertex);
        }

        Graph<T> transponsedGraph = graph.getTransposedGraph();
        Set<T> visited = new HashSet<>();
        while (!resultStack.isEmpty()) {
            T vertex = resultStack.pop();
            if (!visited.contains(vertex)) {
                List<T> component = GraphAlgorithmsA.dfs(transponsedGraph, vertex);
                component.removeAll(visited);
                visited.addAll(component);
                result.add(component);
            }
        }

        result.sort((x, y) -> Integer.compare(y.size(), x.size()));
        return result;
    }
}