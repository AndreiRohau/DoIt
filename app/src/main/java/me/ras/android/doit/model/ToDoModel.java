package me.ras.android.doit.model;

public class ToDoModel {
    private int id;
    private int status;
    private String task;

    public ToDoModel() {
    }

    public ToDoModel(int id, int status, String task) {
        this.id = id;
        this.status = status;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}