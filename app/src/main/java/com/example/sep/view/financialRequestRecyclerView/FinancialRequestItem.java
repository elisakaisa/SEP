package com.example.sep.view.financialRequestRecyclerView;

import com.example.sep.model.FinancialRequest;

public class FinancialRequestItem {
    private int idx;
    private FinancialRequest iRequest;
    
    private String iAmount, iStatus, iEventId;

    public FinancialRequestItem(FinancialRequest request, int idx) {
        iRequest = request;
        
        iEventId = request.getEventId();
        iAmount = String.valueOf(request.getRequiredAmount());
        iStatus = request.getStatus();
        
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public String getAmount() {
        return iAmount;
    }

    public String getStatus() {
        return iStatus;
    }
    public String getEventId() { return iEventId; }

    public FinancialRequest getRequest() {
        return iRequest;
    }
}
