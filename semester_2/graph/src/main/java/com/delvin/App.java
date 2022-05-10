package com.delvin;

import java.util.*;

public class App {
    public static void main(String... argv) throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0 },
        };
        Graph<Integer> graph = new Graph<>(matrix);
        List<Integer> result = GraphAlgorithmsB.tarjan(graph);
        for (Integer el : result)
            System.out.println(el);
    }
}
