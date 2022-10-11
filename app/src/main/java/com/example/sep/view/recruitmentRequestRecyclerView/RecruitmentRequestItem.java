package com.example.sep.view.recruitmentRequestRecyclerView;

import com.example.sep.model.RecruitmentRequest;

import java.io.Serializable;

public class RecruitmentRequestItem implements Serializable {
    int idx;
    RecruitmentRequest iRequest;

    String iJobTitle, iStatus;

    public RecruitmentRequestItem(RecruitmentRequest request, int idx) {
        iRequest = request;

        iJobTitle = request.getJobTitle();
        iStatus = request.getStatus();

        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public RecruitmentRequest getiRequest() {
        return iRequest;
    }

    public String getiJobTitle() {
        return iJobTitle;
    }

    public String getiStatus() {
        return iStatus;
    }
}
