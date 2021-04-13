package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DistanceGraphMetricCalculationTest extends AbstractTest {

    @Test
    void radius() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4, 5, 6 ,7));
        testGraph.addEdge(1, 2, null);
        testGraph.addEdge(2, 3, null);
        testGraph.addEdge(2, 4, null);
        testGraph.addEdge(2, 5, null);
        testGraph.addEdge(2, 6, null);
        testGraph.addEdge(6, 7, null);
        Assertions.assertEquals(2, DistanceGraphMetricCalculation.radius(testGraph));
        testGraph.deleteEdge(6, 7);
        Assertions.assertEquals(Integer.MAX_VALUE, DistanceGraphMetricCalculation.radius(testGraph));
    }

    @Test
    void diameter() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4, 5, 6 ,7));
        testGraph.addEdge(1, 2, null);
        testGraph.addEdge(2, 3, null);
        testGraph.addEdge(2, 4, null);
        testGraph.addEdge(2, 5, null);
        testGraph.addEdge(2, 6, null);
        testGraph.addEdge(6, 7, null);
        Assertions.assertEquals(3, DistanceGraphMetricCalculation.diameter(testGraph));
        testGraph.deleteEdge(6, 7);
        Assertions.assertEquals(Integer.MAX_VALUE, DistanceGraphMetricCalculation.diameter(testGraph));
    }
}