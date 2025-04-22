package nsu.chebotareva.config.model;

import java.util.*;

public class Config {
    private List<Student> students = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private Map<String, ControlPoint> controlPoints = new HashMap<>();
    private Map<String, String> settings = new HashMap<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Map<String, ControlPoint> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(Map<String, ControlPoint> controlPoints) {
        this.controlPoints = controlPoints;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }
}