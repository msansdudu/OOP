package nsu.chebotareva.checker;

import nsu.chebotareva.checker.model.CheckResult;
import nsu.chebotareva.checker.model.TaskResult;
import nsu.chebotareva.config.model.Config;
import nsu.chebotareva.config.model.Student;
import nsu.chebotareva.config.model.Task;
import nsu.chebotareva.util.ProcessExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositoryChecker {
    private final Config config;
    private final File baseDir;
    private final ProcessExecutor processExecutor;

    public RepositoryChecker(Config config) {
        this.config = config;
        String repoDir = config.getSettings().getOrDefault("repoDir",
                System.getProperty("java.io.tmpdir") + "/oop-checker-" + UUID.randomUUID());
        this.baseDir = new File(repoDir);
        this.baseDir.mkdirs();
        this.processExecutor = new ProcessExecutor();
    }

    public List<CheckResult> checkRepositories() throws Exception {
        validateGitConfig();
        List<CheckResult> results = new ArrayList<>();

        for (Student student : config.getStudents()) {
            CheckResult result = checkStudentRepository(student);
            results.add(result);
        }

        return results;
    }

    private void validateGitConfig() throws Exception {
        processExecutor.execute("git", "config", "--global", "credential.helper")
                .orElseThrow(() -> new RuntimeException("Git must be configured with credential helper"));
    }

    private CheckResult checkStudentRepository(Student student) throws Exception {
        CheckResult result = new CheckResult(student.getGithubNick());
        File studentDir = new File(baseDir, student.getGithubNick());

        // Клонирование репозитория
        processExecutor.execute("git", "clone", student.getRepoUrl(), studentDir.getAbsolutePath())
                .orElseThrow(() -> new RuntimeException("Failed to clone repository for " + student.getGithubNick()));

        for (Task task : config.getTasks()) {
            TaskResult taskResult = checkTask(studentDir, task);
            result.addTaskResult(taskResult);
        }

        return result;
    }

    private TaskResult checkTask(File studentDir, Task task) {
        File taskDir = new File(studentDir, task.getId());
        int score = taskDir.exists() ? task.getMaxPoints() : 0;
        return new TaskResult(task.getId(), score);
    }
}