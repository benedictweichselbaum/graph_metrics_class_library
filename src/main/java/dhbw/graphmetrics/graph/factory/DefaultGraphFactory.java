package dhbw.graphmetrics.graph.factory;

import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.exceptions.GraphCreationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultGraphFactory {

    public static final String EQUAL_NO_OF_NODES_EDGE_MARKINGS_ERROR = "Same number of nodes as edge markings";

    public static  <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> circleGraph(List<N> nodes, List<E> edgeMarkings) {
        if (nodes.size() != edgeMarkings.size()) {
            throw new GraphCreationException(EQUAL_NO_OF_NODES_EDGE_MARKINGS_ERROR);
        }
        SimpleUndirectedAdjacencyListGraph<N, E> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addAllNodes(nodes);
        for (int i = 0; i < nodes.size(); i++) {
            if (i == nodes.size() - 1) {
                graph.addEdge(nodes.get(i), nodes.get(0), edgeMarkings.get(i));
            } else {
                graph.addEdge(nodes.get(i), nodes.get(i + 1), edgeMarkings.get(i));
            }
        }
        return graph;
    }

    public static  <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> completeGraph(Collection<N> nodes, E edgeMarking) {
        SimpleUndirectedAdjacencyListGraph<N, E> newGraph = new SimpleUndirectedAdjacencyListGraph<>();
        newGraph.addAllNodes(nodes);
        List<N> connectedNodes = new LinkedList<>();
        for (N node1 : nodes) {
            connectedNodes.add(node1);
            for (N node2 : nodes) {
                if (!connectedNodes.contains(node2)) {
                    newGraph.addEdge(node1, node2, edgeMarking);
                }
            }
        }
        return newGraph;
    }

    public static  <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> completeBipartiteGraph(
            Collection<N> firstNodeSet, Collection<N> secondNodeSet, E edgeMarking) {
        SimpleUndirectedAdjacencyListGraph<N, E> newGraph = new SimpleUndirectedAdjacencyListGraph<>();
        newGraph.addAllNodes(firstNodeSet);
        newGraph.addAllNodes(secondNodeSet);
        for (N nodeFromFirstSet : firstNodeSet) {
            for (N nodeFromSecondSet : secondNodeSet) {
                newGraph.addEdge(nodeFromFirstSet, nodeFromSecondSet, edgeMarking);
            }
        }
        return newGraph;
    }
}
