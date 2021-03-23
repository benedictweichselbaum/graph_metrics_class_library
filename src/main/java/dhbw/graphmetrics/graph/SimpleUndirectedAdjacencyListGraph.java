package dhbw.graphmetrics.graph;

import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.exceptions.GraphAnalyticException;
import dhbw.graphmetrics.graph.exceptions.GraphManipulationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SimpleUndirectedAdjacencyListGraph<N extends Comparable<N>, E> implements Graph<N, E> {

	private static final String ADD_EDGE_EXCEPTION_MESSAGE = "No edge insertion possible";
	private static final String ADD_NODE_EXCEPTION_MESSAGE = "Node already exists";
	private static final String DELETE_NODE_EXCEPTION_MESSAGE = "Node does not exist";
	private static final String DELETE_EDGE_EXCEPTION_MESSAGE = "Deletion of edge not possible";
	private static final String FIND_ADJACENT_NODES_EXCEPTION_MESSAGE = "Node does not exist";

	private Map<N, List<Edge<N, E>>> nodeNeighbourMap = new HashMap<>();

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
	public Graph<N, E> addEdge(N from, N to, E marking) {
		if (!this.containsEdge(from, to) && containsNode(from) && containsNode(to)) {
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
	public boolean containsNode(N node) {
		return this.nodeNeighbourMap.containsKey(node) ||
				this.nodeNeighbourMap.keySet().stream()
						.anyMatch(compareNode -> compareNode.compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
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
	public Set<N> adjacentNodes(N node) {
		if (this.containsNode(node)) {
			return this.nodeNeighbourMap.get(this.findEqualNode(node)).stream().map(Edge::getToNode).collect(Collectors.toSet());
		} else {
			throw new GraphAnalyticException(FIND_ADJACENT_NODES_EXCEPTION_MESSAGE);
		}
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

	private boolean adjacencyListHasEdgeTo(List<Edge<N, E>> adjacencyList, N to) {
		return adjacencyList.stream().anyMatch(edge -> edge.getToNode().compareTo(to) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
	}

	private void deleteEdgeFromAdjacencyList(List<Edge<N, E>> adjacencyList, N to) {
		adjacencyList.removeIf(edge -> edge.getToNode().compareTo(to) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
	}

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
}