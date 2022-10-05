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
        EventItem currentItem = mEventItem.get(position);
        holder.iClientName.setText(mEventItem.get(position).getClientName());
        holder.iLevel.setText(String.valueOf(mEventItem.get(position).getLevel()));
    }

    @Override
    public int getItemCount() {
        return mEventItem.size();
    }

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        public TextView iClientName, iLevel;

        public EventItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iClientName = itemView.findViewById(R.id.tv_cell_client_name);
            iLevel = itemView.findViewById(R.id.tv_cell_level);

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
}
