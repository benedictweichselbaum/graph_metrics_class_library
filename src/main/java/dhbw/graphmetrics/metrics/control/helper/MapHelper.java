package dhbw.graphmetrics.metrics.control.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapHelper {

    public static <K extends Comparable<K>, V> Map<K, V> fillMapWithDefaultValue(Collection<K> keys, V defaultValue) {
        Map<K, V> hashMap = new HashMap<>();
        keys.forEach(key -> hashMap.put(key, defaultValue));
        return hashMap;
    }

    public static <K extends Comparable<K>, V> Map<K, V> cloneMap(Map<K, V> mapToClone) {
        return mapToClone.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
