package dhbw.graphmetrics.metrics.control.helper;

import dhbw.graphmetrics.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

class MapHelperTest extends AbstractTest {

    @Test
    void fillMapWithDefaultValue() {
        Collection<Integer> keys = Arrays.asList(1, 2, 3);
        String defaultValue = "Test";
        Map<Integer, String> map = MapHelper.fillMapWithDefaultValue(keys, defaultValue);
        Assertions.assertTrue(map.containsKey(1));
        Assertions.assertTrue(map.containsKey(2));
        Assertions.assertTrue(map.containsKey(3));
        map.forEach((k, v) -> {
            Assertions.assertEquals(defaultValue, v);
        });
    }

    @Test
    void cloneMap() {
        Map<Integer, String> map = Map.of(1, "1", 2, "2", 3, "3", 4, "4", 5, "5", 6, "6");
        Map<Integer, String> map2 = MapHelper.cloneMap(map);
        Assertions.assertEquals(map, map2);
    }
}