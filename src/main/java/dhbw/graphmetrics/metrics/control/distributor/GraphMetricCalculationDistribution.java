package dhbw.graphmetrics.metrics.control.distributor;

import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.BasicNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricChoiceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphMetricCalculationDistribution {

	private static final String NOT_IMPLEMENTED_CHOICE_MESSAGE = "The chosen metric is not implemented";

	public static <N extends Comparable<N>, E> Number distributeGraphMetricCalculation(Graph<N, E> graph, GraphMetric metric) {
		switch (metric) {
			case ORDER:
				return BasicGraphMetricCalculation.order(graph);
			case SIZE:
				return BasicGraphMetricCalculation.size(graph);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}

	public static <N extends Comparable<N>, E> Number distributeNodeMetricCalculation(Graph<N, E> graph, N node, NodeMetric metric) {
		switch (metric) {
			case DEGREE:
				return BasicNodeMetricCalculation.degree(graph, node);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}
}