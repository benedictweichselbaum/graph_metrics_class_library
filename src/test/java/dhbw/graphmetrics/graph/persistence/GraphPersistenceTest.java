package dhbw.graphmetrics.graph.persistence;

import dhbw.graphmetrics.AbstractTest;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.graph.marker.UndirectedGraph;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GraphPersistenceTest extends AbstractTest {

    @Test
    void persistGraph_directed() throws Exception {
        Graph<Integer, Integer> graph = new SimpleDirectedAdjacencyListGraph<>();
        graph.addAllNodes(Arrays.asList(1, 2, 3));
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        GraphPersistence.persistGraph(graph, absolutePath + "/", "generatedGraphDirected");

        File file = new File(absolutePath + "/generatedGraphDirected.g");
        String data = "";
        try (InputStream inputStream = new FileInputStream(file)) {
            data = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
        List<String> lines = Arrays.asList(data.split("\n"));
        Assertions.assertEquals(8, lines.size());
        Assertions.assertTrue(lines.contains("directed"));
        Assertions.assertTrue(lines.contains("(1 1)"));
        Assertions.assertTrue(lines.contains("(2 2)"));
        Assertions.assertTrue(lines.contains("(3 3)"));
        Assertions.assertTrue(lines.contains("(4 1)"));
        Assertions.assertTrue(lines.contains("(5 1)"));
        Assertions.assertTrue(lines.contains("(1)-[4]->(2)") || lines.contains("(1)-[5]->(2)"));
        Assertions.assertTrue(lines.contains("(1)-[5]->(3)") || lines.contains("(1)-[4]->(3)"));
    }

    @Test
    void persistGraph_undirected() throws Exception {
        Graph<Integer, Integer> graph = new SimpleUndirectedAdjacencyListGraph<>();
        graph.addAllNodes(Arrays.asList(1, 2, 3));
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        GraphPersistence.persistGraph(graph, absolutePath + "/", "generatedGraphUndirected");

        File file = new File(absolutePath + "/generatedGraphUndirected.g");
        String data;
        try (InputStream inputStream = new FileInputStream(file)) {
            data = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
        List<String> lines = Arrays.asList(data.split("\n"));
        Assertions.assertEquals(12, lines.size());
        Assertions.assertTrue(lines.contains("undirected"));
        Assertions.assertTrue(lines.contains("(1 1)"));
        Assertions.assertTrue(lines.contains("(2 2)"));
        Assertions.assertTrue(lines.contains("(3 3)"));
        Assertions.assertTrue(lines.contains("(4 1)"));
        Assertions.assertTrue(lines.contains("(5 1)"));
        Assertions.assertTrue(lines.contains("(6 1)"));
        Assertions.assertTrue(lines.contains("(7 1)"));
    }

    @Test
    void loadGraph_directed() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources", "graphDirected.g");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        Graph<String, Integer> graph = GraphPersistence.loadGraph(absolutePath, String.class, Integer.class);

        Assertions.assertTrue(graph instanceof DirectedGraph);
        Assertions.assertEquals(9, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(10, BasicGraphMetricCalculation.size(graph));
        Assertions.assertTrue(graph.containsNode("Nürnberg"));
        Assertions.assertTrue(graph.containsNode("Hannover"));
        Assertions.assertTrue(graph.containsNode("Berlin"));
        Assertions.assertTrue(graph.containsNode("Stade"));
        Assertions.assertTrue(graph.containsNode("Stuttgart"));
        Assertions.assertTrue(graph.containsNode("Köln"));
        Assertions.assertTrue(graph.containsNode("Hamburg"));
        Assertions.assertTrue(graph.containsNode("Teststadt"));
        Assertions.assertTrue(graph.containsNode("München"));
        Assertions.assertTrue(graph.containsEdge("Nürnberg", "Berlin"));
        Assertions.assertTrue(graph.containsEdge("Berlin", "Hamburg"));
        Assertions.assertTrue(graph.containsEdge("Hamburg", "München"));
        Assertions.assertTrue(graph.containsEdge("Köln", "Nürnberg"));
        Assertions.assertTrue(graph.containsEdge("Hannover", "Teststadt"));
        Assertions.assertTrue(graph.containsEdge("Nürnberg", "Teststadt"));
        Assertions.assertTrue(graph.containsEdge("Hannover", "Hamburg"));
        Assertions.assertTrue(graph.containsEdge("Köln", "Stade"));
        Assertions.assertTrue(graph.containsEdge("Stade", "Stuttgart"));
    }

    @Test
    void loadGraph_undirected() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources", "graphUndirected.g");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        Graph<String, Integer> graph = GraphPersistence.loadGraph(absolutePath, String.class, Integer.class);

        Assertions.assertTrue(graph instanceof UndirectedGraph);
        Assertions.assertEquals(9, BasicGraphMetricCalculation.order(graph));
        Assertions.assertEquals(10, BasicGraphMetricCalculation.size(graph));
        Assertions.assertTrue(graph.containsNode("Nürnberg"));
        Assertions.assertTrue(graph.containsNode("Hannover"));
        Assertions.assertTrue(graph.containsNode("Berlin"));
        Assertions.assertTrue(graph.containsNode("Stade"));
        Assertions.assertTrue(graph.containsNode("Stuttgart"));
        Assertions.assertTrue(graph.containsNode("Köln"));
        Assertions.assertTrue(graph.containsNode("Hamburg"));
        Assertions.assertTrue(graph.containsNode("Teststadt"));
        Assertions.assertTrue(graph.containsNode("München"));
        Assertions.assertTrue(graph.containsEdge("Nürnberg", "Berlin"));
        Assertions.assertTrue(graph.containsEdge("Berlin", "Hamburg"));
        Assertions.assertTrue(graph.containsEdge("Hamburg", "München"));
        Assertions.assertTrue(graph.containsEdge("Köln", "Nürnberg"));
        Assertions.assertTrue(graph.containsEdge("Hannover", "Teststadt"));
        Assertions.assertTrue(graph.containsEdge("Nürnberg", "Teststadt"));
        Assertions.assertTrue(graph.containsEdge("Hannover", "Hamburg"));
        Assertions.assertTrue(graph.containsEdge("Köln", "Stade"));
        Assertions.assertTrue(graph.containsEdge("Stade", "Stuttgart"));
    }
}