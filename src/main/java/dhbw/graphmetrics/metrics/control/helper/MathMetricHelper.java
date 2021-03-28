package dhbw.graphmetrics.metrics.control.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathMetricHelper {

    public static Long binomialCoefficient(Integer n, Integer k) {
        if (2 * k > n) k = n - k;
        long solution = 1L;
        for (int i = 1; i <= k; i++) {
            solution = solution * (n + 1 - i) / i;
        }
        return solution;
    }
}
