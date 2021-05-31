package me.ras.android.doit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoModel {
    private int id;
    private int status;
    private String task;

    public ToDoModel(int status, String task) {
        this.status = status;
        this.task = task;
    }
}
