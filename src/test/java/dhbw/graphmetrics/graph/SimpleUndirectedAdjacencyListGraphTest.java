package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SimpleUndirectedAdjacencyListGraphTest extends AbstractTest {

    private Graph<Integer, Integer> graph;

    @BeforeEach
    void setUp() {
        this.graph = new SimpleUndirectedAdjacencyListGraph<>();
    }

    @Test
    void addEdge() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        Assertions.assertEquals(2, this.graph.edges().size());
    }

    @Test
    void deleteEdge() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        this.graph.addEdge(1, 3, 1);
        Assertions.assertEquals(4, this.graph.edges().size());
        this.graph.deleteEdge(1, 2);
        Assertions.assertEquals(2, this.graph.edges().size());
    }

    @Test
    void containsEdge() {
        this.graph.addAllNodes(Arrays.asList(1, 2, 3));
        this.graph.addEdge(1, 2, 1);
        this.graph.addEdge(1, 3, 1);
        Assertions.assertTrue(this.graph.containsEdge(1, 2));
        Assertions.assertTrue(this.graph.containsEdge(1, 3));
        Assertions.assertTrue(this.graph.containsEdge(2, 1));
        Assertions.assertTrue(this.graph.containsEdge(3, 1));
    }
}