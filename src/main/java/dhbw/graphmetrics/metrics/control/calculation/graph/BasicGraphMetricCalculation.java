
package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.graph.marker.UndirectedGraph;
import dhbw.graphmetrics.metrics.control.calculation.node.BasicNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import dhbw.graphmetrics.metrics.control.helper.SearchAlgorithms;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class providing methods for basic graph metrics
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicGraphMetricCalculation {

	private static final String METRIC_CALCULATION_EXCEPTION_MESSAGE = "Metric calculation failed";

	/**
	 * Method calculating the order of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return order of graph
	 */
	public static <N extends Comparable<N>, E> Integer order(Graph<N, E> graph) {
		return graph.nodes().size();
	}

	/**
	 * Method calculating the size of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return size of graph
	 */
	public static <N extends Comparable<N>, E> Integer size(Graph<N, E> graph) {
		if (graph instanceof UndirectedGraph) {
			return graph.edges().size() / 2;
		} else if (graph instanceof DirectedGraph) {
			return graph.edges().size();
		} else {
			throw new MetricCalculationException(METRIC_CALCULATION_EXCEPTION_MESSAGE);
		}
	}

	/**
	 * Method calculating the maximum degree of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return maximum degree of graph
	 */
	public static <N extends Comparable<N>, E> Integer maxDegree(Graph<N, E> graph) {
		if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
		return graph.nodes().stream().mapToInt(node ->
				BasicNodeMetricCalculation.degree(graph, node)).max()
				.orElseThrow(() -> new MetricCalculationException(METRIC_CALCULATION_EXCEPTION_MESSAGE));
	}

	/**
	 * Method calculating the minimum degree of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return minimum degree of graph
	 */
	public static <N extends Comparable<N>, E> Integer minDegree(Graph<N, E> graph) {
		if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
		return graph.nodes().stream().mapToInt(node ->
				BasicNodeMetricCalculation.degree(graph, node)).min()
				.orElseThrow(() -> new MetricCalculationException(METRIC_CALCULATION_EXCEPTION_MESSAGE));
	}

	/**
	 * Method calculating the average degree of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return average degree of graph
	 */
	public static <N extends Comparable<N>, E> Double avgDegree(Graph<N, E> graph) {
		if (BasicGraphMetricCalculation.order(graph) == 0) return 0.0;
		return graph.nodes().stream().mapToInt(node ->
				BasicNodeMetricCalculation.degree(graph, node)).average()
				.orElseThrow(() -> new MetricCalculationException(METRIC_CALCULATION_EXCEPTION_MESSAGE));
	}

	/**
	 * Method calculating the number of components of a graph
	 * @param graph graph to be analyzed
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return number of components of graph
	 */
	public static <N extends Comparable<N>, E> Integer numberOfComponents(Graph<N, E> graph) {
		Integer numberOfComponents = 0;
		List<N> visitedNodes = new ArrayList<>();
		for (N node : graph.nodes()) {
			if (!visitedNodes.contains(node)) {
				visitedNodes.addAll(SearchAlgorithms.depthFirstVisitingSearch(graph, node));
				numberOfComponents++;
			}
		}
		return numberOfComponents;
	}
}