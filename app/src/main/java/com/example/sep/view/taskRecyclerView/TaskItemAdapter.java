package com.example.sep.view.taskRecyclerView;

import static com.example.sep.database.Employees.PRODUCTION_MANAGER;
import static com.example.sep.database.Employees.SERVICE_MANAGER;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;
import com.example.sep.viewModel.RoleTransfer;

import java.util.ArrayList;
import java.util.Objects;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder> {

    private final ArrayList<TaskItem> mTaskItem;
    private View.OnClickListener mOnItemClickListener;
    String role = RoleTransfer.getRole();

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
        if (Objects.equals(role, PRODUCTION_MANAGER)|| Objects.equals(role, SERVICE_MANAGER))
        {
            holder.view1.setText(String.valueOf(mTaskItem.get(position).getBelongsToEvent()));
            holder.view2.setText(mTaskItem.get(position).getAssignedTeam());
            holder.view3.setText(mTaskItem.get(position).getAssignedTo());
            holder.view4.setText(String.valueOf(mTaskItem.get(position).getTaskPlanningStatus()));
            holder.view5.setText(String.valueOf(mTaskItem.get(position).getExtraBudgetRequest()));
            holder.view6.setText(String.valueOf(mTaskItem.get(position).getExtraResourcesRequest()));
        } else {
            holder.view1.setText(mTaskItem.get(position).getBelongsToEvent());
            holder.view2.setText(String.valueOf(mTaskItem.get(position).getTaskID()));
            holder.view3.setText(mTaskItem.get(position).getTaskPriority());
            holder.view4.setText(mTaskItem.get(position).getAssignedBy());
            holder.view5.setText("View");
            holder.view6.setText("");
        }


    }

    @Override
    public int getItemCount() {
        return mTaskItem.size();
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder {

        public TextView view1, view2, view3, view4, view5, view6;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view1 = itemView.findViewById(R.id.tv_1);
            view2 = itemView.findViewById(R.id.tv_2);
            view3 = itemView.findViewById(R.id.tv_3);
            view4 = itemView.findViewById(R.id.tv_4);
            view5 = itemView.findViewById(R.id.tv_5);
            view6 = itemView.findViewById(R.id.tv_6);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public TaskItemAdapter(ArrayList<TaskItem> taskItem) {
        mTaskItem = taskItem;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
