package com.example.sep.model;

import java.io.Serializable;

public class FinancialRequest implements Serializable {

    String projectReference, requestingDepartment, requiredAmount, reason;

    public FinancialRequest(String projectRef, String department, String amount, String reason) {
        this.projectReference = projectRef;
        this.requestingDepartment = department;
        this.requiredAmount = amount;
        this.reason = reason;
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

    public String getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(String requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
