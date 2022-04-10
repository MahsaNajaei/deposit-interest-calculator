package ir.dotin.main.logic;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public interface Sorter<K, V> {
    static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        Map sortedMap = new TreeMap((Comparator<K>) (key1, key2) -> map.get(key2).compareTo(map.get(key1)));

        sortedMap.putAll(map);

        return sortedMap;
    }
}