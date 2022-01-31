package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class SearchAlgorithmsTest extends AbstractTest {

    @Test
    void depthFirstVisitingSearch() {
        Graph<Integer, Integer> graph = DefaultGraphFactory.lineGraph(Arrays.asList(0, 1, 2, 3, 4, 5), null);
        graph.deleteNode(3);
        List<Integer> visitedNodes = SearchAlgorithms.depthFirstVisitingSearch(graph, 2);
        Assertions.assertEquals(3, visitedNodes.size());
        Assertions.assertTrue(visitedNodes.contains(0));
        Assertions.assertTrue(visitedNodes.contains(1));
        Assertions.assertTrue(visitedNodes.contains(2));
    }

    @Test
    void advancedBreadthFirstSearch() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4));
        testGraph.addEdge(1, 2, 2);
        testGraph.addEdge(2, 4, 2);
        testGraph.addEdge(1, 3, 1);
        testGraph.addEdge(3, 4, 2);
        Tuple<Map<Integer, Integer>, Map<Integer, Integer>> solution = SearchAlgorithms.advancedBreadthFirstSearch(testGraph, 1);
        Assertions.assertEquals(2, solution.getFirstObject().get(4));
        Assertions.assertEquals(2, solution.getSecondObject().get(4));
        Assertions.assertEquals(1, solution.getFirstObject().get(3));
        Assertions.assertEquals(1, solution.getSecondObject().get(3));
    }
}