package com.example.sep.view.taskRecyclerView;

import com.example.sep.model.Event;
import com.example.sep.model.Task;

import java.io.Serializable;

public class TaskItem implements Serializable {
    Integer idx;
    Task iTask;
    String iTaskSubject, iTaskPriority, iAssignedBy, iDetails;



    public TaskItem(Task task, Integer idx) {
        iTask = task;
        iTaskSubject = task.getProjectReference();
        iTaskPriority = task.getTaskPriority();
        iAssignedBy = task.getAssignedBy();
        iDetails = task.getTaskDescription();
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public Task getTask() {
        return iTask;
    }

    public String getTaskSubject() {
        return iTaskSubject;
    }

    public String getTaskPriority() {
        return iTaskPriority;
    }

    public String getAssignedBy() {
        return iAssignedBy;
    }

    public String getDetails() {
        return iDetails;
    }
}
