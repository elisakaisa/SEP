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
        EventItem currentItem =mEventItem.get(position);
        holder.iClientName.setText(mEventItem.get(position).getiClientName());

    }

    @Override
    public int getItemCount() {
        return mEventItem.size();
    }

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        public TextView iClientName;

        public EventItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iClientName = itemView.findViewById(R.id.tv_cell_client_name);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public EventItemAdapter(ArrayList<EventItem> eventItem) {
        mEventItem = eventItem;
    }

    public void setOnItemclickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
