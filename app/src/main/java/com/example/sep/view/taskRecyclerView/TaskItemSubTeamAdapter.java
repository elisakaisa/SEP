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

public class TaskItemSubTeamAdapter extends RecyclerView.Adapter<TaskItemSubTeamAdapter.TaskItemViewHolder> {
 //TODO combine with TaskItemManagerAdapter
    private ArrayList<TaskItem> mTaskItem;
    private View.OnClickListener mOnItemClickListener;


    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_task_sub_team, parent, false);
        return new TaskItemSubTeamAdapter.TaskItemViewHolder(lv);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskItemSubTeamAdapter.TaskItemViewHolder holder, int position) {
        holder.iTaskSubject.setText(String.valueOf(mTaskItem.get(position).getTaskID()));
        holder.iPriority.setText(mTaskItem.get(position).getTaskPriority());
        holder.iSender.setText(mTaskItem.get(position).getAssignedBy());
        holder.iDetails.setText("View");
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

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public TaskItemSubTeamAdapter(ArrayList<TaskItem> taskItem) {
        mTaskItem = taskItem;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
