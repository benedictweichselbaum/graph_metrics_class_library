package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.graph.Graph;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchAlgorithms {

    public static <N extends Comparable<N>, E> void depthFirstSearch(Graph<N, E> graph, N node, List<N> visitedNodes) {
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
    }
}
