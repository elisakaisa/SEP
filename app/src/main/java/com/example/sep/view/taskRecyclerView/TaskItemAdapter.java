package com.example.sep.view.taskRecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;

import java.util.ArrayList;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder> {

    private ArrayList<TaskItem> mTaskItem;

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_task, parent, false);
        return new TaskItemAdapter.TaskItemViewHolder(lv);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskItemAdapter.TaskItemViewHolder holder, int position) {
        holder.iTaskSubject.setText(mTaskItem.get(position).getDetails());
        holder.iPriority.setText(mTaskItem.get(position).getTaskPriority());
        holder.iSender.setText(mTaskItem.get(position).getAssignedBy());
        holder.iSender.setText("View");
    }

    @Override
    public int getItemCount() {
        return mTaskItem.size();
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder {

        public TextView iTaskSubject, iPriority, iSender, iDetails;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iTaskSubject = itemView.findViewById(R.id.tv_task_subject);
            iPriority = itemView.findViewById(R.id.tv_priority);
            iSender = itemView.findViewById(R.id.tv_sender);
            iDetails = itemView.findViewById(R.id.tv_details);
        }
    }
}
