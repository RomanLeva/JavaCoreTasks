import group.parallel_aggregation_results_task.Student;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AggregationResultsTest {
    @Test
    public void testAggregationResults() {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        List<Map.Entry<String, Integer>> listOfEntriesOfSubjectsAndResults =
                students.parallelStream().flatMap(student -> student.getGrades().entrySet().stream()).toList();

        Map<String, Double> groupedBySubjectAndAverageResult =
                listOfEntriesOfSubjectsAndResults.parallelStream().collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingInt(Map.Entry::getValue)));

        System.out.println("\nСредние оценки по всем студентам:");
        for (Map.Entry<String, Double> entry : groupedBySubjectAndAverageResult.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }


}
