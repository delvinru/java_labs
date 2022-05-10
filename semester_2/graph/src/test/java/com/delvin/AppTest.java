package com.delvin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void testDFS_1() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 7, 0, 5, 0, 0, 0 },
                { 7, 0, 8, 9, 7, 0, 0 },
                { 0, 8, 0, 0, 5, 0, 0 },
                { 5, 9, 0, 0, 15, 6, 0 },
                { 0, 7, 5, 15, 0, 8, 9 },
                { 0, 0, 0, 6, 8, 0, 11 },
                { 0, 0, 0, 0, 9, 11, 0 },
        };

        Graph<Integer> graph = new Graph<>(matrix);
        assertTrue(GraphAlgorithmsA.dfs(graph, 1, 6));
    }

    @Test
    public void testDFS_2() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addConnection(1, 2);
        graph.addConnection(1, 4);
        graph.addConnection(2, 3);
        graph.addConnection(4, 2);
        assertTrue(GraphAlgorithmsA.dfs(graph, 1, 3));
    }

    @Test
    public void testDFS_3() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addConnection(1, 2);
        graph.addConnection(1, 4);
        graph.addConnection(2, 3);
        graph.addConnection(4, 2);
        assertFalse(GraphAlgorithmsA.dfs(graph, 4, 1));
    }

    @Test
    public void testBFS_1() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 7, 0, 5, 0, 0, 0 },
                { 7, 0, 8, 9, 7, 0, 0 },
                { 0, 8, 0, 0, 5, 0, 0 },
                { 5, 9, 0, 0, 15, 6, 0 },
                { 0, 7, 5, 15, 0, 8, 9 },
                { 0, 0, 0, 6, 8, 0, 11 },
                { 0, 0, 0, 0, 9, 11, 0 },
        };

        Graph<Integer> graph = new Graph<>(matrix);
        assertTrue(GraphAlgorithmsA.bfs(graph, 1, 6));
    }

    @Test
    public void testBFS_2() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addConnection(1, 2);
        graph.addConnection(1, 4);
        graph.addConnection(2, 3);
        graph.addConnection(4, 2);
        assertTrue(GraphAlgorithmsA.bfs(graph, 1, 3));
    }

    @Test
    public void testBFS_3() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addConnection(1, 2);
        graph.addConnection(1, 4);
        graph.addConnection(2, 3);
        graph.addConnection(4, 2);
        assertFalse(GraphAlgorithmsA.bfs(graph, 4, 1));
    }

    @Test
    public void testDijkstra_1() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 7, 0, 5, 0, 0, 0 },
                { 7, 0, 8, 9, 7, 0, 0 },
                { 0, 8, 0, 0, 5, 0, 0 },
                { 5, 9, 0, 0, 15, 6, 0 },
                { 0, 7, 5, 15, 0, 8, 9 },
                { 0, 0, 0, 6, 8, 0, 11 },
                { 0, 0, 0, 0, 9, 11, 0 },
        };
        Graph<Integer> graph = new Graph<>(matrix);
        assertArrayEquals(new int[] { 0, 7, 15, 5, 14, 11, 22 }, GraphAlgorithmsA.dijkstra(graph, 0));
    }

    @Test
    public void testDijkstra_2() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 9, 75, 0, 0 },
                { 9, 0, 95, 19, 42 },
                { 75, 95, 0, 51, 66 },
                { 0, 19, 51, 0, 31 },
                { 0, 42, 66, 31, 0 }
        };
        Graph<Integer> graph = new Graph<>(matrix);
        assertArrayEquals(new int[] { 0, 9, 75, 28, 51 }, GraphAlgorithmsA.dijkstra(graph, 0));
    }

    @Test
    public void testMST_1() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 7, 0, 5, 0, 0, 0 },
                { 7, 0, 8, 9, 7, 0, 0 },
                { 0, 8, 0, 0, 5, 0, 0 },
                { 5, 9, 0, 0, 15, 6, 0 },
                { 0, 7, 5, 15, 0, 8, 9 },
                { 0, 0, 0, 6, 8, 0, 11 },
                { 0, 0, 0, 0, 9, 11, 0 },
        };
        Graph<Integer> graph = new Graph<>(matrix);
        Graph<Integer> kruskalMST = GraphAlgorithmsA.kruskal(graph);
        Graph<Integer> primMST = GraphAlgorithmsA.prim(graph);

        // I'm too lazy for implement function for nice graph comparing ¯\_(ツ)_/¯
        assertEquals(39, kruskalMST.getGraphWeight());
        assertEquals(39, primMST.getGraphWeight());
        assertEquals(kruskalMST.getGraphWeight(), primMST.getGraphWeight());
        assertEquals(primMST.getEdges().size(), kruskalMST.getEdges().size());
    }

    @Test
    public void testMST_2() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 9, 75, 0, 0 },
                { 9, 0, 95, 19, 42 },
                { 75, 95, 0, 51, 66 },
                { 0, 19, 51, 0, 31 },
                { 0, 42, 66, 31, 0 }
        };
        Graph<Integer> graph = new Graph<>(matrix);
        Graph<Integer> kruskalMST = GraphAlgorithmsA.kruskal(graph);
        Graph<Integer> primMST = GraphAlgorithmsA.prim(graph);

        // I'm too lazy for implement function for nice graph comparing ¯\_(ツ)_/¯
        assertEquals(110, kruskalMST.getGraphWeight());
        assertEquals(110, primMST.getGraphWeight());
        assertEquals(kruskalMST.getGraphWeight(), primMST.getGraphWeight());
        assertEquals(primMST.getEdges().size(), kruskalMST.getEdges().size());
    }

    @Test
    public void testMST_3() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 3, 0, 2, 0, 0, 0, 0, 4 },
                { 3, 0, 0, 0, 0, 0, 0, 4, 0 },
                { 0, 0, 0, 6, 0, 1, 0, 2, 0 },
                { 2, 0, 6, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 0, 8 },
                { 0, 0, 1, 0, 0, 0, 8, 0, 0 },
                { 0, 0, 0, 0, 0, 8, 0, 0, 0 },
                { 0, 4, 2, 0, 0, 0, 0, 0, 0 },
                { 4, 0, 0, 0, 8, 0, 0, 0, 0 }
        };
        Graph<Integer> graph = new Graph<>(matrix);
        Graph<Integer> kruskalMST = GraphAlgorithmsA.kruskal(graph);
        Graph<Integer> primMST = GraphAlgorithmsA.prim(graph);

        // I'm too lazy for implement function for nice graph comparing ¯\_(ツ)_/¯
        assertEquals(25, kruskalMST.getGraphWeight());
        assertEquals(25, primMST.getGraphWeight());
        assertEquals(kruskalMST.getGraphWeight(), primMST.getGraphWeight());
        assertEquals(primMST.getEdges().size(), kruskalMST.getEdges().size());
    }

    @Test
    public void testMST_4() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 4, 0, 0, 5 },
                { 4, 0, 3, 6, 1 },
                { 0, 3, 0, 6, 2 },
                { 0, 6, 6, 0, 7 },
                { 5, 1, 2, 7, 0 }
        };
        Graph<Integer> graph = new Graph<>(matrix);
        Graph<Integer> kruskalMST = GraphAlgorithmsA.kruskal(graph);
        Graph<Integer> primMST = GraphAlgorithmsA.prim(graph);

        // I'm too lazy for implement function for nice graph comparing ¯\_(ツ)_/¯
        assertEquals(13, kruskalMST.getGraphWeight());
        assertEquals(13, primMST.getGraphWeight());
        assertEquals(kruskalMST.getGraphWeight(), primMST.getGraphWeight());
        assertEquals(primMST.getEdges().size(), kruskalMST.getEdges().size());
    }

    @Test(expected = GraphException.class)
    public void testGraphExceptions_1() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.getEdges();
    }

    @Test(expected = GraphException.class)
    public void testGraphExceptions_2() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.getVertexs();
    }

    @Test(expected = GraphException.class)
    public void testGraphExceptions_3() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.getConnections(9999);
    }

    @Test(expected = GraphException.class)
    public void testTarjanLoopGraph() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 7, 0, 5, 0, 0, 0 },
                { 7, 0, 8, 9, 7, 0, 0 },
                { 0, 8, 0, 0, 5, 0, 0 },
                { 5, 9, 0, 0, 15, 6, 0 },
                { 0, 7, 5, 15, 0, 8, 9 },
                { 0, 0, 0, 6, 8, 0, 11 },
                { 0, 0, 0, 0, 9, 11, 0 },
        };

        Graph<Integer> graph = new Graph<>(matrix);
        List<Integer> result = GraphAlgorithmsB.tarjan(graph);
    }

    @Test
    public void testTarjan_1() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(1);
        graph.addVertex(0);
        graph.addVertex(3);

        graph.addConnection(0, 2);
        graph.addConnection(0, 1);
        graph.addConnection(1, 2);
        graph.addConnection(1, 3);
        graph.addConnection(2, 3);
        graph.addConnection(2, 4);
        graph.addConnection(3, 4);

        List<Integer> testResult = new ArrayList<>();
        testResult.add(0);
        testResult.add(1);
        testResult.add(2);
        testResult.add(3);
        testResult.add(4);

        assertEquals(testResult, GraphAlgorithmsB.tarjan(graph));
    }

    @Test
    public void testTarjan_2() throws GraphException {
        int[][] matrix = new int[][] {
                { 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0 },
        };
        Graph<Integer> graph = new Graph<>(matrix);
        List<Integer> testResult = new ArrayList<>();
        testResult.add(1);
        testResult.add(3);
        testResult.add(2);
        testResult.add(4);
        testResult.add(5);
        testResult.add(6);
        assertEquals(testResult, GraphAlgorithmsB.tarjan(graph));
    }

    @Test
    public void testFleury_1() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addConnection(1, 2);
        graph.addConnection(2, 3);
        graph.addConnection(3, 4);
        graph.addConnection(4, 5);
        graph.addConnection(5, 1);
        graph.addConnection(2, 5);

        System.out.println(GraphAlgorithmsB.fleury(graph));
    }

    @Test
    public void testFindEulerCycle_1() throws GraphException {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addConnection(1, 2);
        graph.addConnection(2, 3);
        graph.addConnection(3, 4);
        graph.addConnection(4, 5);
        graph.addConnection(5, 1);
        graph.addConnection(2, 5);

        System.out.println(GraphAlgorithmsB.findEulerCycle(graph));
    }

    @Test
    public void GraphB_kosaraju_1() throws GraphException {
        Graph<Integer> graph = new Graph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addVertex(3);

        graph.addVertex(4);

        graph.addConnection(0, 2);
        graph.addConnection(2, 1);
        graph.addConnection(1, 0);
        graph.addConnection(0, 3);
        graph.addConnection(3, 4);

        // [0, 1, 2] [3] [4]
        List<ArrayList<Integer>> expect = new ArrayList<>();
        expect.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        expect.add(new ArrayList<>(Arrays.asList(3)));
        expect.add(new ArrayList<>(Arrays.asList(4)));
        assertEquals(expect, GraphAlgorithmsB.kosaraju(graph));
    }

    @Test
    public void GraphB_kosaraju_2() throws GraphException {
        Graph<Integer> graph = new Graph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addVertex(7);

        graph.addVertex(8);

        graph.addConnection(0, 1);
        graph.addConnection(1, 2);
        graph.addConnection(2, 3);
        graph.addConnection(3, 0);
        graph.addConnection(2, 4);
        graph.addConnection(4, 5);
        graph.addConnection(5, 6);
        graph.addConnection(6, 4);
        graph.addConnection(7, 6);
        graph.addConnection(7, 8);

        // [0, 1, 2] [4, 5, 7] [7] [8]
        List<ArrayList<Integer>> expect = new ArrayList<>();
        expect.add(new ArrayList<>(Arrays.asList(0, 3, 2, 1)));
        expect.add(new ArrayList<>(Arrays.asList(4, 6, 5)));
        expect.add(new ArrayList<>(Arrays.asList(7)));
        expect.add(new ArrayList<>(Arrays.asList(8)));
        assertEquals(expect, GraphAlgorithmsB.kosaraju(graph));
    }
}
