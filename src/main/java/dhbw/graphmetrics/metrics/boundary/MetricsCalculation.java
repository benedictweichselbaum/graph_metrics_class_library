package dhbw.graphmetrics.metrics.boundary;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.NodeToNodeMetric;
import dhbw.graphmetrics.metrics.control.distributor.GraphMetricCalculationDistribution;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MetricsCalculation {

	public static <N extends Comparable<N>, E> Number calculateGraphMetric(Graph<N, E> graph, GraphMetric metric) {
		return GraphMetricCalculationDistribution.distributeGraphMetricCalculation(graph, metric);
	}

	public static <N extends Comparable<N>, E> Number calculateNodeMetric(Graph<N, E> graph, N node, NodeMetric metric) {
		N realNodeInGraph = graph.findEqualNode(node);
		return GraphMetricCalculationDistribution.distributeNodeMetricCalculation(graph, realNodeInGraph, metric);
	}

	public static <N extends Comparable<N>, E> Number calculateNodeToNodeMetric(Graph<N, E> graph, N node1, N node2, NodeToNodeMetric nodeToNodeMetric) {
		N realFirstNode = graph.findEqualNode(node1);
		N realSecondNode = graph.findEqualNode(node2);
		return GraphMetricCalculationDistribution.distributeNodeToNodeMetricCalculation(graph, realFirstNode, realSecondNode, nodeToNodeMetric);
	}
}