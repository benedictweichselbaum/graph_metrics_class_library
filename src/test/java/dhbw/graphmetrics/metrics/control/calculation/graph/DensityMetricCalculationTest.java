package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DensityMetricCalculationTest extends AbstractTest {

    @Test
    void density() {
        Graph<Integer, Integer> testGraph = DefaultGraphFactory.completeGraph(Arrays.asList(1, 2, 3), null);
        Assertions.assertEquals(1.0, DensityMetricCalculation.density(testGraph));
        testGraph.deleteEdge(1, 2);
        Assertions.assertEquals(2.0/3.0, DensityMetricCalculation.density(testGraph));
        testGraph.deleteEdge(2, 3);
        testGraph.deleteEdge(3, 1);
        Assertions.assertEquals(0, DensityMetricCalculation.density(testGraph));
    }
}