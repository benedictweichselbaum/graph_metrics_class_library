package dhbw.graphmetrics.graph.factory;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.exceptions.GraphCreationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultGraphFactory {

    public static final String EQUAL_NO_OF_NODES_EDGE_MARKINGS_ERROR = "Same number of nodes as edge markings";

    public static  <N extends Comparable<N>, E> Graph<N, E> circleGraph(List<N> nodes, List<E> edgeMarkings) {
        if (nodes.size() != edgeMarkings.size()) {
            throw new GraphCreationException(EQUAL_NO_OF_NODES_EDGE_MARKINGS_ERROR);
        }
        Graph<N, E> graph = new SimpleUndirectedAdjacencyListGraph<>();
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
}
