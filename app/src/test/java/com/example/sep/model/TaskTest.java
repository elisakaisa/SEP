package com.example.sep.model;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {

    Task task;

    @Before
    public void init() { task = TestVariables.task1; }

    @Test
    public void createTask() {
        assertEquals(task, TestVariables.task1);
    }

    @Test
    public void changeTask() {
        // testing getters and setters
        task.setBudgetForTask(TestVariables.task2.getBudgetForTask());
        task.setExtraBudgetRequest(TestVariables.task2.getRequestExtraBudget());
        task.setTaskPlanningStatus(TestVariables.task2.getTaskPlanningStatus());
        task.setTaskPriority(TestVariables.task2.getTaskPriority());
        task.setTaskDescription(TestVariables.task2.getTaskDescription());
        task.setAssignedTo(TestVariables.task2.getAssignedTo());
        task.setBelongsToEvent(TestVariables.task2.getBelongsToEvent());
        task.setDepartment(TestVariables.task2.getDepartment());
        task.setProjectReference(TestVariables.task2.getProjectReference());
        task.setTeam(TestVariables.task2.getTeam());

        assertEquals(task.getBudgetForTask(), TestVariables.task2.getBudgetForTask());
        assertEquals(task.getRequestExtraBudget(), TestVariables.task2.getRequestExtraBudget());
        assertEquals(task.getTaskPlanningStatus(), TestVariables.task2.getTaskPlanningStatus());
        assertEquals(task.getTaskPriority(), TestVariables.task2.getTaskPriority());
        assertEquals(task.getTaskDescription(), TestVariables.task2.getTaskDescription());
        assertEquals(task.getAssignedTo(), TestVariables.task2.getAssignedTo());
        assertEquals(task.getBelongsToEvent(), TestVariables.task2.getBelongsToEvent());
        assertEquals(task.getDepartment(), TestVariables.task2.getDepartment());
        assertEquals(task.getProjectReference(), TestVariables.task2.getProjectReference());
        assertEquals(task.getTeam(), TestVariables.task2.getTeam());

        assertEquals(task.getAssignedBy(), TestVariables.task1.getAssignedBy());
        assertEquals(task.getId(), TestVariables.task1.getId());
        assertEquals(task.getRequestExtraResources(), TestVariables.task1.getRequestExtraResources());
    }

}