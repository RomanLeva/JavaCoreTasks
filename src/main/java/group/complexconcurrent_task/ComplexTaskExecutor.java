package group.complexconcurrent_task;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;

    private Runnable barrierAction = () -> {
        System.out.println("Barrier reached. Lets gain the results");
    };

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        executorService = Executors.newFixedThreadPool(numberOfTasks);
        barrier = new CyclicBarrier(numberOfTasks, barrierAction);
    }

    public void executeTasks(int numberOfTasks) {
        System.out.println("Thread working is " + Thread.currentThread().getName());
        Future<String>[] futures = new Future[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;

            futures[i] = executorService.submit(() -> {
                ComplexTask task = new ComplexTask(taskId);
                String result = task.execute();
                barrier.await();
                return result;
            });
        }

        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println(result + " by thread " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}
