package nsu.chebotareva.config;

import nsu.chebotareva.config.model.Config;
import groovy.lang.GroovyShell;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class ConfigLoader {
    public Config load(String configResource) throws Exception {
        Config config = new Config();
        GroovyShell shell = new GroovyShell();
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(configResource)))) {
            shell.setVariable("config", config);
            shell.evaluate(reader);
        }
        return config;
    }
}