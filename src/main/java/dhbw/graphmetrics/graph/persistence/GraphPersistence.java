package dhbw.graphmetrics.graph.persistence;

import com.google.gson.Gson;
import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.SimpleDirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.SimpleUndirectedAdjacencyListGraph;
import dhbw.graphmetrics.graph.edge.Edge;
import dhbw.graphmetrics.graph.exceptions.GraphCreationException;
import dhbw.graphmetrics.graph.marker.DirectedGraph;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class offering methods for persisting the graph into a file and reading a persisted graph from a file
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphPersistence {

    private static final String NEW_LINE = "\n";
    private static final String DIRECTED_KEY_WORD = "directed";
    public static final String UNDIRECTED_KEY_WORD = "undirected";
    public static final String GRAPH_FORMAT_SYNTAX_ERROR = "Graph format syntax error";

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
            return convertTextToGraph(data, nodeType, edgeType);
        }
    }

    private static <N extends Comparable<N>, E> Graph<N, E> convertTextToGraph(String graph, Class<N> nodeType, Class<E> edgeType) {
        String[] graphArray = graph.split(NEW_LINE);
        Gson gson = new Gson();
        Map<String, String> objectMap = new HashMap<>();
        boolean graphIsDirected = DIRECTED_KEY_WORD.equals(graphArray[0]);
        Graph<N, E> newGraph = graphIsDirected ?
                new SimpleDirectedAdjacencyListGraph<>() :
                new SimpleUndirectedAdjacencyListGraph<>();
        List<String[]> connections = new ArrayList<>();
        for (int i = 1; i < graphArray.length; i++) {
            String currentString = graphArray[i].trim();
            if (!(currentString.startsWith("(") && currentString.endsWith(")"))) {
                throw new GraphCreationException(GRAPH_FORMAT_SYNTAX_ERROR);
            }
            currentString = currentString.substring(1, currentString.length()-1);
            if (currentString.contains("->")) {
                String[] connectionString = currentString.split("(\\Q)-[\\E)|(\\Q]->(\\E)");
                if (connectionString.length != 3) {
                    throw new GraphCreationException(GRAPH_FORMAT_SYNTAX_ERROR);
                }
                connections.add(connectionString);
            } else {
                String[] objectString = currentString.split(" ");
                if (objectString.length != 2) {
                    throw new GraphCreationException(GRAPH_FORMAT_SYNTAX_ERROR);
                }
                objectMap.put(objectString[0], objectString[1]);
            }
        }
        Set<String> nodes = new HashSet<>();
        Map<String, N> nodeMap = new HashMap<>();
        for (String[] connection : connections) {
            nodes.add(connection[0]);
            nodes.add(connection[2]);
        }
        nodes.forEach(nodeIdentifier -> {
            N newNode = gson.fromJson(objectMap.get(nodeIdentifier), nodeType);
            newGraph.addNode(newNode);
            nodeMap.put(nodeIdentifier, newNode);
        });
        if (graphIsDirected) {
            connections.forEach(connection -> newGraph.addEdge(nodeMap.get(connection[0]), nodeMap.get(connection[2]), gson.fromJson(objectMap.get(connection[1]), edgeType)));
        } else {
            List<Tuple<String, String>> containingEdges = new ArrayList<>();
            connections = connections.stream().filter(connection -> {
                if (containingEdges.stream().noneMatch(containEdge ->
                        (containEdge.getFirstObject().equals(connection[0]) && containEdge.getSecondObject().equals(connection[2])) ||
                        (containEdge.getFirstObject().equals(connection[2]) && containEdge.getSecondObject().equals(connection[0]))
                )) {
                    containingEdges.add(new Tuple<>(connection[0], connection[2]));
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            connections.forEach(connection -> newGraph.addEdge(nodeMap.get(connection[0]), nodeMap.get(connection[2]), gson.fromJson(objectMap.get(connection[1]), edgeType)));
        }
        return newGraph;
    }

    private static <N extends Comparable<N>, E> String getGraphAsText(Graph<N, E> graph) {
        StringBuilder stringBuilder = new StringBuilder();
        if (graph instanceof DirectedGraph) {
            stringBuilder.append(DIRECTED_KEY_WORD).append(NEW_LINE);
        } else {
            stringBuilder.append(UNDIRECTED_KEY_WORD).append(NEW_LINE);
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
            edgeJsonMap.put(String.valueOf(objectCounter), gson.toJson(edge.getMarking()));
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
