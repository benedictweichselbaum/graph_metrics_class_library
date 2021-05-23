package dhbw.graphmetrics.graph.exceptions;

/**
 * Exception class that gets thrown when an error occurs when calling graph factory methods
 */
public class GraphCreationException extends RuntimeException {
    public GraphCreationException(String message) {
        super(message);
    }
}
