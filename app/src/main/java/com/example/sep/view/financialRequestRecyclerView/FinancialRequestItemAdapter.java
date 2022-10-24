package com.example.sep.view.financialRequestRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.R;

import java.util.ArrayList;

public class FinancialRequestItemAdapter extends RecyclerView.Adapter<FinancialRequestItemAdapter.FinancialRequestViewHolder> {
    public ArrayList<FinancialRequestItem> mRequestItem;
    private View.OnClickListener mOnItemClickListener;

    @NonNull
    @Override
    public FinancialRequestItemAdapter.FinancialRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_financial_request, parent, false);
        return new FinancialRequestItemAdapter.FinancialRequestViewHolder(lv);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialRequestItemAdapter.FinancialRequestViewHolder holder, int position) {

        holder.iEventId.setText(String.valueOf(mRequestItem.get(position).getEventId()));
        holder.iAmount.setText(mRequestItem.get(position).getAmount());
        holder.iStatus.setText(mRequestItem.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mRequestItem.size();
    }


    public class FinancialRequestViewHolder extends RecyclerView.ViewHolder{
        public TextView iEventId, iAmount, iStatus;
        public FinancialRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            iEventId = itemView.findViewById(R.id.tv_fin_request_event_id);
            iAmount = itemView.findViewById(R.id.tv_fin_request_amount);
            iStatus = itemView.findViewById(R.id.tv_fin_request_status);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
    public FinancialRequestItemAdapter(ArrayList<FinancialRequestItem> requestItem) {
        mRequestItem = requestItem;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
}
