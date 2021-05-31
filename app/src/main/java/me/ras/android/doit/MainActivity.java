package me.ras.android.doit;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import me.ras.android.doit.adapter.ToDoAdapter;
import me.ras.android.doit.model.ToDoModel;
import me.ras.android.doit.util.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter toDoAdapter;
    private FloatingActionButton floatingActionButton;

    private List<ToDoModel> toDoModels;
    private DatabaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dbh = new DatabaseHandler(this);
        dbh.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoAdapter = new ToDoAdapter(dbh, this);
        tasksRecyclerView.setAdapter(toDoAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(toDoAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        floatingActionButton = findViewById(R.id.fab);

        toDoModels = dbh.getAllTasks();
        Collections.reverse(toDoModels);
        toDoAdapter.setToDoModels(toDoModels);

        floatingActionButton.setOnClickListener(
                view -> AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG));
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        toDoModels = dbh.getAllTasks();
        Collections.reverse(toDoModels);
        toDoAdapter.setToDoModels(toDoModels);
        toDoAdapter.notifyDataSetChanged();
    }
}