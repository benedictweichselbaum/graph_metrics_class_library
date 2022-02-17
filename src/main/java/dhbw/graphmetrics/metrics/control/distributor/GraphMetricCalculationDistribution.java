package dhbw.graphmetrics.metrics.control.distributor;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.NodeToNodeMetric;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.ChromaticIndexMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.ChromaticNumberMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.DensityMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.graph.DistanceGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.BasicNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.CentralityMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.node.DistanceNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.nodetonode.NodeToNodeDistanceMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricChoiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Control class taking initial metric calculation requests and distributing the request to the calculation classes
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphMetricCalculationDistribution {

	private static final String NOT_IMPLEMENTED_CHOICE_MESSAGE = "The chosen metric is not implemented";

	/**
	 * Method for distributing the graph metrics
	 * @param graph graph to be analyzed
	 * @param metric graph metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
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
				return ChromaticIndexMetricCalculation.chromaticIndexExact(graph);
			case CHROMATIC_INDEX_GREEDY:
				return ChromaticIndexMetricCalculation.chromaticIndexGreedy(graph);
			case DENSITY:
				return DensityMetricCalculation.density(graph);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}

	/**
	 * Method for distributing node metric requests
	 * @param graph graph to be analyzed
	 * @param node node in graph to be analyzed
	 * @param metric node metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
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
			case DEGREE_CENTRALITY:
				return CentralityMetricCalculation.degreeCentrality(graph, node);
			case CLOSENESS_CENTRALITY:
				return CentralityMetricCalculation.closenessCentrality(graph, node);
			case CLOSENESS_CENTRALITY_DISCONNECTED:
				return CentralityMetricCalculation.closenessCentralityDisconnected(graph, node);
			case BETWEENNESS_CENTRALITY:
				return CentralityMetricCalculation.betweennessCentrality(graph, node);
			case EIGENVECTOR_CENTRALITY:
				return CentralityMetricCalculation.eigenvectorCentrality(graph, node);
			case PAGE_RANK:
				return CentralityMetricCalculation.pageRank(graph, node);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}

	/**
	 * Method for distributing node to node metric requests
	 * @param graph graph to be analyzed
	 * @param node1 first node in graph
	 * @param node2 second node in graph
	 * @param metric node to node metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
	public static <N extends Comparable<N>, E> Number distributeNodeToNodeMetricCalculation(Graph<N, E> graph, N node1, N node2, NodeToNodeMetric metric) {
		switch (metric) {
			case DISTANCE:
				return NodeToNodeDistanceMetricCalculation.distance(graph, node1, node2, false);
			case DISTANCE_BASED_ON_MARKING:
				return NodeToNodeDistanceMetricCalculation.distance(graph, node1, node2, true);
			case NUMBER_OF_SHORTEST_PATHS:
				return NodeToNodeDistanceMetricCalculation.numberOfShortestPaths(graph, node1, node2);
			default:
				throw new MetricChoiceException(NOT_IMPLEMENTED_CHOICE_MESSAGE);
		}
	}
}