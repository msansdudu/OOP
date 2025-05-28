package nsu.chebotareva.util;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ProcessExecutor {
    public Optional<Integer> execute(String... command) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        if (process.waitFor(30, TimeUnit.SECONDS)) {
            return Optional.of(process.exitValue());
        } else {
            process.destroy();
            return Optional.empty();
        }
    }
}