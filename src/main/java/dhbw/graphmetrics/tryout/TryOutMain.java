package dhbw.graphmetrics.tryout;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.factory.DefaultGraphFactory;
import dhbw.graphmetrics.graph.matrix.AdjacencyMatrix;
import dhbw.graphmetrics.graph.persistence.GraphPersistence;
import dhbw.graphmetrics.metrics.GraphMetric;
import dhbw.graphmetrics.metrics.boundary.MetricsCalculation;

import java.util.List;

public class TryOutMain {
    public static void main(String[] args) {
        Graph<String, Integer> graph = new SimpleDirectedAdjacencyListGraph<>();
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

        Graph<Integer, Integer> graph2 = new SimpleDirectedAdjacencyListGraph<>();
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addNode(5);
        graph2.addNode(6);
        graph2.addNode(7);
        graph2.addNode(8);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(2, 4, 1);
        graph2.addEdge(3, 4, 1);
        graph2.addEdge(3, 7, 1);
        graph2.addEdge(4, 7, 1);
        graph2.addEdge(7, 6, 1);
        graph2.addEdge(6, 5, 1);
        graph2.addEdge(5, 8, 1);
        graph2.addEdge(8, 2, 1);
        AdjacencyMatrix<Integer, Integer> adjacencyMatrix = graph2.adjacencyMatrix();
        graph2.printOutGraph();

        Graph<Integer, Integer> graph3 = new SimpleUndirectedAdjacencyListGraph<>();
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 1);
        graph3.addEdge(2, 4, 1);
        graph3.addEdge(2, 3, 1);
        graph3.addEdge(3, 4, 1);
        graph3.addEdge(1, 4, 1);


    }
}
