import group.blockingqueve_task.BlockingQueue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BlockingQueueTest {
    @Test
    public void testBlockingQueue() throws InterruptedException {
        int capacity = 5;
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        BlockingQueue<Integer> queue = new BlockingQueue<>(capacity);

        Thread producer = new Thread(() -> {
            try {
                for (int i : numbers) {
                    System.out.println("Produced: "+i);
                    queue.enqueue(i);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    Integer value = queue.dequeue();
                    System.out.println("Consumed: "+value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumer.start();
        Thread.sleep(100);
        producer.start();

    }


}
