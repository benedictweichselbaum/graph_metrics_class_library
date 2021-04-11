package dhbw.graphmetrics;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;

public abstract class AbstractTest {
    protected Graph<Integer, Integer> createUndirectedGraphWithNumberOfNodes(int numberOfNodes) {
        Graph<Integer, Integer> graph = new SimpleUndirectedAdjacencyListGraph<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            graph.addNode(i);
        }
        return graph;
    }

    protected Graph<Integer, Integer> createDirectedGraphWithNumberOfNodes(int numberOfNodes) {
        Graph<Integer, Integer> graph = new SimpleDirectedAdjacencyListGraph<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            graph.addNode(i);
        }
        return graph;
    }
}
