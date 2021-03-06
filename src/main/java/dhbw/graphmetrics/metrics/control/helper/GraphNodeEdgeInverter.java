package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.edge.Edge;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class offering the method for inverting a graph
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphNodeEdgeInverter {

    /**
     * Method inverting a graph. All edges become nodes and the new nodes get connected if the original edges
     * were incident to each other.
     * @param graph graph to be inverted
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return inverted graph
     */
    public static <N extends Comparable<N>, E> Graph<Integer, Void> invertGraphForChromaticIndex(Graph<N, E> graph) {
        Set<Edge<N, E>> edges = new HashSet<>();
        Graph<Integer, Void> newGraph;

        // determine all single edges
        for (Edge<N, E> edge : graph.edges()) {
            if (edgeSetDoesNotContainSimilarEdge(edges, edge)) {
                edges.add(edge);
            }
        }
        newGraph = new SimpleUndirectedAdjacencyListGraph<>();

        // add new nodes from edges into inverted graph
        int correspondingNodeNumber = 1;
        Map<Edge<N, E>, Integer> map = new HashMap<>();
        for (Edge<N, E> edge : edges) {
            map.put(edge, correspondingNodeNumber);
            newGraph.addNode(correspondingNodeNumber);
            correspondingNodeNumber++;
        }

        // add edges into inverted graph
        for (Edge<N, E> edge1 : edges) {
            for (Edge<N, E> edge2 : edges) {
                if (edgesAreIncidentToSameNode(edge1, edge2)) {
                    int fromNode = map.get(edge1);
                    int toNode = map.get(edge2);
                    if (!newGraph.containsEdge(fromNode, toNode)) {
                        newGraph.addEdge(fromNode, toNode, null);
                    }
                }
            }
        }
        return newGraph;
    }

    private static <N extends Comparable<N>, E> boolean edgesAreIncidentToSameNode(Edge<N, E> edge1, Edge<N, E> edge2) {
        return (edge1.getToNode().equals(edge2.getToNode()) ||
                edge1.getFromNode().equals(edge2.getFromNode()) ||
                edge1.getToNode().equals(edge2.getFromNode()) ||
                edge1.getFromNode().equals(edge2.getToNode())) && !edge1.equals(edge2);
    }

    private static <N extends Comparable<N>, E> boolean edgeSetDoesNotContainSimilarEdge(Set<Edge<N, E>> edgeList, Edge<N, E> edge) {
        return edgeList.stream().noneMatch(edgeInList -> edgeInList.getToNode().equals(edge.getFromNode()) && edgeInList.getFromNode().equals(edge.getToNode()));
    }
}
