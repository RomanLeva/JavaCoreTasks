
import group.factorial_task.FactorialTask;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.ForkJoinPool;


public class ForkJoinFactorialTest {
    @Test
    public void testForkJoinTask() {
        int n = 10; // Вычисление факториала для числа 10

        long result;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
        assertEquals(3628800, result);
    }
}
