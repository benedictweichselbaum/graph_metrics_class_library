package dhbw.graphmetrics.graph.exceptions;

/**
 * Exception class that gets thrown when an error occurs when calling analytic methods of the graph
 */
public class GraphAnalyticException extends RuntimeException{
    public GraphAnalyticException(String message) {
        super(message);
    }
}
