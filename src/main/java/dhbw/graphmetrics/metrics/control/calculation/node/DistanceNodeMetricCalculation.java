package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceNodeMetricCalculation {

    private static final Integer DEFAULT_EDGE_WEIGHT = 1;
    public static final String NO_EDGE_IN_GRAPH_ERROR_MESSAGE = "No edge in graph";
    public static final String ECCENTRICITY_CALCULATION_ERROR_MESSAGE = "Eccentricity calculation error";
    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * Calculates the distance between two nodes with Uniform Cost Search algorithm.
     * When edge markings are integers they are used for the algorithm
     * -1 when distance is infinity
     * @param graph graph to work on
     * @param from from node
     * @param to to node
     * @param <N> type of graph node
     * @param <E> type of graph edge marking
     * @return distance between two nodes
     */
    public static <N extends Comparable<N>, E> Integer distance(Graph<N, E> graph, N from, N to) {
        N realFromNode = graph.findEqualNode(from);
        N realToNode = graph.findEqualNode(to);

        List<N> visitedNodes = new ArrayList<>();

        PriorityQueue<Tuple<N, Integer>> frontier =
                new PriorityQueue<>(graph.nodes().size(), Comparator.comparingInt(Tuple::getSecondObject));
        frontier.add(new Tuple<>(realFromNode, 0));

        boolean edgeMarkingIsInteger = edgeMarkingOfGraphIsInteger(graph);

        while(!frontier.isEmpty()) {
            Tuple<N, Integer> nextTuple = frontier.poll();
            if (nextTuple.getFirstObject().compareTo(realToNode) == Graph.DEFAULT_COMPARE_TO_EQUALITY_VALUE) {
                return nextTuple.getSecondObject();
            }
            visitedNodes.add(nextTuple.getFirstObject());
            frontier.addAll(graph.edgesFromNode(nextTuple.getFirstObject()).stream().filter(edge ->
                    !visitedNodes.contains(edge.getToNode())).map(edge ->
                        createFrontierTuple(edge, nextTuple, edgeMarkingIsInteger)).collect(Collectors.toSet()));
        }
        return INFINITY;
    }

    public static <N extends Comparable<N>, E> Integer eccentricity(Graph<N, E> graph, N node) {
        return graph.nodes().stream().mapToInt(toNode -> distance(graph, node, toNode)).max()
                .orElseThrow(() -> new MetricCalculationException(ECCENTRICITY_CALCULATION_ERROR_MESSAGE));
    }

    private static <N extends Comparable<N>, E> boolean edgeMarkingOfGraphIsInteger(Graph<N, E> graph) {
        return graph.edges().stream().findFirst().orElseThrow(() ->
                new MetricCalculationException(NO_EDGE_IN_GRAPH_ERROR_MESSAGE)).getMarking() instanceof Integer;
    }

    private static <N extends Comparable<N>, E> Tuple<N, Integer> createFrontierTuple (Edge<N, E> edgeToTransform,
                                                                                       Tuple<N, Integer> baseTuple,
                                                                                       boolean edgeMarkingIsInteger) {
        if (edgeMarkingIsInteger) {
            return new Tuple<>(edgeToTransform.getToNode(), baseTuple.getSecondObject() + (Integer) edgeToTransform.getMarking());
        } else {
            return new Tuple<>(edgeToTransform.getToNode(), baseTuple.getSecondObject() + DEFAULT_EDGE_WEIGHT);
        }
    }
}
