package dhbw.graphmetrics.graph.matrix;

import dhbw.graphmetrics.graph.edge.Edge;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public class AdjacencyMatrix<N extends Comparable<N>, E> {
    private int nextIndex = 0;
    private final Map<N, Integer> nodeIndexMap = new HashMap<>();
    private final E[][] matrix;

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
