package dhbw.graphmetrics.tryout;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.boundary.MetricsCalculation;

public class TryOutMain {
    public static void main(String[] args) {
        Graph<String, String> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addNode("München");
        graph.addNode("Nürnberg");
        graph.addNode("Hamburg");
        graph.addNode("Berlin");
        graph.addNode("Köln");
        graph.addNode("Stade");
        graph.addNode("Stuttgart");
        graph.addNode("Hannover");
        graph.addNode("Teststadt");
        graph.addEdge("München", "Nürnberg", "200 km");
        graph.addEdge("Nürnberg", "Berlin", "500 km");
        graph.addEdge("Berlin", "Hamburg", "300 km");
        graph.addEdge("Hamburg", "München", "700 km");
        graph.addEdge("Köln", "Nürnberg", "400 km");
        graph.addEdge("Stade", "Stuttgart", "");
        graph.addEdge("Hannover", "Teststadt", "");
        graph.printOutGraph();
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.NUMBER_OF_CONNECTED_COMPONENTS));
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.AVG_DEGREE));
    }
}
