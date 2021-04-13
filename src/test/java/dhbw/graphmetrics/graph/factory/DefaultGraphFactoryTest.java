package dhbw.graphmetrics.graph.factory;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.helper.MathMetricHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DefaultGraphFactoryTest extends AbstractTest {

    @Test
    void circleGraph() {
        Graph<Integer, Integer> graph = DefaultGraphFactory.circleGraph(Arrays.asList(1, 2, 3), Arrays.asList(1, 1, 1));
        Assertions.assertEquals(3, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(3, BasicGraphMetricCalculation.size(graph));
        Assertions.assertTrue(graph.containsEdge(3, 1));
    }

    @Test
    void completeGraph() {
        Graph<Integer, Integer> graph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3, 4), 1);
        Assertions.assertEquals(4, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(MathMetricHelper.binomialCoefficient(4, 2), BasicGraphMetricCalculation.size(graph).longValue());
    }

    @Test
    void completeBipartiteGraph() {
        Graph<Integer, Integer> graph = DefaultGraphFactory.completeBipartiteGraph(Arrays.asList(1, 2, 3), Arrays.asList(4, 5), 1);
        Assertions.assertEquals(5, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(6, BasicGraphMetricCalculation.size(graph));
        Assertions.assertTrue(graph.containsEdge(1, 4));
        Assertions.assertTrue(graph.containsEdge(1, 5));
        Assertions.assertTrue(graph.containsEdge(2, 5));
        Assertions.assertTrue(graph.containsEdge(2, 4));
        Assertions.assertTrue(graph.containsEdge(3, 4));
        Assertions.assertTrue(graph.containsEdge(3, 5));
        Assertions.assertFalse(graph.containsEdge(1, 2));
        Assertions.assertFalse(graph.containsEdge(1, 3));
        Assertions.assertFalse(graph.containsEdge(2, 3));
        Assertions.assertFalse(graph.containsEdge(4, 5));
    }

    @Test
    void lineGraph() {
        Graph<Integer, Integer> graph = DefaultGraphFactory.lineGraph(Arrays.asList(1, 2, 3, 4), 1);
        Assertions.assertEquals(4, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(3, BasicGraphMetricCalculation.size(graph));
        Assertions.assertTrue(graph.containsEdge(1, 2));
        Assertions.assertTrue(graph.containsEdge(2, 3));
        Assertions.assertTrue(graph.containsEdge(3, 4));
        Assertions.assertFalse(graph.containsEdge(4, 1));
    }
}