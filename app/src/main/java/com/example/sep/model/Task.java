package com.example.sep.model;

import java.io.Serializable;

public class Task implements Serializable {
    private int taskId, belongsToEvent;
    private String department, assignedBy,team, assignedTo, projectReference,
            taskDescription, taskPriority, budgetForTask, taskPlanningStatus;
    private String requestExtraBudget, requestExtraResources;

    //Task -> event id, subteam, assigned person, planning status (pending, planned ok, planned extra budget), budget request, recruitment request (no need, pending, done)

    // Task planning status
    public static final String PLANNING_PENDING = "Pending";
    public static final String PLANNING_OK = "OK";
    public static final String PLANNING_EXTRA_BUDGET = "Extra Budget";

    public static final String REQUESTS_CHECK = "TBD";
    public static final String REQUESTS_NO_NEED = "No need";
    public static final String REQUESTS_PENDING = "Pending";
    public static final String REQUESTS_SUBMITTED = "Submitted";
    public static final String REQUESTS_ACCEPTED = "Accepted";


    public Task (int taskId,
                 int belongsToEvent,
                 String department,
                 String assignedBy,
                 String team,
                 String assignedTo,
                 String projectReference,
                 String taskDescription,
                 String taskPriority,
                 String taskPlanningStatus,
                 String budgetForTask,
                 String requestExtraBudget,
                 String requestExtraResources){

        this.taskId = taskId;
        this.belongsToEvent = belongsToEvent;
        this.department = department;
        this.assignedBy = assignedBy;
        this.team = team;
        this.assignedTo = assignedTo;
        this.projectReference = projectReference;
        this.taskDescription = taskDescription;
        this.taskPlanningStatus = taskPlanningStatus;
        this.taskPriority = taskPriority;

        this.budgetForTask = budgetForTask;
        this.requestExtraBudget = requestExtraBudget;
        this.requestExtraResources = requestExtraResources;
    }



    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getBelongsToEvent() {return belongsToEvent;}
    public void setBelongsToEvent(int belongsToEvent) {this.belongsToEvent = belongsToEvent;}

    public String getAssignedBy() { return assignedBy; }

    public Integer getId() { return taskId; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public String getProjectReference() { return projectReference; }
    public void setProjectReference(String projectReference) { this.projectReference = projectReference; }

    public String getTaskDescription() { return taskDescription; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getTaskPriority() { return taskPriority; }
    public void setTaskPriority(String taskPriority) { this.taskPriority = taskPriority; }

    public String getBudgetForTask() { return budgetForTask; }

    public void setBudgetForTask(String budgetForTask) {
        this.budgetForTask = budgetForTask;
    }

    public String getTaskPlanningStatus() { return taskPlanningStatus; }
    public void setTaskPlanningStatus(String taskPlanningStatus) {
        this.taskPlanningStatus = taskPlanningStatus;
    }

    public String getRequestExtraBudget() { return requestExtraBudget; }
    public void setExtraBudgetRequest(String requestExtraBudget) {
        this.requestExtraBudget = requestExtraBudget;
    }
    public String getRequestExtraResources() { return requestExtraResources; }

    public void setRequestExtraResources(String requestExtraResources) {
        this.requestExtraResources = requestExtraResources;
    }
}
