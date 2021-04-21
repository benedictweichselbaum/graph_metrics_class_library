package dhbw.graphmetrics.graph.persistence;

import com.google.gson.Gson;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphPersistence {

    private static final String NEW_LINE = "\n";

    public static  <N extends Comparable<N>, E> void persistGraph(Graph<N, E> graph, String path, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path + fileName + ".g")) {
            fileWriter.write(getGraphAsText(graph));
        }
    }

    public static <N extends Comparable<N>, E> Graph<N, E> loadGraph(String path, Class<N> nodeType, Class<E> edgeType) throws IOException {
        File file = new File(path);
        try (InputStream inputStream = new FileInputStream(file)) {
            String data = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining(NEW_LINE));

        }
        return null;
    }

    private static <N extends Comparable<N>, E> Graph<N, E> convertTextToGraph(String graph, Class<N> nodeType, Class<E> edgeType) {
        String[] graphArray = graph.split(NEW_LINE);
        Gson gson = new Gson();
        Map<String, String> objectMap = new HashMap<>();

        Graph<N, E> newGraph = "directed".equals(graphArray[0]) ?
                new SimpleDirectedAdjacencyListGraph<>() :
                new SimpleUndirectedAdjacencyListGraph<>();
        for (int i = 1; i < graphArray.length; i++) {
            if (graphArray[i].contains("->")) {

            } else {

            }
        }
        return null;
    }

    private static <N extends Comparable<N>, E> String getGraphAsText(Graph<N, E> graph) {
        StringBuilder stringBuilder = new StringBuilder();
        if (graph instanceof DirectedGraph) {
            stringBuilder.append("directed").append(NEW_LINE);
        } else {
            stringBuilder.append("undirected").append(NEW_LINE);
        }
        int objectCounter = 1;
        Map<N, String> nodeIdentifierMap = new HashMap<>();
        Map<Edge<N, E>, String> edgeIdentifierMap = new HashMap<>();
        Map<String, String> nodeJsonMap = new HashMap<>();
        Map<String, String> edgeJsonMap = new HashMap<>();
        Gson gson = new Gson();
        for (N node : graph.nodes()) {
            nodeJsonMap.put(String.valueOf(objectCounter), gson.toJson(node));
            nodeIdentifierMap.put(node, String.valueOf(objectCounter));
            objectCounter++;
        }
        for (Edge<N, E> edge : graph.edges()) {
            edgeJsonMap.put(String.valueOf(objectCounter), gson.toJson(edge));
            edgeIdentifierMap.put(edge, String.valueOf(objectCounter));
            objectCounter++;
        }
        nodeJsonMap.forEach((k, v) -> stringBuilder.append("(").append(k).append(" ").append(v).append(")").append(NEW_LINE));
        edgeJsonMap.forEach((k, v) -> stringBuilder.append("(").append(k).append(" ").append(v).append(")").append(NEW_LINE));
        for (N node : graph.nodes()) {
            for (Edge<N, E> edge : graph.edgesFromNode(node)) {
                stringBuilder.append("(").append(nodeIdentifierMap.get(node)).append(")-[").append(edgeIdentifierMap.get(edge))
                        .append("]->(").append(nodeIdentifierMap.get(edge.getToNode())).append(")").append(NEW_LINE);
            }
        }
        return stringBuilder.toString();
    }
}
