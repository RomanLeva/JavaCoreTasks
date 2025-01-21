package group.complexconcurrent_task;

public class ComplexTask {
    private int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public String execute(){
        try {
            Thread.sleep(1000); // выполняем сложнейшую задачу
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Complex Task " + taskId + " finished";
    }
}
