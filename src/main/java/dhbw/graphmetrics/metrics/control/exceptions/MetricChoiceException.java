package dhbw.graphmetrics.metrics.control.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetricChoiceException extends RuntimeException {
    public MetricChoiceException(String message) {
        super(message);
    }
}
