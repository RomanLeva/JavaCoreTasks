import group.iterator_task.MyIterator;
import org.junit.Test;

import java.util.Map;

public class IteratorTest {
    @Test
    public void testIterator() {
        String[] elements = {"apple", "banana", "apple", "orange", "banana", "banana"};

        Map<String, Integer> counts = MyIterator.countElements(elements);

        counts.forEach((key, value) -> System.out.println(key + ": " + value));

    }
}