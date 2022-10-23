package com.example.sep.database;
/*
mockup of a task database
 */

import com.example.sep.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private ArrayList<Task> theTasks;

    public TaskList() { }

    public ArrayList<Task> getTheTasks() {
        if (theTasks == null) theTasks = new ArrayList<>();
        return theTasks;
    }

    public void addTask(Task task) {
        if (theTasks == null) theTasks = new ArrayList<>();
        theTasks.add(task);
    }

    public void deleteTask(Task task) { theTasks.remove(task); }

    public void updateTask(Task oldTask, Task newTask) {
        deleteTask(oldTask);
        addTask(newTask);
    }

    public int setNewTaskId() {
        // method to look for the biggest id in the event list and returning a new unique id by adding one to the biggest
        int maxId = 0;
        if (theTasks.size() > 0) {
            maxId = theTasks.stream().max(Comparator.comparing(Task::getId)).get().getId();
        }
        return maxId + 1;
    }

    public Task findTaskById(int id) {
        // method to return the task by its id
        return theTasks.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }
}
