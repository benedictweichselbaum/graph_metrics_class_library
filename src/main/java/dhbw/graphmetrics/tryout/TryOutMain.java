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
        graph.addEdge("München", "Nürnberg", "200 km");
        graph.addEdge("Nürnberg", "Berlin", "500 km");
        graph.addEdge("Berlin", "Hamburg", "300 km");
        graph.addEdge("Hamburg", "München", "700 km");
        graph.addEdge("Köln", "Nürnberg", "400 km");
        graph.printOutGraph();
        graph.deleteEdge("München", "Nürnberg");
        graph.printOutGraph();
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.ORDER));
        graph.deleteNode("Nürnberg");
        graph.printOutGraph();
        System.out.println();
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.ORDER));
        System.out.println(MetricsCalculation.calculateNodeMetric(graph, "Hamburg", NodeMetric.DEGREE));
        System.out.println(MetricsCalculation.calculateGraphMetric(graph, GraphMetric.SIZE));
    }
}
