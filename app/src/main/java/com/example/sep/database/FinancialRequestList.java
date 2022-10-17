package com.example.sep.database;
/*
mockup of an financial request database
 */
import com.example.sep.model.FinancialRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class FinancialRequestList implements Serializable {
    private ArrayList<FinancialRequest> theRequests;

    public FinancialRequestList() {}

    public ArrayList<FinancialRequest> getTheRequests() {
        if (theRequests == null) theRequests = new ArrayList<>();
        return theRequests;
    }

    public void addFinancialRequest(FinancialRequest financialRequest) {
        if (theRequests == null) theRequests = new ArrayList<>();
        theRequests.add(financialRequest);
    }
    public void deleteFinancialRequest(Integer idx) { theRequests.remove(idx.intValue());}

    public void updateEvent(FinancialRequest request, Integer idx) {
        deleteFinancialRequest(idx);
        addFinancialRequest(request);
    }
}
