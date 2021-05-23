package dhbw.graphmetrics.graph.exceptions;

/**
 * Exception class that gets thrown when an error occurs when calling manipulating methods of the graph
 */
public class GraphManipulationException extends RuntimeException {
    public GraphManipulationException(String message) {
        super(message);
    }
}
