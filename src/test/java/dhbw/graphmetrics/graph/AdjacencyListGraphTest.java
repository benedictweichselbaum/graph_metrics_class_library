package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.matrix.AdjacencyMatrix;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest extends AbstractTest {

    private Graph<Integer, Integer> graph;

    @BeforeEach
    void setUp() {
        this.graph = new SimpleUndirectedAdjacencyListGraph<>();
    }

    @Test
    void addNode() {
        this.graph.addNode(1);
        Assertions.assertEquals(1, BasicGraphMetricCalculation.order(graph));
        Assertions.assertTrue(graph.nodes().contains(1));
    }

    @Test
    void addAllNodes() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        Assertions.assertEquals(3, BasicGraphMetricCalculation.order(graph));
        Assertions.assertTrue(graph.nodes().contains(1));
        Assertions.assertTrue(graph.nodes().contains(2));
        Assertions.assertTrue(graph.nodes().contains(3));
    }

    @Test
    void deleteNode() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.deleteNode(1);
        Assertions.assertEquals(2, BasicGraphMetricCalculation.order(graph));
        Assertions.assertFalse(graph.nodes().contains(1));
        Assertions.assertTrue(graph.nodes().contains(2));
        Assertions.assertTrue(graph.nodes().contains(3));
    }

    @Test
    void containsNode() {
        this.graph.addAllNodes(Arrays.asList(2, 3));
        Assertions.assertFalse(graph.containsNode(1));
        Assertions.assertTrue(graph.containsNode(2));
    }

    @Test
    void nodes() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        Set<Integer> nodes = this.graph.nodes();
        Assertions.assertEquals(3, nodes.size());
        Assertions.assertTrue(nodes.contains(1));
        Assertions.assertTrue(nodes.contains(2));
        Assertions.assertTrue(nodes.contains(3));
    }

    @Test
    void edges() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        Set<Edge<Integer, Integer>> edges = this.graph.edges();
        Assertions.assertEquals(2, edges.size());
        for (Edge<Integer, Integer> edge : edges) {
            Assertions.assertEquals(1, edge.getMarking());
        }
    }

    @Test
    void edgesFromNode() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        List<Edge<Integer, Integer>> edges = this.graph.edgesFromNode(1);
        Assertions.assertEquals(1, edges.size());
        for (Edge<Integer, Integer> edge : edges) {
            Assertions.assertEquals(1, edge.getMarking());
        }
    }

    @Test
    void adjacentNodes() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        this.graph.addEdge(1, 3, 1);
        Set<Integer> nodes = this.graph.adjacentNodes(1);
        Assertions.assertEquals(2, nodes.size());
    }

    @Test
    void adjacencyMatrix() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        this.graph.addEdge(1, 3, 1);
        AdjacencyMatrix<Integer, Integer> adjacencyMatrix = this.graph.adjacencyMatrix();
        Assertions.assertEquals(3, adjacencyMatrix.getNodeIndexMap().size());
    }

    @Test
    void findEqualNode() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        Assertions.assertEquals(1, this.graph.findEqualNode(1));
    }
}