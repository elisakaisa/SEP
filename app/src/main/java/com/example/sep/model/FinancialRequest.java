package com.example.sep.model;

import java.io.Serializable;

public class FinancialRequest implements Serializable {

    private String projectReference, requestingDepartment, reason, eventId, status;
    private int requiredAmount;

    // status
    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String DISMISSED = "Dismissed";

    public FinancialRequest(String eventId, String projectRef, String department, int amount, String reason, String status) {
        this.eventId = eventId;
        this.projectReference = projectRef;
        this.requestingDepartment = department;
        this.requiredAmount = amount;
        this.reason = reason;
        this.status = status;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getRequestingDepartment() {
        return requestingDepartment;
    }

    public void setRequestingDepartment(String requestingDepartment) {
        this.requestingDepartment = requestingDepartment;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEventId() {
        return eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
