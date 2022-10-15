package com.example.sep.model;

import static org.junit.Assert.*;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
    Employee employee;

    @Before
    public void init() { employee = TestVariables.employee1; }

    @Test
    public void getRole() {
        assertEquals(employee.getRole(), TestVariables.employee1.getRole());
    }

    @Test
    public void setRole() {
        employee.setRole("new Role");
        assertEquals(employee.getRole(), "new Role");
    }

    @Test
    public void getDepartment() {
        assertEquals(employee.getDepartment(), TestVariables.employee1.getDepartment());
    }

    @Test
    public void setDepartment() {
        employee.setDepartment("new dep");
        assertEquals(employee.getDepartment(), "new dep");
    }

    @Test
    public void getPassword() {
        assertEquals(employee.getPassword(), TestVariables.employee1.getPassword());
    }

    @Test
    public void setPassword() {
        employee.setPassword("brooo");
        assertEquals(employee.getPassword(), "brooo");
    }

    @Test
    public void getName() {
        assertEquals(employee.getName(), TestVariables.employee1.getName());
    }

    @Test
    public void setName() {
        employee.setName("new name");
        assertEquals(employee.getName(), "new name");
    }
}