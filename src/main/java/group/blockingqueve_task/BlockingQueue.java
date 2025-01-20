package group.blockingqueve_task;

import java.util.ArrayDeque;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        if (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        if (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notify();
        return item;
    }

    // Метод для получения текущего размера очереди
    public synchronized int size() {
        return queue.size();
    }

}
