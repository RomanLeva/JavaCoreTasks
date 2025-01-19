package group.iterator_task;

import java.util.HashMap;
import java.util.Map;

public class MyIterator {
    public static <T> Map<T, Integer> countElements(T[] array) {
        Map<T, Integer> elementCountMap = new HashMap<>();

        for (T element : array) {
            elementCountMap.put(element, elementCountMap.getOrDefault(element, 0) + 1);
        }

        return elementCountMap;
    }
}
