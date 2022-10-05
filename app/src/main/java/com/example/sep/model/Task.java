package com.example.sep.model;

import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Task {
    String department, team, projectReference, taskDescription, assignedTo, taskPriority;

    public Task (String department, String team, String projectReference,
                 String taskDescription, String assignedTo, String taskPriority){

        this.department = department;
        this.team = team;
        this.projectReference = projectReference;
        this.taskDescription = taskDescription;
        this.assignedTo = assignedTo;
        this.taskPriority = taskPriority;

    }


    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

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
}
