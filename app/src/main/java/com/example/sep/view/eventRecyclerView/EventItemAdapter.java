package com.example.sep.view.eventRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;

import java.util.ArrayList;

public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.EventItemViewHolder> {
    private ArrayList<EventItem> mEventItem;
    private View.OnClickListener mOnItemClickListener;

    @NonNull
    @Override
    public EventItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_event, parent, false);
        return new EventItemViewHolder(lv);
    }

    @Override
    public void onBindViewHolder(@NonNull EventItemViewHolder holder, int position) {
        holder.iClientName.setText(mEventItem.get(position).getClientName());
        holder.iLevel.setText(stringLevelFromInt(mEventItem.get(position).getLevel()));
        holder.iStatus.setText(mEventItem.get(position).getStatus());
        holder.iEventType.setText(mEventItem.get(position).getEventType());
    }

    @Override
    public int getItemCount() {
        return mEventItem.size();
    }

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        public TextView iClientName, iLevel, iStatus, iEventType;

        public EventItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iClientName = itemView.findViewById(R.id.tv_cell_client_name);
            iLevel = itemView.findViewById(R.id.tv_cell_level);
            iStatus = itemView.findViewById(R.id.tv_cell_status);
            iEventType = itemView.findViewById(R.id.tv_cell_event_type);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public EventItemAdapter(ArrayList<EventItem> eventItem) {
        mEventItem = eventItem;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }

    private String stringLevelFromInt(int iLevel) {
        String sLevel = null;
        switch (iLevel) {
            case 0:
                sLevel = "created by CS";
                break;
            case 1:
                sLevel = "approved by SCS";
                break;
            case 2:
                sLevel = "reviewed by FM";
                break;
            case 3:
                sLevel = "approved by AM";
                break;
        }
        return sLevel;
    }
}
