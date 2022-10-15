package com.example.sep.database;

import com.example.sep.model.Event;
import com.example.sep.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

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

    public int setNewTaskId() {
        // method to look for the biggest id in the event list and returning a new unique id by adding one to the biggest
        int maxId = 0;
        if (theTasks.size() > 0) {
            maxId = theTasks.stream().max(Comparator.comparing(Task::getId)).get().getId();
        }
        return maxId + 1;
    }
}
