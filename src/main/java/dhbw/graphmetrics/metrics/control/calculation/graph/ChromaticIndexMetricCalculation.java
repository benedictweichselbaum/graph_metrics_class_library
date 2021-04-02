package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.helper.GraphNodeEdgeInverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChromaticIndexMetricCalculation {

    public static <N extends Comparable<N>, E> Integer chromaticIndexExact(Graph<N, E> graph) {
        return ChromaticNumberMetricCalculation.chromaticNumberExact(GraphNodeEdgeInverter.invertGraphForChromaticIndex(graph));
    }

    public static <N extends Comparable<N>, E> Integer chromaticIndexGreedy(Graph<N, E> graph) {
        return ChromaticNumberMetricCalculation.chromaticNumberGreedy(GraphNodeEdgeInverter.invertGraphForChromaticIndex(graph));
    }
}
