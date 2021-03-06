package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ChromaticIndexMetricCalculationTest extends AbstractTest {

    @Test
    void chromaticIndexExact() {
        Graph<Integer, Integer> completeBipartiteGraph = DefaultGraphFactory.completeBipartiteGraph(Arrays.asList(1, 2, 3),
                Arrays.asList(5, 6), null);
        Assertions.assertEquals(BasicGraphMetricCalculation.maxDegree(completeBipartiteGraph),
                ChromaticIndexMetricCalculation.chromaticIndexExact(completeBipartiteGraph));

        Graph<Integer, Integer> triangleGraph = new SimpleUndirectedAdjacencyListGraph<>();
        triangleGraph.addAllNodes(Arrays.asList(1, 2, 3));
        triangleGraph.addEdge(1, 2, null);
        triangleGraph.addEdge(2, 3, null);
        triangleGraph.addEdge(3, 1, null);
        Assertions.assertEquals(BasicGraphMetricCalculation.maxDegree(triangleGraph) + 1,
                ChromaticIndexMetricCalculation.chromaticIndexExact(triangleGraph));
    }

    @Test
    void chromaticIndexExact_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(1,
                ChromaticIndexMetricCalculation.chromaticIndexExact(graph));
    }

    @Test
    void chromaticIndexExact_noEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(1,
                ChromaticIndexMetricCalculation.chromaticIndexExact(graph));
    }

    @Test
    void chromaticIndexGreedy() {
        Graph<Integer, Integer> completeBipartiteGraph = DefaultGraphFactory.completeBipartiteGraph(Arrays.asList(1, 2, 3),
                Arrays.asList(5, 6), null);
        Assertions.assertEquals(4,
                ChromaticIndexMetricCalculation.chromaticIndexGreedy(completeBipartiteGraph));

        Graph<Integer, Integer> triangleGraph = new SimpleUndirectedAdjacencyListGraph<>();
        triangleGraph.addAllNodes(Arrays.asList(1, 2, 3));
        triangleGraph.addEdge(1, 2, null);
        triangleGraph.addEdge(2, 3, null);
        triangleGraph.addEdge(3, 1, null);
        Assertions.assertEquals(BasicGraphMetricCalculation.maxDegree(triangleGraph) + 1,
                ChromaticIndexMetricCalculation.chromaticIndexGreedy(triangleGraph));
    }

    @Test
    void chromaticIndexGreedy_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(1,
                ChromaticIndexMetricCalculation.chromaticIndexGreedy(graph));
    }

    @Test
    void chromaticIndexGreedy_noEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(1,
                ChromaticIndexMetricCalculation.chromaticIndexGreedy(graph));
    }
}