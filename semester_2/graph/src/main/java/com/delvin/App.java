package com.delvin;

public class App {
    public static void main(String[] args) throws GraphException {
        int[][] matrix = new int[][] {
                // { 17, 18, 20, 8, 16, 20, 3, 16 },
                // { 16, 12, 1, 3, 4, 11, 16, 15 },
                // { 2, 2, 19, 7, 15, 14, 4, 18 },
                // { 10, 11, 1, 14, 1, 19, 0, 13 },
                // { 6, 18, 11, 15, 15, 10, 0, 20 },
                // { 9, 15, 10, 5, 8, 15, 0, 3 },
                // { 14, 4, 18, 12, 3, 5, 19, 15 },
                // { 10, 10, 18, 8, 2, 18, 10, 9 },
        };
        Graph<Integer> graph = new Graph<>(matrix);
        Graph<Integer> kruskalMST = GraphAlgorithmsA.kruskal(graph);
        System.out.println(kruskalMST);
        System.out.println(kruskalMST.getGraphWeight());
        Graph<Integer> primMST = GraphAlgorithmsA.prim(graph);
        System.out.println(primMST);
        System.out.println(primMST.getGraphWeight());
    }
}
