
package dhbw.graphmetrics.metrics.control.calculation.graph;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import dhbw.graphmetrics.graph.Graph;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicGraphMetricCalculation {

	public static <N extends Comparable<N>, E> Integer order(Graph<N, E> graph) {
		return graph.nodes().size();
	}
	public static <N extends Comparable<N>, E> Integer size(Graph<N, E> graph) {
		return graph.edges().size();
	}
}