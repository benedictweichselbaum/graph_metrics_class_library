package dhbw.graphmetrics.metrics.control.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapHelper {

    public static <K extends Comparable<K>, V> Map<K, V> fillMapWithDefaultValue(Set<K> keys, V defaultValue) {
        Map<K, V> hashMap = new HashMap<>();
        for (K key : keys) {
            hashMap.put(key, defaultValue);
        }
        return hashMap;
    }

    public static <K extends Comparable<K>, V> Map<K, V> cloneMap(Map<K, V> mapToClone) {
        Map<K, V> hashMap = new HashMap<>();
        mapToClone.forEach((hashMap::put));
        return hashMap;
    }
}
