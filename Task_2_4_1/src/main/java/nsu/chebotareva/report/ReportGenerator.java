package nsu.chebotareva.report;

import nsu.chebotareva.checker.model.CheckResult;
import nsu.chebotareva.config.model.Config;
import nsu.chebotareva.config.model.Task;

import java.io.FileWriter;
import java.util.List;

public class ReportGenerator {
    private final Config config;

    public ReportGenerator(Config config) {
        this.config = config;
    }

    public void generateReport(List<CheckResult> results, String outputFile) throws Exception {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h1>OOP Course Report</h1>");
        html.append("<table border='1'>");
        html.append("<tr><th>Student</th><th>Total Score</th>");

        for (Task task : config.getTasks()) {
            html.append("<th>").append(task.getName()).append("</th>");
        }
        html.append("</tr>");

        for (CheckResult result : results) {
            html.append("<tr>");
            html.append("<td>").append(result.getStudentNick()).append("</td>");
            html.append("<td>").append(result.getTotalScore()).append("</td>");
            for (Task task : config.getTasks()) {
                int score = result.getTaskResults().getOrDefault(task.getId(), new nsu.chebotareva.checker.model.TaskResult(task.getId(), 0)).getScore();
                html.append("<td>").append(score).append("</td>");
            }
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(html.toString());
        }
    }
}