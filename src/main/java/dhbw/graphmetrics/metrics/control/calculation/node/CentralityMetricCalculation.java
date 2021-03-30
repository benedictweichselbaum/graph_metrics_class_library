package dhbw.graphmetrics.metrics.control.calculation.node;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.graph.matrix.AdjacencyMatrix;
import dhbw.graphmetrics.metrics.control.calculation.graph.BasicGraphMetricCalculation;
import dhbw.graphmetrics.metrics.control.calculation.nodetonode.NodeToNodeDistanceMetricCalculation;
import dhbw.graphmetrics.metrics.control.helper.SearchAlgorithms;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CentralityMetricCalculation {

    public static final double INFINITY = Double.MAX_VALUE;
    public static final double MINIMUM_CONVERGING_DISTANCE = 0.001;

    public static <N extends Comparable<N>, E> Integer degreeCentrality(Graph<N, E> graph, N node) {
        return BasicNodeMetricCalculation.degree(graph, node);
    }

    public static <N extends Comparable<N>, E> Double closenessCentrality(Graph<N, E> graph, N node) {
        int summedDistance = 0;
        for (Integer distance : SearchAlgorithms.advancedBreadthFirstSearch(graph, node).getFirstObject().values()) {
            summedDistance += distance;
        }
        if (summedDistance == 0) {
            return INFINITY;
        }
        return (BasicGraphMetricCalculation.order(graph) - 1.0) / summedDistance;
    }

    public static <N extends Comparable<N>, E> Double betweennessCentrality(Graph<N, E> graph, N node) {
        double betweennessCentrality = 0.0;
        for (N s : graph.nodes()) {
            for (N t : graph.nodes()) {
                if (!s.equals(t) && !s.equals(node) && !t.equals(node)) {
                    betweennessCentrality += (numberOfShortestPathsThroughNode(graph, s, t, node) * 1.0) /
                            NodeToNodeDistanceMetricCalculation.numberOfShortestPaths(graph, s, t);
                }
            }
        }
        return betweennessCentrality;
    }

    public static <N extends Comparable<N>, E> Double eigenvectorCentrality(Graph<N, E> graph, N node) {
        AdjacencyMatrix<N, E> adjacencyMatrix = graph.adjacencyMatrix();
        double[][] edgeOneAdjacencyMatrix = new double[adjacencyMatrix.getMatrix().length][adjacencyMatrix.getMatrix().length];
        for (int i = 0; i < adjacencyMatrix.getMatrix().length; i++) {
            for (int j = 0; j < adjacencyMatrix.getMatrix().length; j++) {
                edgeOneAdjacencyMatrix[i][j] = adjacencyMatrix.getMatrix()[i][j] == null ? 0 : 1;
            }
        }
        double[][] eigenvectorCentralityVector = powerIteration(new Array2DRowRealMatrix(edgeOneAdjacencyMatrix),
                createStartVector(edgeOneAdjacencyMatrix.length),null);
        return eigenvectorCentralityVector[adjacencyMatrix.getNodeIndexMap().get(node)][0];
    }

    private static double[][] powerIteration(RealMatrix adjacencyMatrix, RealMatrix vector, Double previousNormalizedValue) {
        RealMatrix newVector = adjacencyMatrix.multiply(vector);
        double normalizedValue = normalizedVectorValue(newVector);
        for (int entryIndex = 0; entryIndex < newVector.getRowDimension(); entryIndex++) {
            newVector.setEntry(entryIndex, 0, newVector.getEntry(entryIndex, 0) / normalizedValue);
        }
        if (powerIterationConverged(previousNormalizedValue, normalizedValue)) {
            return newVector.getData();
        } else {
            return powerIteration(adjacencyMatrix, newVector, normalizedValue);
        }
    }

    private static boolean powerIterationConverged(Double previousNormalizedValue, Double normalizedValue) {
        if (previousNormalizedValue == null) return false;
        else return (Math.abs(previousNormalizedValue - normalizedValue) <= MINIMUM_CONVERGING_DISTANCE);
    }

    private static double normalizedVectorValue(RealMatrix vector) {
        double sum = 0.0;
        for (double vectorEntry : vector.getColumn(0)) {
            sum += Math.pow(vectorEntry, 2);
        }
        return Math.sqrt(sum);
    }

    private static RealMatrix createStartVector(int numberOfRows) {
        double[][] vector = new double[numberOfRows][1];
        for(int i = 0; i < numberOfRows; i++) {
            vector[i][0] = 1;
        }
        return new Array2DRowRealMatrix(vector);
    }

    private static <N extends Comparable<N>, E> Integer numberOfShortestPathsThroughNode(Graph<N, E> graph,
                                                                                         N fromNode, N toNode,
                                                                                         N throughNode) {
        if (NodeToNodeDistanceMetricCalculation.distance(graph, fromNode, toNode, false) <
                NodeToNodeDistanceMetricCalculation.distance(graph, fromNode, throughNode, false) +
                        NodeToNodeDistanceMetricCalculation.distance(graph, throughNode, toNode, false)) {
            return 0;
        } else {
            return NodeToNodeDistanceMetricCalculation.numberOfShortestPaths(graph, fromNode, throughNode) *
                    NodeToNodeDistanceMetricCalculation.numberOfShortestPaths(graph, throughNode, toNode);
        }
    }


}
