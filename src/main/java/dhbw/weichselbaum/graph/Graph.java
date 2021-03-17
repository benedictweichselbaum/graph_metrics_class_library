
package dhbw.weichselbaum.graph;


import dhbw.weichselbaum.graph.edge.Edge;

import java.util.List;

/**
 * Interface for a graph
 * @param <N> Type of node
 * @param <E> Type of edge marking
 */
public interface Graph<N extends Comparable<N>, E> {

	/**
	 * Method that adds a new node into the graph
	* @param node new Graph node
	* @return this graph
	*/
	Graph<N, E> addNode(N node);

	/**
	 * Method that adds edge between two nodes. Edge has a edge marking
	* @param from from node
	* @param to to node
	* @param marking edge marking for new edge
	*/
	Graph<N, E> addEdge(N from, N to, E marking);

	/**
	 * Method that deletes certain node
	* @param node to delete
	* @return this graph
	*/
	Graph<N, E> deleteNode(N node);

	/**
	 * Method that deletes certain edge
	* @param from from node
	* @param to to node
	* @return this graph
	*/
	Graph<N, E> deleteEdge(N from, N to);

	/**
	 * Method that checks if node is in graph
	* @param node searched for node
	* @return true, if node is in graph else false
	*/
	boolean contains(N node);

	/**
	 * Method that returns the nodes of the graph as a list
	 * @return nodes in a list
	 */
	List<N> nodes();

	/**
	 * Method that returns the edges of the graph as a list
	 * @return edges in a list
	 */
	List<Edge<N, E>> edges();
}