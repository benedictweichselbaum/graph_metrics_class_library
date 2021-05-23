package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.exceptions.GraphAnalyticException;
import dhbw.graphmetrics.graph.exceptions.GraphManipulationException;
import dhbw.graphmetrics.graph.matrix.AdjacencyMatrix;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract class for an adjacency list graph. Holding all relevant methods that do not need to be
 * overwritten by inheriting classes.
 * @param <N> type of node
 * @param <E> type of node marking
 */
@Getter
@NoArgsConstructor
public abstract class AdjacencyListGraph<N extends Comparable<N>, E> implements Graph<N, E> {

    protected static final String ADD_EDGE_EXCEPTION_MESSAGE = "No edge insertion possible";
    protected static final String ADD_NODE_EXCEPTION_MESSAGE = "Node already exists";
    protected static final String DELETE_NODE_EXCEPTION_MESSAGE = "Node does not exist";
    protected static final String DELETE_EDGE_EXCEPTION_MESSAGE = "Deletion of edge not possible";
    protected static final String FIND_ADJACENT_NODES_EXCEPTION_MESSAGE = "Node does not exist";

    protected final Map<N, List<Edge<N, E>>> nodeNeighbourMap = new HashMap<>();

    @Override
    public Graph<N, E> addNode(N node) {
        if (this.containsNode(node)) {
            throw new GraphManipulationException(ADD_NODE_EXCEPTION_MESSAGE);
        } else {
            this.nodeNeighbourMap.put(node, new ArrayList<>());
            return this;
        }
    }

    @Override
    public Graph<N, E> addAllNodes(Collection<N> nodes) {
        for (N node : nodes) {
            this.addNode(node);
        }
        return this;
    }

    @Override
    public Graph<N, E> deleteNode(N node) {
        if (this.containsNode(node)) {
            N nodeInGraph = this.findEqualNode(node);
            this.nodeNeighbourMap.remove(nodeInGraph);
            this.nodeNeighbourMap.values().forEach(adjacencyList -> {
                List<Edge<N, E>> edgesToRemove = new ArrayList<>();
                adjacencyList.forEach(edge -> {
                            if (edge.getToNode().compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE) {
                                edgesToRemove.add(edge);
                            }
                        }
                );
                adjacencyList.removeAll(edgesToRemove);
            });
            return this;
        } else {
            throw new GraphManipulationException(DELETE_NODE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean containsNode(N node) {
        if (node == null) {
            return false;
        } else {
            return this.nodeNeighbourMap.containsKey(node) ||
                    this.nodeNeighbourMap.keySet().stream()
                            .anyMatch(compareNode -> compareNode.compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
        }
    }

    @Override
    public Set<N> nodes() {
        return this.nodeNeighbourMap.keySet();
    }

    @Override
    public Set<Edge<N, E>> edges() {
        Set<Edge<N, E>> edgeSet = new HashSet<>();
        this.nodeNeighbourMap.values().forEach(edgeSet::addAll);
        return edgeSet;
    }

    @Override
    public List<Edge<N, E>> edgesFromNode(N node) {
        return this.nodeNeighbourMap.get(this.findEqualNode(node));
    }

    @Override
    public Set<N> adjacentNodes(N node) {
        if (this.containsNode(node)) {
            return this.nodeNeighbourMap.get(this.findEqualNode(node)).stream().map(Edge::getToNode).collect(Collectors.toSet());
        } else {
            throw new GraphAnalyticException(FIND_ADJACENT_NODES_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public AdjacencyMatrix<N, E> adjacencyMatrix() {
        return new AdjacencyMatrix<>(this.nodes(), this.edges());
    }

    @Override
    public N findEqualNode(N node) {
        if (this.nodeNeighbourMap.containsKey(node)) {
            return node;
        }
        return this.nodeNeighbourMap.keySet().stream().filter(
                nodeInGraph -> nodeInGraph.compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE
        ).findFirst().orElse(null);
    }

    @Override
    public void printOutGraph() {
        StringBuilder stringBuilder = new StringBuilder();
        this.nodeNeighbourMap.forEach((n, e) -> {
            stringBuilder.append(n).append(": ");
            e.forEach(edge -> stringBuilder.append(edge.getToNode()).append(";"));
            stringBuilder.append("\n");
        });
        stringBuilder.append("---------------------------------------");
        System.out.println(stringBuilder.toString());
    }

    protected boolean adjacencyListHasEdgeTo(List<Edge<N, E>> adjacencyList, N to) {
        return adjacencyList.stream().anyMatch(edge -> edge.getToNode().compareTo(to) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
    }

    protected void deleteEdgeFromAdjacencyList(List<Edge<N, E>> adjacencyList, N to) {
        adjacencyList.removeIf(edge -> edge.getToNode().compareTo(to) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
    }

    protected boolean nodesAreNotEqual(N node1, N node2) {
        return !this.findEqualNode(node1).equals(this.findEqualNode(node2));
    }
}
