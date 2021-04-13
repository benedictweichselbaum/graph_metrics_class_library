package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DistanceNodeMetricCalculationTest extends AbstractTest {

    @Test
    void eccentricity() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4, 5, 6 ,7));
        testGraph.addEdge(1, 2, null);
        testGraph.addEdge(2, 3, null);
        testGraph.addEdge(2, 4, null);
        testGraph.addEdge(2, 5, null);
        testGraph.addEdge(2, 6, null);
        testGraph.addEdge(6, 7, null);
        Assertions.assertEquals(3, DistanceNodeMetricCalculation.eccentricity(testGraph, 1));
        testGraph.deleteEdge(1, 2);
        Assertions.assertEquals(Integer.MAX_VALUE, DistanceNodeMetricCalculation.eccentricity(testGraph, 1));
    }
}