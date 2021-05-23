package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.exceptions.GraphManipulationException;
import dhbw.graphmetrics.graph.marker.SimpleGraph;
import dhbw.graphmetrics.graph.marker.UndirectedGraph;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for constructing a simple undirected adjacency list graph. Inherits methods from AdjacencyListGraph and implements
 * the methods for edge operations according to the graphs properties.
 * @param <N> type of node
 * @param <E> type of edge marking
 */
@Getter
@NoArgsConstructor
public class SimpleUndirectedAdjacencyListGraph<N extends Comparable<N>, E> extends AdjacencyListGraph<N, E> implements UndirectedGraph, SimpleGraph {

	@Override
	public Graph<N, E> addEdge(N from, N to, E marking) {
		if (!this.containsEdge(from, to) && this.containsNode(from) && this.nodesAreNotEqual(from, to)) {
			N fromNodeInGraph = this.findEqualNode(from);
			N toNodeInGraph = this.findEqualNode(to);
			this.nodeNeighbourMap.get(fromNodeInGraph).add(new Edge<>(fromNodeInGraph, toNodeInGraph, marking));
			this.nodeNeighbourMap.get(toNodeInGraph).add(new Edge<>(toNodeInGraph, fromNodeInGraph, marking));

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
			this.deleteEdgeFromAdjacencyList(this.nodeNeighbourMap.get(toNodeInGraph), fromNodeInGraph);

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
			return this.adjacencyListHasEdgeTo(this.nodeNeighbourMap.get(fromNodeInGraph), toNodeInGraph) &&
					this.adjacencyListHasEdgeTo(this.nodeNeighbourMap.get(toNodeInGraph), fromNodeInGraph);
		}
		return false;
	}
}