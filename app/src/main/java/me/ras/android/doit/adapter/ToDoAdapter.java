package me.ras.android.doit.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.ras.android.doit.AddNewTask;
import me.ras.android.doit.MainActivity;
import me.ras.android.doit.R;
import me.ras.android.doit.model.ToDoModel;
import me.ras.android.doit.util.DatabaseHandler;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> toDoModels;
    private MainActivity mainActivity;
    private DatabaseHandler dbh;

    public ToDoAdapter(DatabaseHandler dbh, MainActivity mainActivity) {
        this.dbh = dbh;
        this.mainActivity = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ViewHolder holder, int position) {
        dbh.openDatabase();
        ToDoModel item = toDoModels.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(intToBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    dbh.updateStatus(item.getId(), 1);
                } else {
                    dbh.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoModels.size();
    }

    private boolean intToBoolean(int i) {
        return i != 0;
    }

    public void setToDoModels(List<ToDoModel> toDoModels) {
        this.toDoModels = toDoModels;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mainActivity;
    }

    public void editItem(int position) {
        ToDoModel item = toDoModels.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(mainActivity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
