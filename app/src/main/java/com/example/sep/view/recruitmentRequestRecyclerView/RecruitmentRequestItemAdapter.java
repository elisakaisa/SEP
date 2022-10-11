package com.example.sep.view.recruitmentRequestRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;

import java.util.ArrayList;

public class RecruitmentRequestItemAdapter extends RecyclerView.Adapter<RecruitmentRequestItemAdapter.RecruitmentRequestViewHolder> {
    public ArrayList<RecruitmentRequestItem> mRequestItem;
    private View.OnClickListener mOnItemClickListener;

    @NonNull
    @Override
    public RecruitmentRequestItemAdapter.RecruitmentRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_recruitemnt_request, parent, false);
        return new RecruitmentRequestItemAdapter.RecruitmentRequestViewHolder(lv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecruitmentRequestItemAdapter.RecruitmentRequestViewHolder holder, int position) {
        holder.iJobTitle.setText(mRequestItem.get(position).getiJobTitle());
        holder.iStatus.setText(mRequestItem.get(position).getiStatus());
    }

    @Override
    public int getItemCount() {
        return mRequestItem.size();
    }

    public class RecruitmentRequestViewHolder extends RecyclerView.ViewHolder{
        public TextView iJobTitle, iStatus;
        public RecruitmentRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            iJobTitle = itemView.findViewById(R.id.tv_rec_request_job_title);
            iStatus = itemView.findViewById(R.id.tv_rec_request_status);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public RecruitmentRequestItemAdapter(ArrayList<RecruitmentRequestItem> requestItem) {
        mRequestItem = requestItem;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
