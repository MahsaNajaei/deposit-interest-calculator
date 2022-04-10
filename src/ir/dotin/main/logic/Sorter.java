package ir.dotin.main.logic;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public interface Sorter<K, V> {
    static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        Map sortedMap = new TreeMap(new Comparator<K>() {
            @Override
            public int compare(K key1, K key2) {
                return map.get(key2).compareTo(map.get(key1));
            }
        });

        sortedMap.putAll(map);

        return sortedMap;
    }
}