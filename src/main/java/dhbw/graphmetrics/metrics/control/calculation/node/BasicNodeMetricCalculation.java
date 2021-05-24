package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.graph.marker.UndirectedGraph;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class providing methods for calculating basic node metrics
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicNodeMetricCalculation {

    public static final String DEGREE_ERROR_MESSAGE = "Graph has to be marked as directed or undirected";

    /**
     * Method calculating the degree of the node
     * @param graph graph to be analyzed
     * @param node node to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return degree of node
     */
    public static <N extends Comparable<N>, E> Integer degree(Graph<N, E> graph, N node) {
        if (graph instanceof DirectedGraph) {
            return outDegree(graph, node) + inDegree(graph, node);
        } else if (graph instanceof UndirectedGraph) {
            return graph.adjacentNodes(node).size();
        } else {
            throw new MetricCalculationException(DEGREE_ERROR_MESSAGE);
        }
    }

    /**
     * Method calculating the out degree of the node
     * @param graph graph to be analyzed
     * @param node node to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return out degree of node
     */
    public static <N extends Comparable<N>, E> Integer outDegree(Graph<N, E> graph, N node) {
        return graph.adjacentNodes(node).size();
    }

    /**
     * Method calculating the in degree of the node
     * @param graph graph to be analyzed
     * @param node node to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return in degree of node
     */
    public static <N extends Comparable<N>, E> Integer inDegree(Graph<N, E> graph, N node) {
        return Math.toIntExact(graph.edges().stream().filter(edge ->
                edge.getToNode().compareTo(graph.findEqualNode(node)) == Graph.DEFAULT_COMPARE_TO_EQUALITY_VALUE).count());
    }
}
