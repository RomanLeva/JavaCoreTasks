package group.filtering_task;

import group.filtering_task.Filter;

import java.util.Collection;
import java.util.List;

public class MyFilter {
    public static List<Object> filter(Collection<?> collection, Filter predicate) {
        return collection.stream().map(predicate::apply).toList()
                .stream().filter(obj -> obj.equals(true)).toList();
    }
}
