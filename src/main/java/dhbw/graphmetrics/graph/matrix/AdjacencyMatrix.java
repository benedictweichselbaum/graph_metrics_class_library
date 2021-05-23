package dhbw.graphmetrics.graph.matrix;

import dhbw.graphmetrics.graph.edge.Edge;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Inner data structure for graph representation as adjacency matrix
 * Can be used for analytics of real graph representation.
 * @param <N> type of node
 * @param <E> type of node marking
 */
@Getter
public class AdjacencyMatrix<N extends Comparable<N>, E> {

    // Integer showing the next node index when adding a new node
    private int nextIndex = 0;

    // Map that maps a node to the index in the matrix
    private final Map<N, Integer> nodeIndexMap = new HashMap<>();

    // adjacency matrix holding the edge markings
    private final E[][] matrix;

    /**
     * Constructor of the adjacency matrix given a collection of nodes and edges
     * @param nodes collection of nodes
     * @param edges collection of edges
     */
    public AdjacencyMatrix(Collection<N> nodes, Collection<Edge<N, E>> edges) {
        this.matrix = (E[][]) new Object[nodes.size()][nodes.size()];
        for (N node : nodes) {
            nodeIndexMap.put(node, nextIndex);
            nextIndex++;
        }
        for (Edge<N, E> edge : edges) {
            matrix[nodeIndexMap.get(edge.getToNode())][nodeIndexMap.get(edge.getFromNode())] = edge.getMarking();
        }
    }
}
