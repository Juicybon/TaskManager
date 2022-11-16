package project.taskmanager;

/**
 * Класс Задача
 */
public class Task {
    public String projectName;
    public String taskName;
    public String task;
    public boolean isDone;

    public Task(String taskName, String task){
        this.taskName = taskName;
        this.task = task;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
