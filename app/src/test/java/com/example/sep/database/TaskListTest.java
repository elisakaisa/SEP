package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class TaskListTest {

    TaskList cTaskList;

    @Before
    public void initList() {
        cTaskList = new TaskList();
        cTaskList.getTheTasks();
        cTaskList.addTask(TestVariables.task1);
        cTaskList.addTask(TestVariables.task2);
    }

    @Test
    public void addTask() {
        // check if tasks are added in init
        assertEquals(cTaskList.getTheTasks().size(), 2);
    }

    @Test
    public void getTheTasks() {
        assertEquals(cTaskList.getTheTasks().get(1).getProjectReference(), TestVariables.task2.getProjectReference());
    }

    @Test
    public void deleteTask() {
        cTaskList.deleteTask(TestVariables.task1);
        assertEquals(cTaskList.getTheTasks().get(0).getId(), TestVariables.task2.getId());
    }

    @Test
    public void updateTask() {
        cTaskList.updateTask(TestVariables.task1, TestVariables.task2);
        assertEquals(cTaskList.getTheTasks().get(0).getId(), TestVariables.task2.getId());
        assertEquals(cTaskList.getTheTasks().get(0).getId(), TestVariables.task2.getId());
    }

    @Test
    public void setNewTaskId() {
        assertEquals(cTaskList.setNewTaskId(), 5);
    }
}