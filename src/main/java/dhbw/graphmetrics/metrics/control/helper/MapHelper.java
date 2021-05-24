package dhbw.graphmetrics.metrics.control.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class providing methods helping with Java maps
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapHelper {

    /**
     * Method returning a map with the desired keys and a default value for every key
     * @param keys collection of keys
     * @param defaultValue default value for every key
     * @param <K> type of key
     * @param <V> type of value
     * @return created map
     */
    public static <K, V> Map<K, V> fillMapWithDefaultValue(Collection<K> keys, V defaultValue) {
        Map<K, V> hashMap = new HashMap<>();
        keys.forEach(key -> hashMap.put(key, defaultValue));
        return hashMap;
    }

    /**
     * Method cloning a map
     * @param mapToClone map that needs to be cloned
     * @param <K> type of key
     * @param <V> type of value
     * @return cloned map
     */
    public static <K, V> Map<K, V> cloneMap(Map<K, V> mapToClone) {
        return mapToClone.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
