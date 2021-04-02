package dhbw.graphmetrics.graph;


import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.matrix.AdjacencyMatrix;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Interface for a graph
 * @param <N> Type of node
 * @param <E> Type of edge marking
 */
public interface Graph<N extends Comparable<N>, E> {

	/**
	 * When nodes are compared with the compareTo method of the
	 * Comparable interface they are equal if the compareTo method
	 * returns this number.
	 */
	 int DEFAULT_COMPARE_TO_EQUALITY_VALUE = 0;

	/**
	 * Method that adds a new node into the graph
	* @param node new Graph node
	* @return this graph
	*/
	Graph<N, E> addNode(N node);

	/**
	 * Method that adds multiple
	 * @param node
	 * @return
	 */
	Graph<N, E> addAllNodes(Collection<N> node);

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
	boolean containsNode(N node);

	/**
	 * Method that checks if certain edge is in graph
	 * @param from from node
	 * @param to to node
	 * @return true, if edge is in graph else false
	 */
	boolean containsEdge(N from, N to);

	/**
	 * Method that returns the nodes of the graph as a list
	 * @return nodes in a list
	 */
	Set<N> nodes();

	/**
	 * Method that returns the edges of the graph as a list
	 * @return edges in a list
	 */
	Set<Edge<N, E>> edges();

	/**
	 * Method that returns edges from a certain node
	 * @param node node
	 * @return set of edges
	 */
	List<Edge<N, E>> edgesFromNode(N node);

	/**
	 * Method that returns all adjacent nodes of a certain node
	 * @param node node for which adjacent nodes should be found
	 * @return set of adjacent nodes
	 */
	Set<N> adjacentNodes(N node);

	/**
	 * Method that finds an equal node in the graph. Build on the compare to mechanism from Comparable<T>
	 * @param node node for comparison
	 * @return equal node in graph (reference)
	 */
	N findEqualNode(N node);

	/**
	 * Returns a representation of the graph as an adjacency matrix for analytics
	 * @return adjacency matrix representation
	 */
	AdjacencyMatrix<N, E> adjacencyMatrix();

	/**
	 * Method that prints out the graph
	 */
	void printOutGraph();
}