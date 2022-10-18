package com.example.sep.view.taskRecyclerView;

import com.example.sep.model.Event;
import com.example.sep.model.Task;

import java.io.Serializable;

public class TaskItem implements Serializable {

    Integer idx;
    Task iTask;
    String iAssignedToTeam, iAssignedTo, iBudgetAssigned, iSender, iPriority, iBelongsToEvent;
    String iExtraBudgetRequest, iExtraResourceRequest, iTaskPlanningStatus;

    public TaskItem(Task task, Integer idx) {
        iTask = task;
        iBelongsToEvent = task.getBelongsToEvent();
        iAssignedTo = task.getAssignedTo();
        iBudgetAssigned = task.getBudgetForTask();
        iExtraBudgetRequest = task.getRequestExtraBudget();
        iExtraResourceRequest = task.getRequestExtraResources();
        iAssignedToTeam = task.getTeam();
        iPriority = task.getTaskPriority();
        iSender = task.getAssignedBy();
        iTaskPlanningStatus = task.getTaskPlanningStatus();
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public Task getTask() {
        return iTask;
    }

    public String getBelongsToEvent() {return iBelongsToEvent;}

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

    public String getExtraBudgetRequest() {
        return iExtraBudgetRequest;
    }
    public void setExtraBudgetRequest(String iExtraBudgetRequest) {
        this.iExtraBudgetRequest = iExtraBudgetRequest;
    }

    public String getExtraResourcesRequest() {
        return iExtraResourceRequest;
    }
    public void setExtraResourcesRequest(String iExtraResourcesRequest) {
        this.iExtraResourceRequest = iExtraResourcesRequest;
    }

    public String getTaskPlanningStatus() {
        return iTaskPlanningStatus;
    }
    public void settTaskPlanningStatus(String iTaskPlanningStatus) {
        this.iTaskPlanningStatus = iTaskPlanningStatus;
    }


}