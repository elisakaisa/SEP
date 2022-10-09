package com.example.sep.view.taskRecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;

import java.util.ArrayList;

public class TaskItemManagerAdapter extends RecyclerView.Adapter<TaskItemManagerAdapter.TaskItemViewHolder> {

    private ArrayList<TaskItem> mTaskItem;
    private View.OnClickListener mOnItemClickListener;


    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_task_manager, parent, false);
        return new TaskItemManagerAdapter.TaskItemViewHolder(lv);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskItemManagerAdapter.TaskItemViewHolder holder, int position) {
        holder.iTaskSubTeam.setText(mTaskItem.get(position).getAssignedTeam());
        holder.iAssignedTo.setText(mTaskItem.get(position).getAssignedTo());
        holder.iBudgetAssigned.setText(String.valueOf(mTaskItem.get(position).getBudgetAssigned()));
        holder.iBudgetRequest.setText(String.valueOf(mTaskItem.get(position).getExtraBudgetRequest()));
    }

    @Override
    public int getItemCount() {
        return mTaskItem.size();
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder {

        public TextView iTaskSubTeam, iAssignedTo, iBudgetAssigned, iBudgetRequest;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iTaskSubTeam = itemView.findViewById(R.id.tv_sub_team);
            iAssignedTo = itemView.findViewById(R.id.tv_assigned_to);
            iBudgetAssigned = itemView.findViewById(R.id.tv_budget_amount);
            iBudgetRequest = itemView.findViewById(R.id.tv_budget_request);
        }
    }

    public TaskItemManagerAdapter(ArrayList<TaskItem> taskItem) {
        mTaskItem = taskItem;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
