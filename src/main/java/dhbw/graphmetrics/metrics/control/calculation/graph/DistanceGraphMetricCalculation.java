package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.calculation.node.DistanceNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DistanceGraphMetricCalculation {

    private static final String RADIUS_CALCULATION_ERROR_MESSAGE = "Radius Calculation error";
    private static final String DIAMETER_CALCULATION_ERROR_MESSAGE = "Diameter Calculation error";

    public static <N extends Comparable<N>, E> Integer radius (Graph<N, E> graph) {
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        return graph.nodes().stream().mapToInt(node -> DistanceNodeMetricCalculation.eccentricity(graph, node)).min()
                .orElseThrow(() -> new MetricCalculationException(RADIUS_CALCULATION_ERROR_MESSAGE));
    }

    public static <N extends Comparable<N>, E> Integer diameter (Graph<N, E> graph) {
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        return graph.nodes().stream().mapToInt(node -> DistanceNodeMetricCalculation.eccentricity(graph, node)).max()
                .orElseThrow(() -> new MetricCalculationException(DIAMETER_CALCULATION_ERROR_MESSAGE));
    }
}
