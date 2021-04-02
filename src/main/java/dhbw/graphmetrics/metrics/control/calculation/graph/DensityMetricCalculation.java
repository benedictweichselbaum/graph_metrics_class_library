package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.marker.SimpleGraph;
import dhbw.graphmetrics.graph.marker.UndirectedGraph;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import dhbw.graphmetrics.metrics.control.helper.MathMetricHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DensityMetricCalculation {

    public static final String ONLY_SIMPLE_GRAPHS_ERROR_MESSAGE =
            "Only simple graphs (no multiple edges, loops) are allowed when calculating density";
    public static final String NOT_CORRECT_GRAPH_IMPLEMENTATION_ERROR_MESSAGE =
            "Graph implementation does not implement UndirectedGraph nor DirectedGraph";

    public static <N extends Comparable<N>, E> Double density(Graph<N, E> graph) {
        if (!(graph instanceof SimpleGraph)) {
            throw new MetricCalculationException(ONLY_SIMPLE_GRAPHS_ERROR_MESSAGE);
        }
        if (graph instanceof UndirectedGraph) {
            return (BasicGraphMetricCalculation.size(graph) / (1.0 * MathMetricHelper.binomialCoefficient(BasicGraphMetricCalculation.order(graph), 2)));
        } else if (graph instanceof DirectedGraph) {
            return (BasicGraphMetricCalculation.size(graph) / (2.0 * MathMetricHelper.binomialCoefficient(BasicGraphMetricCalculation.order(graph), 2)));
        } else {
            throw new MetricCalculationException(NOT_CORRECT_GRAPH_IMPLEMENTATION_ERROR_MESSAGE);
        }
    }
}
