package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicNodeMetricCalculation {

    public static <N extends Comparable<N>, E> Integer degree(Graph<N, E> graph, N node) {
        return graph.adjacentNodes(node).size();
    }
}
