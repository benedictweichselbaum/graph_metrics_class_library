package dhbw.graphmetrics.metrics.control.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class providing methods for calculating mathematical terms
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathMetricHelper {

    /**
     * Method for calculating the binomial coefficient
     * @param n n parameter (n choose k)
     * @param k k parameter (n choose k)
     * @return binomial coefficient for n choose k
     */
    public static Long binomialCoefficient(Integer n, Integer k) {
        if (k == 0) return 1L;
        if (2 * k > n) k = n - k;
        long solution = 1L;
        for (int i = 1; i <= k; i++) {
            solution = solution * (n + 1 - i) / i;
        }
        return solution;
    }
}
