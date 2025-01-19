import group.filtering_task.Filter;
import group.filtering_task.MyFilter;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    public void testFilter() {
        Collection<String> strings = List.of("Hello", "World", "Java");

        Filter lengthFilter = o -> ((String) o).length() >= 5; // Выведем все слова большие ли равные 5 буквам

        Collection<Object> filtered = MyFilter.filter(strings, lengthFilter);
        for (Object o : filtered) {
            assertEquals(o, true);
            System.out.println(o);
        }

    }
}