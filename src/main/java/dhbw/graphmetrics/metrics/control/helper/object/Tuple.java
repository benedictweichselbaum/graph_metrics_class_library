package dhbw.graphmetrics.metrics.control.helper.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Generic tuple. Holding two objects
 * @param <T> type of first object
 * @param <U> type of second object
 */
@AllArgsConstructor
@Getter
@Setter
public class Tuple<T, U> {
    private T firstObject;
    private U secondObject;
}
