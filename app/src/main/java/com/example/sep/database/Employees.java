package com.example.sep.database;
/*
Mockup database
 */

import com.example.sep.model.Employee;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Employees {

    ArrayList<Employee> dbEmployees = new ArrayList<>();

    // deprtments
    private final String ADMINISTRATION = "Administration";

    // Administration department
    Employee SCSO = new Employee("Janet", "Senior Customer Service Officer", "password");
    Employee CS1 = new Employee("Sarah", "Customer Service", "password");
    Employee CS2 = new Employee("Sam", "Customer Service", "password");
    Employee CS3 = new Employee("Judy", "Customer Service", "password");
    Employee CS4 = new Employee("Carine", "Customer Service", "password");
    Employee HRM = new Employee("Simon", "Senior HR Manager", "password");
    Employee HRA = new Employee("Maria", "HR Assistant", "password");
    Employee AD = new Employee("Mike", "Administration department manager", "password");
    Employee MO = new Employee("David", "Marketing officer", "password");
    Employee MA = new Employee("Emma", "Marketing assistant", "password");

    // Financial department
    Employee FM = new Employee("Alice", "Financial manager", "password");
    Employee Acc1 = new Employee("Fredrik", "Accountant", "password");
    Employee Acc2 = new Employee("Sophia", "Accountant", "password");

    // Production department
    Employee PM = new Employee("Jack", "Production department manager", "password");
    Employee Photo1 = new Employee("Tobias", "Photographer", "password");
    Employee Photo2 = new Employee("Magdalena", "Photographer", "password");
    Employee Audio1 = new Employee("Antony", "Audio specialist", "password");
    Employee Audio2 = new Employee("Adam", "Audio specialist", "password");
    Employee Graphic1 = new Employee("Julia", "Graphic designer", "password");
    Employee Graphic2 = new Employee("Raymond", "Graphic designer", "password");
    Employee Deco1 = new Employee("Magy", "Decorating Architect", "password");
    Employee Deco2 = new Employee("Angelina", "Decorating specialist", "password");
    Employee Network1 = new Employee("Christian", "Network engineer", "password");
    Employee Network2 = new Employee("Nicolas", "Network engineer", "password");
    Employee Network3 = new Employee("Michael", "Technician", "password");
    Employee Network4 = new Employee("Robert", "Technician", "password");

    // Services department
    Employee SM = new Employee("Natalie", "Services department manager", "password");
    Employee Chef = new Employee("Helen", "Top chef", "password");
    Employee SW = new Employee("Kate", "Senior waitress", "password");

    // top management
    Employee VP = new Employee("Charlie", "Vice president", "password");
    Employee Sec1 = new Employee("Jennifer", "Secretary", "password");
    Employee Sec2 = new Employee("Jennifer2", "Secretary", "password");

    public void initEmployees() {
        dbEmployees.add(SCSO);
        dbEmployees.add(CS1);
        dbEmployees.add(CS2);
        dbEmployees.add(CS3);
        dbEmployees.add(CS4);
        dbEmployees.add(HRM);
        dbEmployees.add(HRA);
        dbEmployees.add(AD);
        dbEmployees.add(MO);
        dbEmployees.add(MA);
        dbEmployees.add(FM);
        dbEmployees.add(Acc1);
        dbEmployees.add(Acc2);
        dbEmployees.add(PM);
        dbEmployees.add(Photo1);
        dbEmployees.add(Photo2);
        dbEmployees.add(Audio1);
        dbEmployees.add(Audio2);
        dbEmployees.add(Graphic1);
        dbEmployees.add(Graphic2);
        dbEmployees.add(Deco1);
        dbEmployees.add(Deco2);
        dbEmployees.add(Network1);
        dbEmployees.add(Network2);
        dbEmployees.add(Network3);
        dbEmployees.add(Network4);
        dbEmployees.add(SM);
        dbEmployees.add(Chef);
        dbEmployees.add(SW);
        dbEmployees.add(VP);
        dbEmployees.add(Sec1);
        dbEmployees.add(Sec2);
    }

    public ArrayList<Employee> getEmployees() {
        // function useed to test the database
        return dbEmployees;
    }

    public Employee getEmployeeFromDb(final String searchedName) {
        // funxion to return the Employee from the database based on the searched name
        int index = IntStream.range(0, dbEmployees.size())
                .filter(i -> dbEmployees.get(i).getName().equals(searchedName))
                .findFirst()
                .orElse(-1);
        if (index == -1) return null;
        else return dbEmployees.get(index);
    }


    /*public Employee getEmployeeFromDbByDepartment(final String searchedDepartment) {
        ArrayList<Employees> employees = new ArrayList<>();
        // funxion to return the Employee from the database based on the searched name
        int index = IntStream.range(0, dbEmployees.size())
                .filter(i -> {
                    Integer idx = dbEmployees.get(i).getRole().equals(searchedDepartment));
                    employees.add(dbEmployees.get(index));
                }
                .findFirst()
                .orElse(-1);
        if (index == -1) return null;
        else return employeeName;
    }*/
}

