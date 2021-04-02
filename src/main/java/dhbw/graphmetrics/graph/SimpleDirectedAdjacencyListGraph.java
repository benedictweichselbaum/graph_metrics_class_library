package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.exceptions.GraphManipulationException;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.graph.marker.SimpleGraph;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleDirectedAdjacencyListGraph<N extends Comparable<N>, E> extends AdjacencyListGraph<N, E> implements SimpleGraph, DirectedGraph {

    @Override
    public Graph<N, E> addEdge(N from, N to, E marking) {
        if (!this.containsEdge(from, to) && this.containsNode(from) && this.nodesAreNotEqual(from, to)) {
            N fromNodeInGraph = this.findEqualNode(from);
            N toNodeInGraph = this.findEqualNode(to);
            this.nodeNeighbourMap.get(fromNodeInGraph).add(new Edge<>(fromNodeInGraph, toNodeInGraph, marking));

            return this;
        } else {
            throw new GraphManipulationException(ADD_EDGE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Graph<N, E> deleteEdge(N from, N to) {
        if (this.containsEdge(from, to)) {
            N fromNodeInGraph = this.findEqualNode(from);
            N toNodeInGraph = this.findEqualNode(to);
            this.deleteEdgeFromAdjacencyList(this.nodeNeighbourMap.get(fromNodeInGraph), toNodeInGraph);

            return this;
        } else {
            throw new GraphManipulationException(DELETE_EDGE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean containsEdge(N from, N to) {
        if (this.containsNode(from) && this.containsNode(to)) {
            N fromNodeInGraph = this.findEqualNode(from);
            N toNodeInGraph = this.findEqualNode(to);
            return this.adjacencyListHasEdgeTo(this.nodeNeighbourMap.get(fromNodeInGraph), toNodeInGraph);
        }
        return false;
    }
}
