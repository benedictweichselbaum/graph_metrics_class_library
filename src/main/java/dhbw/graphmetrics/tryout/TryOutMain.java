package dhbw.graphmetrics.tryout;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.NodeMetric;
import dhbw.graphmetrics.metrics.boundary.MetricsCalculation;

public class TryOutMain {
    public static void main(String[] args) {
        Graph<String, Integer> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addNode("München");
        graph.addNode("Nürnberg");
        graph.addNode("Hamburg");
        graph.addNode("Berlin");
        graph.addNode("Köln");
        graph.addNode("Stade");
        graph.addNode("Stuttgart");
        graph.addNode("Hannover");
        graph.addNode("Teststadt");
        graph.addEdge("München", "Nürnberg", 200);
        graph.addEdge("Nürnberg", "Berlin", 500);
        graph.addEdge("Berlin", "Hamburg", 300);
        graph.addEdge("Hamburg", "München", 700);
        graph.addEdge("Köln", "Nürnberg", 400);
        graph.addEdge("Hannover", "Teststadt", 50);
        graph.addEdge("Nürnberg", "Teststadt", 50);
        graph.addEdge("Hannover", "Hamburg", 100);
        graph.addEdge("Köln", "Stade", 300);
        graph.addEdge("Stade", "Stuttgart", 400);
        graph.printOutGraph();
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.NUMBER_OF_CONNECTED_COMPONENTS));
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.RADIUS));
    }
}
