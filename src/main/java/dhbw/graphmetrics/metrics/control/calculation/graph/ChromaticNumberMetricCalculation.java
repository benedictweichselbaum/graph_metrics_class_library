package dhbw.graphmetrics.metrics.control.calculation.graph;

import dhbw.graphmetrics.graph.Graph;
import dhbw.graphmetrics.metrics.control.calculation.node.BasicNodeMetricCalculation;
import dhbw.graphmetrics.metrics.control.exceptions.MetricCalculationException;
import dhbw.graphmetrics.metrics.control.helper.object.Solution;
import dhbw.graphmetrics.metrics.control.helper.object.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChromaticNumberMetricCalculation {

    public static final int NO_COLOR = 0;
    public static final String NODE_NOT_FOUND_IN_LIST_OF_NODE_COLOR_TUPLES_MESSAGE = "Node not found in list of node color tuples";

    /**
     * Method that calculates the exact chromatic number for the graph.
     * Waring: Problem is NP-complete. Calculation may take a long time
     * @param graph graph to work on
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return chromatic number of graph
     */
    public static <N extends Comparable<N>, E> Integer chromaticNumberExact (Graph<N, E> graph) {
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        List<Tuple<N, Integer>> setOfNodeColorings = graph.nodes().stream()
                .map(node -> new Tuple<>(node, NO_COLOR)).collect(Collectors.toList());
        int maxHighestColor = setOfNodeColorings.size();
        Solution<Integer> solution = new Solution<>(maxHighestColor);
        permuteGraphColoringsAndGetSolution(graph, setOfNodeColorings, maxHighestColor, maxHighestColor, solution);
        return solution.getValue();
    }

    public static <N extends Comparable<N>, E> Integer chromaticNumberGreedy (Graph<N, E> graph) {
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        Comparator<Tuple<N, Integer>> comparator = (firstTuple, secondTuple) ->
                BasicNodeMetricCalculation.outDegree(graph, secondTuple.getFirstObject()) -
                        BasicNodeMetricCalculation.outDegree(graph, firstTuple.getFirstObject());
        List<Tuple<N, Integer>> sortedNodesWithColor = new ArrayList<>(graph.nodes()).stream()
                .map(node -> new Tuple<>(node, NO_COLOR)).sorted(comparator).collect(Collectors.toList());
        int highestColorUsed = 1;
        for (Tuple<N, Integer> nodeTuple : sortedNodesWithColor) {
            Integer color = lowestPossibleColor(graph, sortedNodesWithColor, nodeTuple.getFirstObject());
            nodeTuple.setSecondObject(color);
            highestColorUsed = Math.max(highestColorUsed, color);
        }
        return highestColorUsed;
    }

    private static <N extends Comparable<N>, E> Integer lowestPossibleColor(Graph<N, E> graph, List<Tuple<N, Integer>> sortedNodesWithColor, N node) {
        Set<N> adjacentNodes = graph.adjacentNodes(node);
        List<Integer> adjacentColors = sortedNodesWithColor.stream()
                .filter(nodeWithColor -> adjacentNodes.contains(nodeWithColor.getFirstObject()))
                .map(Tuple::getSecondObject).distinct().collect(Collectors.toList());
        int maxColor = graph.nodes().size();
        for (int color = 1; color <= maxColor; ++color) {
            if (!adjacentColors.contains(color)) return color;
        }
        return maxColor;
    }

    private static <N extends Comparable<N>, E> void permuteGraphColoringsAndGetSolution(Graph<N, E> graph,
                                                                                         List<Tuple<N, Integer>> setOfNodeColorings,
                                                                                         int level,
                                                                                         int maxHighestColor,
                                                                                         Solution<Integer> solution) {
        if (level == 0) {
            if (isLegalColoring(graph, setOfNodeColorings)) {
                solution.setValue(Math.min(solution.getValue(), numberOfDistinctColorings(setOfNodeColorings)));
            }
        } else {
            for (int i = 1; i <= maxHighestColor; i++) {
                setOfNodeColorings.get(level - 1).setSecondObject(i);
                permuteGraphColoringsAndGetSolution(graph, setOfNodeColorings, level - 1, maxHighestColor, solution);
            }
        }
    }

    private static <N extends Comparable<N>, E> boolean isLegalColoring (Graph<N, E> graph,
                                                                      List<Tuple<N, Integer>> setOfNodeColorings) {
        boolean isLegal = true;
        for (N node : graph.nodes()) {
            Integer nodeColor = getColorOfSpecificNode(setOfNodeColorings, node);
            for (N adjacentNode : graph.adjacentNodes(node)) {
                if (getColorOfSpecificNode(setOfNodeColorings, adjacentNode).equals(nodeColor)) {
                    isLegal = false;
                }
            }
        }
        return isLegal;
    }

    private static <N extends Comparable<N>> Integer getColorOfSpecificNode(List<Tuple<N, Integer>> setOfNodeColorings, N node) {
        return setOfNodeColorings.stream().filter(nodeAndColor ->
                nodeAndColor.getFirstObject().equals(node)).findFirst().orElseThrow(() ->
                new MetricCalculationException(NODE_NOT_FOUND_IN_LIST_OF_NODE_COLOR_TUPLES_MESSAGE)).getSecondObject();
    }

    private static <N> Integer numberOfDistinctColorings(List<Tuple<N, Integer>> setOfNodeColorings) {
        return (int) setOfNodeColorings.stream().mapToInt(Tuple::getSecondObject).distinct().count();
    }
}
