package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicNodeMetricCalculation {

    public static <N extends Comparable<N>, E> Integer degree(Graph<N, E> graph, N node) {
        return graph.adjacentNodes(node).size();
    }

    public static <N extends Comparable<N>, E> Integer outDegree(Graph<N, E> graph, N node) {
        return graph.adjacentNodes(node).size();
    }

    public static <N extends Comparable<N>, E> Integer inDegree(Graph<N, E> graph, N node) {
        return Math.toIntExact(graph.edges().stream().filter(edge ->
                edge.getToNode().compareTo(graph.findEqualNode(node)) == Graph.DEFAULT_COMPARE_TO_EQUALITY_VALUE).count());
    }
}
