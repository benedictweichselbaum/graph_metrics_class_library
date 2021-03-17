
package dhbw.weichselbaum.graph;

import dhbw.weichselbaum.graph.edge.Edge;
import dhbw.weichselbaum.graph.exceptions.GraphManipulationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SimpleUndirectedAdjacencyListGraph<N extends Comparable<N>, E> implements Graph<N, E> {

	public static final String ADD_EDGE_EXCEPTION_MESSAGE = "No edge insertion possible";
	public static final String ADD_NODE_EXCEPTION_MESSAGE = "Node already exists";

	private Map<N, List<Edge<N, E>>> nodeNeighbourMap = new HashMap<>();

	@Override
	public Graph<N, E> addNode(N node) {
		if (this.contains(node)) {
			throw new GraphManipulationException(ADD_NODE_EXCEPTION_MESSAGE);
		} else {
			this.nodeNeighbourMap.put(node, new ArrayList<>());
			return this;
		}
	}

	@Override
	public Graph<N, E> addEdge(N from, N to, E marking) {
		List<Edge<N, E>> fromNeighbours = this.nodeNeighbourMap.get(from);
		List<Edge<N, E>> toNeighbours = this.nodeNeighbourMap.get(to);
		if (areNotNull(fromNeighbours, toNeighbours)) {
			fromNeighbours.add(new Edge<>(from, to, marking));
			toNeighbours.add(new Edge<>(to, from, marking));
			return this;
		} else {
			N equalFromNode = this.findEqualNode(from);
			N equalToNode = this.findEqualNode(to);
			if (areNotNull(equalFromNode, equalToNode)) {
				return addEdge(equalFromNode, equalToNode, marking);
			} else {
				throw new GraphManipulationException(ADD_EDGE_EXCEPTION_MESSAGE);
			}
		}
	}

	@Override
	public Graph<N, E> deleteNode(N marking) {
		return this;
	}

	@Override
	public Graph<N, E> deleteEdge(N from, N to) {
		return this;
	}

	@Override
	public boolean contains(N node) {
		return this.nodeNeighbourMap.containsKey(node) ||
				this.nodeNeighbourMap.keySet().stream()
						.anyMatch(compareNode -> compareNode.compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE);
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
	public N findEqualNode(N node) {
		return this.nodeNeighbourMap.keySet().stream().filter(
				nodeInGraph -> nodeInGraph.compareTo(node) == DEFAULT_COMPARE_TO_EQUALITY_VALUE
		).findFirst().orElse(null);
	}

	private boolean areNotNull(Object... objects) {
		return Arrays.stream(objects).noneMatch(Objects::isNull);
	}
}