package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathMetricHelperTest extends AbstractTest {

    @Test
    void binomialCoefficient() {
        Assertions.assertEquals(1, MathMetricHelper.binomialCoefficient(7, 0));
        Assertions.assertEquals(20, MathMetricHelper.binomialCoefficient(6, 3));
        Assertions.assertEquals(1, MathMetricHelper.binomialCoefficient(7, 7));
    }
}