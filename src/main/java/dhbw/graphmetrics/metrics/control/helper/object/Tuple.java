package dhbw.graphmetrics.metrics.control.helper.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Tuple<T, U> {
    private T firstObject;
    private U secondObject;
}
