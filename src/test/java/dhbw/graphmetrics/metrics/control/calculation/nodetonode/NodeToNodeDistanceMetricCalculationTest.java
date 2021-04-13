package dhbw.graphmetrics.metrics.control.calculation.nodetonode;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class NodeToNodeDistanceMetricCalculationTest extends AbstractTest {

    @Test
    void distance() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4));
        testGraph.addEdge(1, 2, 2);
        testGraph.addEdge(2, 4, 2);
        testGraph.addEdge(1, 3, 1);
        testGraph.addEdge(3, 4, 2);
        testGraph.addEdge(1, 4, 5);
        Assertions.assertEquals(1, NodeToNodeDistanceMetricCalculation.distance(testGraph, 1, 4, false));
        Assertions.assertEquals(3, NodeToNodeDistanceMetricCalculation.distance(testGraph, 1, 4, true));

        Graph<Integer, String> graphWithStringMarking = new SimpleUndirectedAdjacencyListGraph<>();
        graphWithStringMarking.addAllNodes(Arrays.asList(1, 2));
        graphWithStringMarking.addEdge(1, 2, "Test");
        Assertions.assertThrows(ClassCastException.class, () -> NodeToNodeDistanceMetricCalculation.distance(graphWithStringMarking, 1, 2, true));
    }

    @Test
    void numberOfShortestPaths() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4));
        testGraph.addEdge(1, 2, 2);
        testGraph.addEdge(2, 4, 2);
        testGraph.addEdge(1, 3, 1);
        testGraph.addEdge(3, 4, 2);
        testGraph.addEdge(1, 4, 5);
        Assertions.assertEquals(2, NodeToNodeDistanceMetricCalculation.numberOfShortestPaths(testGraph, 1, 4));
    }
}