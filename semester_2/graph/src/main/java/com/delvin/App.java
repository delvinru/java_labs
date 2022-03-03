package com.delvin;

public class App {
    public static void main(String[] args) throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 1, 0, 7 },
                { 0, 0, 3, 0 },
                { 0, 0, 0, 0 },
                { 0, 3, 0, 0 },
        };

        Graph<Integer> graph = new Graph<>(matrix);
        System.out.println(graph);

        boolean reshDfs = GraphAlgorithmsA.dfs(graph, 1, 4);
        System.out.print("DFS: ");
        System.out.println(reshDfs);

        System.out.print("BFS: ");
        boolean resBfs = GraphAlgorithmsA.bfs(graph, 1, 3);
        System.out.println(resBfs);

        System.out.println("Dijkstra: ");
        int[] dist = GraphAlgorithmsA.dijkstra(graph, 0);
        for (int el : dist)
            System.out.print(el + " ");
    }
}
