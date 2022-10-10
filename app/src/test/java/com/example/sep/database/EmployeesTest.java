package com.example.sep.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.sep.model.Employee;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class EmployeesTest {
    Employees cEmployees;

    @Before
    public void init() {
        cEmployees = new Employees();
        cEmployees.initEmployees();
    }

    @Test
    public void getEmployees() {
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

        Employee employee = cEmployees.getEmployeeFromDb("Janet");

        assertEquals(employee.getName(), "Janet");
        assertEquals(employee.getRole(), "Senior Customer Service Officer");
        assertNotEquals(employee.getName(), "Sandra");

        Employee employee2 = cEmployees.getEmployeeFromDb("Helen");

        assertEquals(employee2.getName(), "Helen");
        assertEquals(employee2.getRole(), "Top chef");

    }

    @Test
    public void getEmployeeFromDbBySubTeam() {

        // init
        Employees cEmployees = new Employees();
        cEmployees.initEmployees();
        ArrayList<Employee> employee = cEmployees.getEmployeesFromDbBySubTeam(Employees.PRODUCTION,Employees.PHOTO);

        assertEquals(employee.get(0).getDepartment(), "Production");
        assertEquals(employee.get(0).getRole(), "Photographer");
        assertEquals(employee.get(1).getDepartment(), "Production");
        assertEquals(employee.get(1).getRole(), "Photographer");
        assertEquals(employee.size(), 2);


        ArrayList<Employee> employee2 = cEmployees.getEmployeesFromDbBySubTeam("Service", "Top chef");

        assertEquals(employee2.get(0).getDepartment(), "Service");
        assertEquals(employee2.get(0).getRole(), "Top chef");
        //assertEquals(employee.size(), 1);

    }
}