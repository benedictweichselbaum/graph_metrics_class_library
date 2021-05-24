package dhbw.graphmetrics.metrics.control.exceptions;

/**
 * Exception class that gets thrown then calculating a metric goes wrong
 */
public class MetricCalculationException extends RuntimeException {
    public MetricCalculationException(String message) {
        super(message);
    }
}
