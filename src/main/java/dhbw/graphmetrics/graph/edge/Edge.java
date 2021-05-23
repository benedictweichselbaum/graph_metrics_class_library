
package dhbw.graphmetrics.graph.edge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an edge in a graph
 * @param <N> type of node
 * @param <E> type of edge marking
 */
@Getter
@Setter
@AllArgsConstructor
public class Edge<N extends Comparable<N>, E> {

	private N fromNode;

	private N toNode;

	private E marking;

}