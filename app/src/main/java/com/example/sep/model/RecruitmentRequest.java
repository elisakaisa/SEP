package com.example.sep.model;

import java.io.Serializable;

public class RecruitmentRequest implements Serializable {
    private String contractType, requestingDepartment, yearsOfExperience, jobTitle, jobDescription, status;
    private int eventId;

    // status
    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String DISMISSED = "Dismissed";

    public RecruitmentRequest(String contractType, String requestingDepartment, String yearsOfExperience, String jobTitle, String jobDescription, String status, int eventId) {
        this.contractType = contractType;
        this.requestingDepartment = requestingDepartment;
        this.yearsOfExperience = yearsOfExperience;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.status = status;
        this.eventId = eventId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getRequestingDepartment() {
        return requestingDepartment;
    }

    public void setRequestingDepartment(String requestingDepartment) {
        this.requestingDepartment = requestingDepartment;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
