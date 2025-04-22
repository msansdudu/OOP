package nsu.chebotareva;

import nsu.chebotareva.checker.RepositoryChecker;
import nsu.chebotareva.config.ConfigLoader;
import nsu.chebotareva.config.model.Config;
import nsu.chebotareva.report.ReportGenerator;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigLoader configLoader = new ConfigLoader();
            Config config = configLoader.load("config.groovy");

            RepositoryChecker checker = new RepositoryChecker(config);
            var results = checker.checkRepositories();

            ReportGenerator reportGenerator = new ReportGenerator(config);
            reportGenerator.generateReport(results, "report.html");

            System.out.println("Report generated: report.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}