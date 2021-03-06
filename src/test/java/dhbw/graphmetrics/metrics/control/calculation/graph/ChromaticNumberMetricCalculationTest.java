package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ChromaticNumberMetricCalculationTest extends AbstractTest {

    @Test
    void chromaticNumberExact() {
        Graph<Integer, Integer> completeGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3, 4, 5, 6), null);
        Assertions.assertEquals(6, ChromaticNumberMetricCalculation.chromaticNumberExact(completeGraph));

        Graph<Integer, Integer> circleGraphOdd = DefaultGraphFactory.circleGraph(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 1, 1, 1, 1));
        Assertions.assertEquals(3, ChromaticNumberMetricCalculation.chromaticNumberExact(circleGraphOdd));

        Graph<Integer, Integer> circleGraphEven = DefaultGraphFactory.circleGraph(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 1, 1, 1, 1, 1));
        Assertions.assertEquals(2, ChromaticNumberMetricCalculation.chromaticNumberExact(circleGraphEven));
    }

    @Test
    void chromaticNumberExact_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(1, ChromaticNumberMetricCalculation.chromaticNumberExact(graph));
    }

    @Test
    void chromaticNumberExact_noEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(1, ChromaticNumberMetricCalculation.chromaticNumberExact(graph));
    }

    @Test
    void chromaticNumberGreedy() {
        Graph<Integer, Integer> completeGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3, 4, 5, 6), null);
        Assertions.assertEquals(6, ChromaticNumberMetricCalculation.chromaticNumberGreedy(completeGraph));

        Graph<Integer, Integer> circleGraphOdd = DefaultGraphFactory.circleGraph(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 1, 1, 1, 1));
        Assertions.assertEquals(3, ChromaticNumberMetricCalculation.chromaticNumberGreedy(circleGraphOdd));

        Graph<Integer, Integer> circleGraphEven = DefaultGraphFactory.circleGraph(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 1, 1, 1, 1, 1));
        Assertions.assertEquals(2, ChromaticNumberMetricCalculation.chromaticNumberGreedy(circleGraphEven));
    }

    @Test
    void chromaticNumberGreedy_emptyGraph() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(0);
        Assertions.assertEquals(1, ChromaticNumberMetricCalculation.chromaticNumberGreedy(graph));
    }

    @Test
    void chromaticNumberGreedy_noEdges() {
        Graph<Integer, Integer> graph = createUndirectedGraphWithNumberOfNodes(4);
        Assertions.assertEquals(1, ChromaticNumberMetricCalculation.chromaticNumberGreedy(graph));
    }
}