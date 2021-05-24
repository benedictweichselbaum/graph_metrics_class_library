package dhbw.graphmetrics.metrics.boundary;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.NodeToNodeMetric;
import dhbw.graphmetrics.metrics.control.distributor.GraphMetricCalculationDistribution;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class providing the three major methods for calculating graph metrics.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MetricsCalculation {

	/**
	 * Wrapper method for calculating a graph metric regarding the whole graph
	 * @param graph graph to be analyzed
	 * @param metric wanted graph metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
	public static <N extends Comparable<N>, E> Number calculateGraphMetric(Graph<N, E> graph, GraphMetric metric) {
		return GraphMetricCalculationDistribution.distributeGraphMetricCalculation(graph, metric);
	}

	/**
	 * Wrapper method for calculating a graph metric regarding a single node in the graph
	 * @param graph graph to be analyzed
	 * @param node regarding node in the graph
	 * @param metric node metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
	public static <N extends Comparable<N>, E> Number calculateNodeMetric(Graph<N, E> graph, N node, NodeMetric metric) {
		N realNodeInGraph = graph.findEqualNode(node);
		return GraphMetricCalculationDistribution.distributeNodeMetricCalculation(graph, realNodeInGraph, metric);
	}

	/**
	 * Wrapper method for calculating a graph metric regarding two nodes in the graph
	 * @param graph graph to be analyzed
	 * @param node1 first node
	 * @param node2 second node
	 * @param nodeToNodeMetric node to node metric
	 * @param <N> type of node
	 * @param <E> type of edge marking
	 * @return metric number
	 */
	public static <N extends Comparable<N>, E> Number calculateNodeToNodeMetric(Graph<N, E> graph, N node1, N node2, NodeToNodeMetric nodeToNodeMetric) {
		N realFirstNode = graph.findEqualNode(node1);
		N realSecondNode = graph.findEqualNode(node2);
		return GraphMetricCalculationDistribution.distributeNodeToNodeMetricCalculation(graph, realFirstNode, realSecondNode, nodeToNodeMetric);
	}
}