package com.example.sep.database;

import static org.junit.Assert.*;

import com.example.sep.model.Employee;

import org.junit.Test;

import java.util.List;

public class EmployeesTest {

    @Test
    public void getEmployeesTest() {

        // init
        Employees cEmployees = new Employees();
        List<Employee> employees = cEmployees.getEmployees();

        assertEquals(employees.get(0).getName(), "Janet");
        assertEquals(employees.get(0).getRole(), "Senior Customer Service Officer");
        assertNotEquals(employees.get(0).getName(), "Sandra");
        assertEquals(employees.size(), 32);
    }
}