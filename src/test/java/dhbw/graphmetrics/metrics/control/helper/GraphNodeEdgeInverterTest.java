package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GraphNodeEdgeInverterTest extends AbstractTest {

    @Test
    void invertGraphForChromaticIndex() {
        Graph<Integer, Integer> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addAllNodes(Arrays.asList(1, 2, 3));
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);

        Graph<Integer, Void> newGraph = GraphNodeEdgeInverter.invertGraphForChromaticIndex(graph);
        Assertions.assertEquals(1, BasicGraphMetricCalculation.size(newGraph));
        Assertions.assertEquals(2, BasicGraphMetricCalculation.order(newGraph));
        Assertions.assertTrue(newGraph.containsEdge(1, 2));
    }
}