
package dhbw.weichselbaum.graph;

import dhbw.weichselbaum.graph.edge.Edge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class SimpleUndirectedAdjacencyListGraph<N extends Comparable<N>, E> implements Graph<N, E> {

	private Map<N, List<Edge<N, E>>> nodeNeighbourMap;

	@Override
	public Graph<N, E> addNode(N marking) {
		return this;
	}

	@Override
	public Graph<N, E> addEdge(N from, N to, E marking) {
		return this;
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
		return false;
	}

	@Override
	public List<N> nodes() {
		return Collections.emptyList();
	}

	@Override
	public List<Edge<N, E>> edges() {
		return Collections.emptyList();
	}
}