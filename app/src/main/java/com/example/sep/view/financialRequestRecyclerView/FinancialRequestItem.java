package com.example.sep.view.financialRequestRecyclerView;

import com.example.sep.model.FinancialRequest;

public class FinancialRequestItem {
    private int idx;
    private FinancialRequest iRequest;
    
    private String iAmount, iDepartment;

    public FinancialRequestItem(FinancialRequest request, int idx) {
        iRequest = request;
        
        iDepartment = request.getRequestingDepartment();
        iAmount = String.valueOf(request.getRequiredAmount());
        
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public String getAmount() {
        return iAmount;
    }

    public String getDepartment() {
        return iDepartment;
    }

    public FinancialRequest getRequest() {
        return iRequest;
    }
}
