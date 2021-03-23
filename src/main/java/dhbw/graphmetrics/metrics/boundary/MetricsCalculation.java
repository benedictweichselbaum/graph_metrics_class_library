
package dhbw.graphmetrics.metrics.boundary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MetricsCalculation {

	public static <N extends Comparable<N>, E> Number calculateGraphMetric(Graph<N, E> graph, GraphMetric metric) {
		return null;
	}

	public static <N extends Comparable<N>, E> Number calculateNodeMetric(Graph<N, E> graph, N node, NodeMetric metric) {
		return null;
	}

}