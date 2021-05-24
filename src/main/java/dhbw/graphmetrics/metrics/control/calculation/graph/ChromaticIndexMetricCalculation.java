package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.helper.GraphNodeEdgeInverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class providing methods for calculating the chromatic index of a graph
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChromaticIndexMetricCalculation {

    /**
     * Method calculating the chromatic index of a graph exactly by inverting the graph and calculating the
     * chromatic number.
     * @see ChromaticNumberMetricCalculation
     * @param graph graph to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return chromatic index of graph
     */
    public static <N extends Comparable<N>, E> Integer chromaticIndexExact(Graph<N, E> graph) {
        return ChromaticNumberMetricCalculation.chromaticNumberExact(GraphNodeEdgeInverter.invertGraphForChromaticIndex(graph));
    }

    /**
     * Method calculating the chromatic index of a graph by inverting the graph and calculating the
     * chromatic number (only an approximation; see chromatic number).
     * @see ChromaticNumberMetricCalculation
     * @param graph graph to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return approximated chromatic index of graph
     */
    public static <N extends Comparable<N>, E> Integer chromaticIndexGreedy(Graph<N, E> graph) {
        return ChromaticNumberMetricCalculation.chromaticNumberGreedy(GraphNodeEdgeInverter.invertGraphForChromaticIndex(graph));
    }
}
