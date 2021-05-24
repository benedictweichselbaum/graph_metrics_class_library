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

/**
 * Class providing methods for calculating the chromatic number of a graph
 */
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
        // special case: empty graph
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        // init list of color mappings
        List<Tuple<N, Integer>> listOfNodeColors = graph.nodes().stream()
                .map(node -> new Tuple<>(node, NO_COLOR)).collect(Collectors.toList());
        int maxHighestColor = listOfNodeColors.size();
        Solution<Integer> solution = new Solution<>(maxHighestColor);

        // start searching for solution recursively
        permuteGraphColoringsAndGetSolution(graph, listOfNodeColors, maxHighestColor, maxHighestColor, solution);
        return solution.getValue();
    }

    /**
     * Method that calculates the approximation of the chromatic number
     * Is way faster than the exact method.
     * @param graph graph to be analyzed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return approximated chromatic number
     */
    public static <N extends Comparable<N>, E> Integer chromaticNumberGreedy (Graph<N, E> graph) {
        // special case: empty graph
        if (BasicGraphMetricCalculation.order(graph) == 0) return 0;
        // sorting comparator so in the node list the node with the highest out degree comes first
        Comparator<Tuple<N, Integer>> comparator = (firstTuple, secondTuple) ->
                BasicNodeMetricCalculation.outDegree(graph, secondTuple.getFirstObject()) -
                        BasicNodeMetricCalculation.outDegree(graph, firstTuple.getFirstObject());
        List<Tuple<N, Integer>> sortedNodesWithColor = new ArrayList<>(graph.nodes()).stream()
                .map(node -> new Tuple<>(node, NO_COLOR)).sorted(comparator).collect(Collectors.toList());
        int highestColorUsed = 1;
        // iterate through nodes and allocate maximal possible color
        for (Tuple<N, Integer> nodeTuple : sortedNodesWithColor) {
            Integer color = lowestPossibleColor(graph, sortedNodesWithColor, nodeTuple.getFirstObject());
            nodeTuple.setSecondObject(color);
            highestColorUsed = Math.max(highestColorUsed, color);
        }
        return highestColorUsed;
    }

    /**
     * Private method searching for the greedy algorithm the lowest possible color for a given node
     * @param graph graph that gets operated on
     * @param sortedNodesWithColor list of the nodes and their colors
     * @param node node for which a color is needed
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return lowest possible color
     */
    private static <N extends Comparable<N>, E> Integer lowestPossibleColor(Graph<N, E> graph, List<Tuple<N, Integer>> sortedNodesWithColor, N node) {
        Set<N> adjacentNodes = graph.adjacentNodes(node);
        // get the adjacent colors for the node
        List<Integer> adjacentColors = sortedNodesWithColor.stream()
                .filter(nodeWithColor -> adjacentNodes.contains(nodeWithColor.getFirstObject()))
                .map(Tuple::getSecondObject).distinct().collect(Collectors.toList());
        int maxColor = graph.nodes().size();
        // choose lowest number not in the list of adjacent colors
        for (int color = 1; color <= maxColor; ++color) {
            if (!adjacentColors.contains(color)) return color;
        }
        return maxColor;
    }

    /**
     * Recursive method permuting through all possible colorings of the graph and updating the solution object
     * @param graph graph to be analyzed
     * @param setOfNodeColorings nodes and their colors
     * @param level recursive level (highest level = number of nodes - 1)
     * @param maxHighestColor highest possible color (= number of nodes)
     * @param solution solution object that gets updated
     * @param <N> type of node
     * @param <E> type of edge marking
     */
    private static <N extends Comparable<N>, E> void permuteGraphColoringsAndGetSolution(Graph<N, E> graph,
                                                                                         List<Tuple<N, Integer>> setOfNodeColorings,
                                                                                         int level,
                                                                                         int maxHighestColor,
                                                                                         Solution<Integer> solution) {
        // base case: last level; check if coloring is legal, if so check if its a better solution than the last
        if (level == 0) {
            if (isLegalColoring(graph, setOfNodeColorings)) {
                solution.setValue(Math.min(solution.getValue(), numberOfDistinctColorings(setOfNodeColorings)));
            }
        /*
            recursive case: not last level; iterate through color combinations of the node of the corresponding level
            call method again with lower level --> trying all possible color combinations
        */
        } else {
            for (int i = 1; i <= maxHighestColor; i++) {
                setOfNodeColorings.get(level - 1).setSecondObject(i);
                permuteGraphColoringsAndGetSolution(graph, setOfNodeColorings, level - 1, maxHighestColor, solution);
            }
        }
    }

    /**
     * Private method checking if coloring for a graph is legal
     * @param graph corresponding graph
     * @param setOfNodeColorings nodes and colorings of nodes
     * @param <N> type of node
     * @param <E> type of edge marking
     * @return true if coloring is legal, false else
     */
    private static <N extends Comparable<N>, E> boolean isLegalColoring (Graph<N, E> graph,
                                                                      List<Tuple<N, Integer>> setOfNodeColorings) {
        boolean isLegal = true;
        // go through all nodes of graph
        for (N node : graph.nodes()) {
            // get color of node
            Integer nodeColor = getColorOfSpecificNode(setOfNodeColorings, node);
            for (N adjacentNode : graph.adjacentNodes(node)) {
                // if colors match the coloring is not legal
                if (getColorOfSpecificNode(setOfNodeColorings, adjacentNode).equals(nodeColor)) {
                    isLegal = false;
                }
            }
        }
        return isLegal;
    }

    /**
     * Private method providing the color of a node in a graph given the color allocation list
     * @param setOfNodeColorings list with nodes and their colors
     * @param node node for which the color is needed
     * @param <N> type of node
     * @return color as integer
     */
    private static <N extends Comparable<N>> Integer getColorOfSpecificNode(List<Tuple<N, Integer>> setOfNodeColorings, N node) {
        return setOfNodeColorings.stream().filter(nodeAndColor ->
                nodeAndColor.getFirstObject().equals(node)).findFirst().orElseThrow(() ->
                new MetricCalculationException(NODE_NOT_FOUND_IN_LIST_OF_NODE_COLOR_TUPLES_MESSAGE)).getSecondObject();
    }

    /**
     * Private method returning the amount of distinct colors in a color allocation list
     * @param setOfNodeColorings color allocation list
     * @param <N> type of node
     * @return number of distinct colorings
     */
    private static <N> Integer numberOfDistinctColorings(List<Tuple<N, Integer>> setOfNodeColorings) {
        return (int) setOfNodeColorings.stream().mapToInt(Tuple::getSecondObject).distinct().count();
    }
}
