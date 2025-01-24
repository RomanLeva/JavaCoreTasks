package group.factorial_task;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private int result = 1;
    private static final int THRESHOLD = 5;
    private final int[] integers;
    private int n;

    public FactorialTask(int n) {
        integers = new int[n];
        for (int i = 0; i < n; i++) {
            integers[i] = i+1;
        }
        this.n = n;
    }

    public FactorialTask(int[] integers) {
        this.integers = integers;
    }

    @Override
    protected Integer compute() {
        if (integers.length == 1) {
            return n;
        } else if (integers.length <= THRESHOLD) {
            for (int integer : integers) {
                result *= integer;
            }
        } else {
            int[] leftNewIntegers = new int[integers.length / 2];
            System.arraycopy(integers, 0, leftNewIntegers, 0, integers.length / 2);

            int[] rightNewIntegers = new int[integers.length / 2];
            System.arraycopy(integers, integers.length / 2, rightNewIntegers, 0, rightNewIntegers.length);

            FactorialTask leftSubTask = new FactorialTask(leftNewIntegers);
            FactorialTask rightSubTask = new FactorialTask(rightNewIntegers);

            result = leftSubTask.fork().join() * rightSubTask.fork().join();

        }
        return result;
    }
}
