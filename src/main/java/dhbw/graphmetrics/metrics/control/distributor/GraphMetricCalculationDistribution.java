package dhbw.graphmetrics.metrics.control.distributor;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.ChromaticIndexMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.ChromaticNumberMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.DensityMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.DistanceGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.BasicNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.DistanceNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricChoiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphMetricCalculationDistribution {

	private static final String NOT_IMPLEMENTED_CHOICE_MESSAGE = "The chosen metric is not implemented";

	public static <N extends Comparable<N>, E> Number distributeGraphMetricCalculation(Graph<N, E> graph, GraphMetric metric) {
		switch (metric) {
			case ORDER:
				return BasicGraphMetricCalculation.order(graph);
			case SIZE:
				return BasicGraphMetricCalculation.size(graph);
			case MAX_DEGREE:
				return BasicGraphMetricCalculation.maxDegree(graph);
			case MIN_DEGREE:
				return BasicGraphMetricCalculation.minDegree(graph);
			case AVG_DEGREE:
				return BasicGraphMetricCalculation.avgDegree(graph);
			case NUMBER_OF_CONNECTED_COMPONENTS:
				return BasicGraphMetricCalculation.numberOfComponents(graph);
			case RADIUS:
				return DistanceGraphMetricCalculation.radius(graph);
			case DIAMETER:
				return DistanceGraphMetricCalculation.diameter(graph);
			case  CHROMATIC_NUMBER:
				return ChromaticNumberMetricCalculation.chromaticNumberExact(graph);
			case CHROMATIC_NUMBER_GREEDY:
				return ChromaticNumberMetricCalculation.chromaticNumberGreedy(graph);
			case CHROMATIC_INDEX:
				return ChromaticIndexMetricCalculation.chromaticIndex(graph);
			case DENSITY:
				return DensityMetricCalculation.density(graph);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}

	public static <N extends Comparable<N>, E> Number distributeNodeMetricCalculation(Graph<N, E> graph, N node, NodeMetric metric) {
		switch (metric) {
			case DEGREE:
				return BasicNodeMetricCalculation.degree(graph, node);
			case IN_DEGREE:
				return BasicNodeMetricCalculation.inDegree(graph, node);
			case OUT_DEGREE:
				return BasicNodeMetricCalculation.outDegree(graph, node);
			case ECCENTRICITY:
				return DistanceNodeMetricCalculation.eccentricity(graph, node);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}
}