package nsu.chebotareva.checker.model;

public class TaskResult {
    private final String taskId;
    private final int score;

    public TaskResult(String taskId, int score) {
        this.taskId = taskId;
        this.score = score;
    }

    public String getTaskId() {
        return taskId;
    }

    public int getScore() {
        return score;
    }
}