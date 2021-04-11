package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicGraphMetricCalculationTest extends AbstractTest {

    @Test
    void order() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(4, BasicGraphMetricCalculation.order(graph).intValue());
    }

    @Test
    void size() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.size(graph));

        Graph<Integer, Integer> graph2 = createDirectedGraphWithNumberOfNodes(4);
        graph2.addEdge(1, 2, null);
        graph2.addEdge(1, 3, null);
        graph2.addEdge(1, 4, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.size(graph2));
    }

    @Test
    void maxDegree() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        graph.addEdge(2, 3, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.maxDegree(graph));
    }

    @Test
    void minDegree() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        graph.addEdge(2, 3, null);
        Assertions.assertEquals(1, BasicGraphMetricCalculation.minDegree(graph));
    }

    @Test
    void avgDegree() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        graph.addEdge(2, 3, null);
        Assertions.assertEquals((3 + 2 + 2 + 1) / 4.0, BasicGraphMetricCalculation.avgDegree(graph));
    }

    @Test
    void numberOfComponents() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        graph.addEdge(2, 3, null);
        Assertions.assertEquals(1, BasicGraphMetricCalculation.numberOfComponents(graph));

        Graph<Integer, Integer> graph2 = createDirectedGraphWithNumberOfNodes(4);
        graph2.addEdge(1, 2, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.numberOfComponents(graph2));
    }
}