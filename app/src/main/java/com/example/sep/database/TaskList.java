package com.example.sep.database;

import com.example.sep.model.Event;
import com.example.sep.model.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> theTasks;

    public TaskList() { }

    public ArrayList<Task> getTheTasks() {
        if (theTasks == null) theTasks = new ArrayList<>();
        return theTasks;
    }

    public void addTask(Task task) { theTasks.add(task); }
    public void deleteTask(Integer idx) { theTasks.remove(idx.intValue());}

    public void updateTask(Task task, Integer idx) {
        deleteTask(idx);
        addTask(task);
    }
}
