package me.ras.android.doit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.ras.android.doit.MainActivity;
import me.ras.android.doit.R;
import me.ras.android.doit.model.ToDoModel;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> toDoModels;
    private MainActivity mainActivity;

    public ToDoAdapter(MainActivity mainActivity) {
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
        ToDoModel item = toDoModels.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(intToBoolean(item.getStatus()));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
