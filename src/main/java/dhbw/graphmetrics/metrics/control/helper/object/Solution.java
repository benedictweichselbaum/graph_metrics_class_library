package dhbw.graphmetrics.metrics.control.helper.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Solution<T> {
    private T value;
}
