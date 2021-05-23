package dhbw.graphmetrics.graph.factory;

import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.exceptions.GraphCreationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Factory class offering methods for creating special default graphs.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultGraphFactory {

    public static final String EQUAL_NO_OF_NODES_EDGE_MARKINGS_ERROR = "Same number of nodes as edge markings";

    /**
     * Method creating a circle graph.
     * @param nodes list of nodes in circle graph (order is relevant)
     * @param edgeMarkings list of edge markings for the circle graph (order is relevant)
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return circle graph (simple and undirected)
     */
    public static <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> circleGraph(List<N> nodes, List<E> edgeMarkings) {
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

    /**
     * Method creating a complete graph
     * @param nodes collection of nodes in complete graph
     * @param edgeMarking default edge marking for all created edges
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return complete graph
     */
    public static <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> completeGraph(Collection<N> nodes, E edgeMarking) {
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

    /**
     * Method creating a complete bipartite graph
     * @param firstNodeSet first not connected node set
     * @param secondNodeSet second not connected node set
     * @param edgeMarking default edge marking for all edges
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return complete bipartite graph
     */
    public static <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> completeBipartiteGraph(
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

    /**
     * Method creating a line graph
     * @param nodes list of nodes for line graph
     * @param edgeMarking default edge marking for all edges in graph
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return line graph
     */
    public static <N extends Comparable<N>, E> SimpleUndirectedAdjacencyListGraph<N, E> lineGraph(List<N> nodes, E edgeMarking) {
        SimpleUndirectedAdjacencyListGraph<N, E> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addAllNodes(nodes);
        for (int i = 0; i < nodes.size() - 1; i++) {
            graph.addEdge(nodes.get(i), nodes.get(i + 1), edgeMarking);
        }
        return graph;
    }
}
