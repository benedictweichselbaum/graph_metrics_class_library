package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.calculation.nodetonode.NodeToNodeDistanceMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DistanceNodeMetricCalculation {

    public static final String ECCENTRICITY_CALCULATION_ERROR_MESSAGE = "Eccentricity calculation error";

    public static <N extends Comparable<N>, E> Integer eccentricity(Graph<N, E> graph, N node) {
        return graph.nodes().stream().mapToInt(toNode -> NodeToNodeDistanceMetricCalculation.distance(graph, node, toNode, false)).max()
                .orElseThrow(() -> new MetricCalculationException(ECCENTRICITY_CALCULATION_ERROR_MESSAGE));
    }
}
