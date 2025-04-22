package nsu.chebotareva.checker.model;

import java.util.HashMap;
import java.util.Map;

public class CheckResult {
    private final String studentNick;
    private final Map<String, TaskResult> taskResults;
    private int totalScore;

    public CheckResult(String studentNick) {
        this.studentNick = studentNick;
        this.taskResults = new HashMap<>();
        this.totalScore = 0;
    }

    public void addTaskResult(TaskResult taskResult) {
        taskResults.put(taskResult.getTaskId(), taskResult);
        totalScore += taskResult.getScore();
    }

    public String getStudentNick() {
        return studentNick;
    }

    public Map<String, TaskResult> getTaskResults() {
        return taskResults;
    }

    public int getTotalScore() {
        return totalScore;
    }
}