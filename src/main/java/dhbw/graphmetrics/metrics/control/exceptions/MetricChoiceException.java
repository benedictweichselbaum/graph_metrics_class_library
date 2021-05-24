package dhbw.graphmetrics.metrics.control.exceptions;

/**
 * Exception class that gets thrown then a chosen metric is not implemented.
 * Should not be thrown in current library status.
 */
public class MetricChoiceException extends RuntimeException {
    public MetricChoiceException(String message) {
        super(message);
    }
}
