package com.example.sep.view.taskRecyclerView;

import com.example.sep.model.Event;
import com.example.sep.model.Task;

import java.io.Serializable;

public class TaskItem implements Serializable {

    Integer idx;
    Task iTask;
    String iAssignedToTeam, iAssignedTo, iBudgetAssigned, iSender, iPriority;
    Boolean iExtraBudgetRequest, iExtraResourceRequest;

    public TaskItem(Task task, Integer idx) {
        iTask = task;
        iAssignedTo = task.getAssignedTo();
        iBudgetAssigned = task.getBudgetForTask();
        iExtraBudgetRequest = task.getRequestExtraBudget();
        iExtraResourceRequest = task.getRequestExtraResources();
        iAssignedToTeam = task.getTeam();
        iPriority = task.getTaskPriority();
        iSender = task.getAssignedBy();
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public Task getTask() {
        return iTask;
    }

    public String getAssignedTeam() {
        return iAssignedToTeam;
    }

    public String getAssignedTo() {
        return iAssignedTo;
    }

    public String getBudgetAssigned() {
        return iBudgetAssigned;
    }

    public String getAssignedBy(){return iSender;}
    public String getTaskPriority(){return iPriority;}
    public Integer getTaskID(){return idx;}

    public Boolean getExtraBudgetRequest() {
        return iExtraBudgetRequest;
    }
    public Boolean getExtraResourcesRequest() {
        return iExtraResourceRequest;
    }


}