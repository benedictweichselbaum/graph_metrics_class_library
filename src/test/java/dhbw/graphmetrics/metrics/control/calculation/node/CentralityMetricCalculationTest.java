package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CentralityMetricCalculationTest extends AbstractTest {

    @Test
    void degreeCentrality() {
        Graph<Integer, Integer> testGraph = new SimpleUndirectedAdjacencyListGraph<>();
        testGraph.addAllNodes(Arrays.asList(1, 2, 3, 4));
        testGraph.addEdge(1, 2, 2);
        testGraph.addEdge(2, 4, 2);
        testGraph.addEdge(1, 3, 1);
        testGraph.addEdge(3, 4, 2);
        testGraph.addEdge(1, 4, 5);
        Assertions.assertEquals(3, CentralityMetricCalculation.degreeCentrality(testGraph, 1));
    }

    @Test
    void closenessCentrality() {
        Graph<Integer, Integer> lineGraph = new SimpleUndirectedAdjacencyListGraph<>();
        lineGraph.addAllNodes(Arrays.asList(1, 2, 3, 4, 5));
        lineGraph.addEdge(1, 2, 1);
        lineGraph.addEdge(2, 3, 1);
        lineGraph.addEdge(3, 4, 1);
        lineGraph.addEdge(4, 5, 1);
        Assertions.assertEquals(2.0 / 3.0, CentralityMetricCalculation.closenessCentrality(lineGraph, 3));
        Assertions.assertEquals(0.571428571428571428, CentralityMetricCalculation.closenessCentrality(lineGraph, 2));
        Assertions.assertEquals(0.4, CentralityMetricCalculation.closenessCentrality(lineGraph, 1));
    }

    @Test
    void betweennessCentrality() {
        Graph<Integer, Integer> lineGraph = new SimpleUndirectedAdjacencyListGraph<>();
        lineGraph.addAllNodes(Arrays.asList(1, 2, 3, 4, 5, 6));
        lineGraph.addEdge(1, 2, 1);
        lineGraph.addEdge(3, 2, 1);
        lineGraph.addEdge(3, 4, 1);
        lineGraph.addEdge(3, 5, 1);
        lineGraph.addEdge(5, 6, 1);
        Assertions.assertEquals(16, CentralityMetricCalculation.betweennessCentrality(lineGraph, 3));
        Assertions.assertEquals(8, CentralityMetricCalculation.betweennessCentrality(lineGraph, 5));
        Assertions.assertEquals(0, CentralityMetricCalculation.betweennessCentrality(lineGraph, 1));
    }

    @Test
    void eigenvectorCentrality() {
        Graph<String, Integer> graph = new SimpleDirectedAdjacencyListGraph<>();
        graph.addAllNodes(Arrays.asList("Home", "About", "Product", "Links", "Side A", "Side B", "Side C", "Side D"));
        graph.addEdge("Home", "About", 1);
        graph.addEdge("Home", "Product", 1);
        graph.addEdge("Home", "Links", 1);
        graph.addEdge("About", "Home", 1);
        graph.addEdge("Product", "Home", 1);
        graph.addEdge("Links", "Home", 1);
        graph.addEdge("Links", "Side A", 1);
        graph.addEdge("Links", "Side B", 1);
        graph.addEdge("Links", "Side C", 1);
        graph.addEdge("Links", "Side D", 1);
        graph.addEdge("Side A", "Home", 1);
        graph.addEdge("Side B", "Home", 1);
        graph.addEdge("Side C", "Home", 1);
        graph.addEdge("Side D", "Home", 1);
        Assertions.assertEquals(0.15500449912436315, CentralityMetricCalculation.eigenvectorCentrality(graph, "Side A"));
        Assertions.assertEquals(0.15500449912436315, CentralityMetricCalculation.eigenvectorCentrality(graph, "Side B"));
        Assertions.assertEquals(0.15500449912436315, CentralityMetricCalculation.eigenvectorCentrality(graph, "Side C"));
        Assertions.assertEquals(0.15500449912436315, CentralityMetricCalculation.eigenvectorCentrality(graph, "Side D"));
        Assertions.assertEquals(0.3399917659459919, CentralityMetricCalculation.eigenvectorCentrality(graph, "Links"));
        Assertions.assertEquals(0.3399917659459919, CentralityMetricCalculation.eigenvectorCentrality(graph, "About"));
        Assertions.assertEquals(0.3399917659459919, CentralityMetricCalculation.eigenvectorCentrality(graph, "Product"));
        Assertions.assertEquals(0.7463988332464078, CentralityMetricCalculation.eigenvectorCentrality(graph, "Home"));
    }

    @Test
    void pageRank() {
        Graph<String, Integer> graph = new SimpleDirectedAdjacencyListGraph<>();
        graph.addAllNodes(Arrays.asList("Home", "About", "Product", "Links", "Side A", "Side B", "Side C", "Side D"));
        graph.addEdge("Home", "About", 1);
        graph.addEdge("Home", "Product", 1);
        graph.addEdge("Home", "Links", 1);
        graph.addEdge("About", "Home", 1);
        graph.addEdge("Product", "Home", 1);
        graph.addEdge("Links", "Home", 1);
        graph.addEdge("Links", "Side A", 1);
        graph.addEdge("Links", "Side B", 1);
        graph.addEdge("Links", "Side C", 1);
        graph.addEdge("Links", "Side D", 1);
        graph.addEdge("Side A", "Home", 1);
        graph.addEdge("Side B", "Home", 1);
        graph.addEdge("Side C", "Home", 1);
        graph.addEdge("Side D", "Home", 1);
        Assertions.assertEquals(1.6847788573521636, CentralityMetricCalculation.pageRank(graph, "Home"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "About"));
        Assertions.assertEquals(2.7590091486765838, CentralityMetricCalculation.pageRank(graph, "Links"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "Product"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "Side A"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "Side B"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "Side C"));
        Assertions.assertEquals(0.6271934746439323, CentralityMetricCalculation.pageRank(graph, "Side D"));
    }
}