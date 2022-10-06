package com.example.sep.view.financialRequestRecyclerView;

import com.example.sep.model.FinancialRequest;

public class FinancialRequestItem {
    int idx;
    FinancialRequest iRequest;

    public FinancialRequestItem(FinancialRequest request, int idx) {
        iRequest = request;
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public FinancialRequest getiRequest() {
        return iRequest;
    }
}
