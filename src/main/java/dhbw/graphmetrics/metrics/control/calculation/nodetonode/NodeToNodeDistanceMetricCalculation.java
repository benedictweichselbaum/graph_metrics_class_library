package dhbw.graphmetrics.metrics.control.calculation.nodetonode;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.metrics.control.helper.SearchAlgorithms;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Class providing methods for metrics regarding two nodes
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NodeToNodeDistanceMetricCalculation {

    public static final int INFINITY = Integer.MAX_VALUE;
    private static final Integer DEFAULT_EDGE_WEIGHT = 1;

    /**
     * Calculates the distance between two nodes with Uniform Cost Search algorithm.
     * When edge markings are integers they are used for the algorithm
     * @param graph graph to work on
     * @param from from node
     * @param to to node
     * @param <N> type of graph node
     * @param <E> type of graph edge marking
     * @return distance between two nodes
     */
    public static <N extends Comparable<N>, E> Integer distance(Graph<N, E> graph, N from, N to, boolean useEdgeMarking) {
        N realFromNode = graph.findEqualNode(from);
        N realToNode = graph.findEqualNode(to);

        List<N> visitedNodes = new ArrayList<>();

        // init priority queue with tuples storing a node and its distance from the start node (from)
        PriorityQueue<Tuple<N, Integer>> frontier =
                new PriorityQueue<>(graph.nodes().size(), Comparator.comparingInt(Tuple::getSecondObject));
        frontier.add(new Tuple<>(realFromNode, 0));

        while(!frontier.isEmpty()) {
            // get first tuple from priority queue
            Tuple<N, Integer> nextTuple = frontier.poll();
            // check if it is the goal node
            if (nextTuple.getFirstObject().compareTo(realToNode) == Graph.DEFAULT_COMPARE_TO_EQUALITY_VALUE) {
                return nextTuple.getSecondObject();
            }
            // mark node as visited
            visitedNodes.add(nextTuple.getFirstObject());
            // add all adjacent nodes to the frontier
            frontier.addAll(graph.edgesFromNode(nextTuple.getFirstObject()).stream().filter(edge ->
                    !visitedNodes.contains(edge.getToNode())).map(edge ->
                    createFrontierTuple(edge, nextTuple, useEdgeMarking)).collect(Collectors.toSet()));
        }

        // then goal not found return infinity
        return INFINITY;
    }

    /**
     * Algorithm for finding the number of shortest paths in a graph. Algorithm by Sryheni.
     * @param graph graph to work on
     * @param from from node
     * @param to to node
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return number of shortest paths between from and to in graph
     */
    public static <N extends Comparable<N>, E> Integer numberOfShortestPaths(Graph<N, E> graph, N from, N to) {
        return SearchAlgorithms.advancedBreadthFirstSearch(graph, from).getSecondObject().get(to);
    }

    /**
     * Private method creating a new frontier tuple for the UCS. If edge marking is an integer
     * the new calculated distance is based on the marking. Else the standard cost of 1 for an edge is used.
     * @param edgeToTransform edge containing the information for the adjacent node
     * @param baseTuple tuple on which the cost calculation
     * @param edgeMarkingIsInteger boolean determining if the edge marking should be used (only usable if integer
     *                             otherwise ClassCastException is thrown)
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return new frontier tuple
     */
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
