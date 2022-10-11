package com.example.sep.database;

import com.example.sep.model.RecruitmentRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class RecruitmentRequestList implements Serializable {

    private ArrayList<RecruitmentRequest> theRequests;

    public RecruitmentRequestList() {}

    public ArrayList<RecruitmentRequest> getTheRequests() {
        if (theRequests == null) theRequests = new ArrayList<>();
        return theRequests;
    }

    public void addRecruitmentRequest(RecruitmentRequest recruitmentRequest) { theRequests.add(recruitmentRequest); }
    public void deleteRecruitmentRequest(Integer idx) { theRequests.remove(idx.intValue()); }

    public void updateRequest(RecruitmentRequest recruitmentRequest, Integer idx) {
        deleteRecruitmentRequest(idx);
        addRecruitmentRequest(recruitmentRequest);
    }
}
