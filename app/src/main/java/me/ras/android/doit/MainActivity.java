package me.ras.android.doit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.ras.android.doit.adapter.ToDoAdapter;
import me.ras.android.doit.model.ToDoModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter toDoAdapter;

    private List<ToDoModel> toDoModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        toDoModels = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(toDoAdapter);

        ToDoModel task = new ToDoModel(1, 0, "This is a Test Task");

        toDoModels.add(task);
        toDoModels.add(new ToDoModel(2, 0, "This is a Test Task-2"));
        toDoModels.add(new ToDoModel(3, 0, "This is a Test Task-3"));
        toDoModels.add(new ToDoModel(4, 0, "This is a Test Task-4"));
        toDoModels.add(new ToDoModel(5, 0, "This is a Test Task-5"));

        toDoAdapter.setToDoModels(toDoModels);
    }
}