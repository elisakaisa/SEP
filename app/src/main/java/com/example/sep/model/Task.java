package com.example.sep.model;

import java.io.Serializable;

public class Task implements Serializable {
    String id, department, assignedBy,team, assignedTo, projectReference,
            taskDescription, taskPriority, budgetForTask, taskPlanning;
    Boolean requestExtraBudget, requestExtraResources;

    public Task (String id,
                 String department,
                 String assignedBy,
                 String team,
                 String assignedTo,
                 String projectReference,
                 String taskDescription,
                 String taskPriority,
                 String taskPlanning,
                 String budgetForTask,
                 Boolean requestExtraBudget,
                 Boolean requestExtraResources){

        this.id = id;
        this.department = department;
        this.assignedBy = assignedBy;
        this.team = team;
        this.assignedTo = assignedTo;
        this.projectReference = projectReference;
        this.taskDescription = taskDescription;
        this.taskPlanning = taskPlanning;
        this.taskPriority = taskPriority;

        this.budgetForTask = budgetForTask;
        this.requestExtraBudget = requestExtraBudget;
        this.requestExtraResources = requestExtraResources;
    }



    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAssignedBy() { return assignedBy; }

    public String getId() { return id; }

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
    public Boolean getRequestExtraBudget() { return requestExtraBudget; }
    public Boolean getRequestExtraResources() { return requestExtraResources; }

}
