package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BasicNodeMetricCalculationTest extends AbstractTest {

    @Test
    void degree() {
        Graph<Integer, Integer> testGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3), null);
        Assertions.assertEquals(2, BasicNodeMetricCalculation.degree(testGraph, 1));

        Graph<Integer, Integer> testGraphDirected = new SimpleDirectedAdjacencyListGraph<>();
        testGraphDirected.addAllNodes(Arrays.asList(1, 2, 3));
        testGraphDirected.addEdge(1, 2, null);
        Assertions.assertEquals(1, BasicNodeMetricCalculation.degree(testGraphDirected, 1));
    }

    @Test
    void outDegree() {
        Graph<Integer, Integer> testGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3), null);
        Assertions.assertEquals(2, BasicNodeMetricCalculation.outDegree(testGraph, 1));

        Graph<Integer, Integer> testGraphDirected = new SimpleDirectedAdjacencyListGraph<>();
        testGraphDirected.addAllNodes(Arrays.asList(1, 2, 3));
        testGraphDirected.addEdge(1, 2, null);
        Assertions.assertEquals(1, BasicNodeMetricCalculation.outDegree(testGraphDirected, 1));
        Assertions.assertEquals(0, BasicNodeMetricCalculation.outDegree(testGraphDirected, 2));
    }

    @Test
    void inDegree() {
        Graph<Integer, Integer> testGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3), null);
        Assertions.assertEquals(2, BasicNodeMetricCalculation.inDegree(testGraph, 1));

        Graph<Integer, Integer> testGraphDirected = new SimpleDirectedAdjacencyListGraph<>();
        testGraphDirected.addAllNodes(Arrays.asList(1, 2, 3));
        testGraphDirected.addEdge(1, 2, null);
        Assertions.assertEquals(0, BasicNodeMetricCalculation.inDegree(testGraphDirected, 1));
        Assertions.assertEquals(1, BasicNodeMetricCalculation.inDegree(testGraphDirected, 2));
    }
}