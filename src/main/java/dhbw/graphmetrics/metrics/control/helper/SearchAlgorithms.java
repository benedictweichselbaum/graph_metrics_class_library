package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SearchAlgorithms {

    public static final int DEFAULT_DISTANCE = 1;

    /**
     * DFS search.
     * @param graph graph to work on
     * @param node start node
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return list of visited nodes
     */
    public static <N extends Comparable<N>, E> List<N> depthFirstVisitingSearch(Graph<N, E> graph, N node) {
        List<N> visitedNodes = new ArrayList<>();
        visitedNodes.add(node);
        Deque<N> searchDeque = new ArrayDeque<>();
        searchDeque.push(node);
        while (!searchDeque.isEmpty()) {
            N actNode = searchDeque.pop();
            for (N nonVisitedNode : graph.adjacentNodes(actNode).stream().filter(neighbouringNode ->
                    !visitedNodes.contains(neighbouringNode)).collect(Collectors.toSet())) {
                visitedNodes.add(nonVisitedNode);
                searchDeque.push(nonVisitedNode);
            }
        }

        return visitedNodes;
    }

    /**
     * BFS by Said Sryheni.
     * @param graph graph to work on
     * @param node start node
     * @param <N> type of node
     * @param <E> type edge marking
     * @return tuple of list with shortest distances and number of shortest paths
     */
    public static <N extends Comparable<N>, E> Tuple<Map<N, Integer>, Map<N, Integer>>
                                                        advancedBreadthFirstSearch(Graph<N, E> graph, N node) {
        Map<N, Integer> distances = MapHelper.fillMapWithDefaultValue(graph.nodes(), Integer.MAX_VALUE);
        Map<N, Integer> paths = MapHelper.fillMapWithDefaultValue(graph.nodes(), 0);
        distances.replace(node, 0);
        paths.replace(node, 1);
        Queue<N> searchQueue = new LinkedList<>();
        searchQueue.add(node);
        List<N> visitedNodes = new ArrayList<>();
        while (!searchQueue.isEmpty()) {
            N actNode = searchQueue.poll();
            for (N childNode : graph.adjacentNodes(actNode)) {
                if (!visitedNodes.contains(childNode)) {
                    searchQueue.add(childNode);
                    visitedNodes.add(childNode);
                }
                if (distances.get(childNode) > distances.get(actNode) + DEFAULT_DISTANCE) {
                    distances.replace(childNode, distances.get(actNode) + DEFAULT_DISTANCE);
                    paths.replace(childNode, paths.get(actNode));
                } else if (distances.get(childNode) == distances.get(actNode) + DEFAULT_DISTANCE) {
                    paths.replace(childNode, paths.get(childNode) + paths.get(actNode));
                }
            }
        }
        return new Tuple<>(distances, paths);
    }
}
