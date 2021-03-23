package dhbw.graphmetrics.tryout;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;

public class TryOutMain {
    public static void main(String[] args) {
        Graph<String, String> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addNode("München");
        graph.addNode("Nürnberg");
        graph.addNode("Hamburg");
        graph.addNode("Berlin");
        graph.addNode("Köln");
        graph.addEdge("München", "Nürnberg", "200 km");
        graph.addEdge("Nürnberg", "Berlin", "500 km");
        graph.addEdge("Berlin", "Hamburg", "300 km");
        graph.addEdge("Hamburg", "München", "700 km");
        graph.addEdge("Köln", "Nürnberg", "400 km");
        graph.printOutGraph();
        graph.deleteEdge("München", "Nürnberg");
        graph.printOutGraph();
        graph.deleteNode("Nürnberg");
        graph.printOutGraph();
    }
}
