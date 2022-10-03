package com.example.sep.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.sep.model.Employee;

import org.junit.Test;

import java.util.ArrayList;

public class EmployeesTest {

    @Test
    public void getEmployees() {

        // init
        Employees cEmployees = new Employees();
        cEmployees.initEmployees();
        ArrayList<Employee> employees = cEmployees.getEmployees();

        assertEquals(employees.get(0).getName(), "Janet");
        assertEquals(employees.get(0).getRole(), "Senior Customer Service Officer");
        assertEquals(employees.get(0).getPassword(), "password");
        assertNotEquals(employees.get(0).getName(), "Sandra");

        assertEquals(employees.get(29).getName(), "Charlie");
        assertEquals(employees.get(29).getRole(), "Vice president");
    }

    @Test
    public void getEmployeesFromDb() {

        // init
        Employees cEmployees = new Employees();
        cEmployees.initEmployees();
        Employee employee = cEmployees.getEmployeeFromDb("Janet");

        assertEquals(employee.getName(), "Janet");
        assertEquals(employee.getRole(), "Senior Customer Service Officer");
        assertNotEquals(employee.getName(), "Sandra");

        Employee employee2 = cEmployees.getEmployeeFromDb("Helen");

        assertEquals(employee2.getName(), "Helen");
        assertEquals(employee2.getRole(), "Top chef");

    }
}