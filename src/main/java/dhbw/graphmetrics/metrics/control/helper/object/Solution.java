package dhbw.graphmetrics.metrics.control.helper.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Generic solution holder.
 * @param <T> type of solution
 */
@Getter
@Setter
@AllArgsConstructor
public class Solution<T> {
    private T value;
}
