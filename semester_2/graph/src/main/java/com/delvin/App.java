package com.delvin;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 2, 3 },
                { 0, 0, 4 },
                { 3, 0, 0 }
        };

        Graph<Integer> graph = new Graph<>(matrix);
        System.out.println(graph);
        int[][] m = graph.getAdjustmentMatrix();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++)
                System.out.print(m[i][j] + " ");
            System.out.print("\n");
        }
    }
}
