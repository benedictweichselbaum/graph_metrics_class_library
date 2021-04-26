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
    void order_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.order(graph).intValue());
    }

    @Test
    void size() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        graph.addEdge(1, 2, null);
        graph.addEdge(1, 3, null);
        graph.addEdge(1, 4, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.size(graph));
    }

    @Test
    void size_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.size(graph));
    }

    @Test
    void size_graphWithoutEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(1);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.size(graph));
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
    void maxDegree_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.maxDegree(graph));
    }

    @Test
    void maxDegree_graphWithoutEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.maxDegree(graph));
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
    void minDegree_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.minDegree(graph));
    }

    @Test
    void minDegree_graphWithNoEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.minDegree(graph));
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
    void avgDegree_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.avgDegree(graph));
    }

    @Test
    void avgDegree_graphWithNoEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.avgDegree(graph));
    }

    @Test
    void numberOfComponents_singleComponent() {
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

    @Test
    void numberOfComponents_multipleComponents() {
        Graph<Integer, Integer> graph2 = createDirectedGraphWithNumberOfNodes(4);
        graph2.addEdge(1, 2, null);
        Assertions.assertEquals(3, BasicGraphMetricCalculation.numberOfComponents(graph2));
    }

    @Test
    void numberOfComponents_emptyGraph() {
        Graph<Integer, Integer> graph2 = createDirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(0, BasicGraphMetricCalculation.numberOfComponents(graph2));
    }
}